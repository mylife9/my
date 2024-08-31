package com.ruoyi.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.mapper.OrderMapper;
import com.ruoyi.order.pojo.Driver;
import com.ruoyi.order.pojo.OrderInfo;
import com.ruoyi.order.pojo.PriceRule;
import com.ruoyi.order.service.OrderService;
import com.ruoyi.order.service.WebSocket;
import com.ruoyi.order.utils.AddressUtils;
import com.ruoyi.order.utils.DateUtils;
import com.ruoyi.order.utils.KilometreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.ruoyi.common.core.web.domain.AjaxResult.CODE_TAG;

/**
 * @program: RuoYi-Cloud
 * @author: gtx
 * @description:
 * @create: 2024-08-21 12:59
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    KilometreUtils kilometreUtils;

    @Autowired
    WebSocket webSocket;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public AjaxResult orderUpdate(OrderInfo orderInfo) {

        OrderInfo order = orderMapper.orderSelectOne(Long.valueOf(orderInfo.getId()));

        System.out.println(order);

        //判断司机是否接单
        if (order.getOrderStatus() == 1) {
            log.info("订单开始");

            //司机没有接单，不用扣费，正常修改，也不用司机同意
            orderMapper.orderUpdate(orderInfo);
        } else if (order.getOrderStatus() == 2 || order.getOrderStatus() == 3 || order.getOrderStatus() == 4) {
            log.info("司机已经接单");

            //司机已经接单，改单需司机同意
            order.setDepLatitude(orderInfo.getDepLatitude());
            order.setDepLongitude(orderInfo.getDepLongitude());
            order.setDestLatitude(orderInfo.getDestLatitude());
            order.setDestLongitude(orderInfo.getDestLongitude());

        } else {
            log.info("司机开始行程");
            //司机开始行驶，用户需先支付之前路程的费用，并需司机同意
            order.setDepLatitude(orderInfo.getDepLatitude());
            order.setDepLongitude(orderInfo.getDepLongitude());
            order.setDestLatitude(orderInfo.getDestLatitude());
            order.setDestLongitude(orderInfo.getDestLongitude());

            //计算起始位置和当前位置的公里数，并计算金额
            String s = kilometreUtils.kilometreCom(orderInfo.getDepLongitude(), orderInfo.getDepLatitude(), order.getDepLongitude(), order.getDepLatitude());
            log.info("公里数为：" + s);

            //计数金额
            Driver driver = orderMapper.selectDriverOne(order.getDriverId());

            PriceRule priceRule = orderMapper.selectPrice(driver.getAddress());
            //
            double sum = Double.valueOf(s) - priceRule.getStartMile();

            double price = 0.00;

            if (sum > 0) {
                price = priceRule.getUnitPricePerMinute() * sum + priceRule.getStartFare();

            } else {
                price = priceRule.getStartFare();
            }
            String kilometreCom = kilometreUtils.kilometreCom(orderInfo.getDepLongitude(), orderInfo.getDepLatitude(), orderInfo.getDestLongitude(), orderInfo.getDestLatitude());

            double sum1 = Double.valueOf(kilometreCom) - priceRule.getStartMile();
            double price1 = 0.00;

            if (sum1 > 0) {
                price1 = priceRule.getUnitPricePerMinute() * sum1 + priceRule.getStartFare();

            } else {
                price1 = priceRule.getStartFare();
            }

            order.setPrice(price + price1);

        }

        webSocket.sendMessage(JSON.toJSONString(order));


        return AjaxResult.success("请稍等....");

    }

    @Override
    public AjaxResult orderUpdateOk(OrderInfo orderInfo) {
        //司机临时切换下车地点，修改订单目的地
        orderMapper.orderUpdate(orderInfo);

        return AjaxResult.success("改单成功");

    }

    @Override
    public AjaxResult detectionMethod2(Long driverId) throws ParseException {
        //1.查询司机上一次的订单状态
        //可能刷单行为次数key
        String orderNumKey = "method2-num-" + DateUtils.today() + driverId;
        //刷单次数key
        String orderCountKey = "method2-count-" + DateUtils.today() + driverId;
        //司机当天订单数据key订单量
        String orderKey = "order-" + DateUtils.today() + driverId;
        //获取刷单次数
        String orderCount = stringRedisTemplate.opsForValue().get(orderCountKey);
        if (!StringUtils.isEmpty(orderCount) && Integer.valueOf(orderCount) >= 3) {
            return AjaxResult.error(500, "警告，您的账号已被冻结，请联系客服");
        }
        //获取redis订单数据
        String orderJson = stringRedisTemplate.opsForValue().get(orderKey);
        //判断redis订单数据是否为空
        if (!StringUtils.isEmpty(orderJson)) {
            List<OrderInfo> orderList = JSON.parseArray(orderJson, OrderInfo.class);
            OrderInfo orderInfo = orderList.get(orderList.size() - 1);
            // orderStatus  8: 支付完成 9.订单取消
            //2.判断司机上一个订单状态
            //2.1状态为已取消
            if (orderInfo.getOrderStatus() == 9) {
                //如果已取消，可能刷单次数+1
                String orderNum = stringRedisTemplate.opsForValue().get(orderNumKey);
                if (StringUtils.isEmpty(orderNum)) {
                    stringRedisTemplate.opsForValue().set(orderNumKey, String.valueOf(1), DateUtils.timeRemaining(), TimeUnit.SECONDS);
                }else{
                    stringRedisTemplate.opsForValue().increment(orderNumKey, 1);
                }
                //redis查询可能刷单次数
                String orderNumNow = stringRedisTemplate.opsForValue().get(orderNumKey);
                if (Integer.valueOf(orderNumNow) >= 3) {
                    if(StringUtils.isEmpty(orderCount)){
                        stringRedisTemplate.opsForValue().set(orderCountKey, String.valueOf(1), DateUtils.timeRemaining(), TimeUnit.SECONDS);
                        //将可能刷单次数清空
                        stringRedisTemplate.delete(orderNumKey);
                        return AjaxResult.success("警告，此次订单可能存在刷单行为");
                    }
                    else{
                        //刷单次数+1
                        stringRedisTemplate.opsForValue().increment(orderCountKey, 1);
                        //将可能刷单次数清空
                        stringRedisTemplate.delete(orderNumKey);
                        return AjaxResult.success("警告，此次订单可能存在刷单行为");
                    }
                }
                return AjaxResult.success();
            }
            //2.1状态为正常结束
            return AjaxResult.success();
        }
        //今日未接单，不进行检测
        return AjaxResult.success();
    }

    @Override
    public AjaxResult saveOrderInfoToRedis(OrderInfo orderInfo) throws ParseException {
        //支付完成或订单取消后的操作
        //获取当天订单信息

        //司机当天订单数据key
        String orderKey = "order-" + DateUtils.today() + orderInfo.getDriverId();
        String orderJson = stringRedisTemplate.opsForValue().get(orderKey);
        //判断订单信息是否为空
        if (!StringUtils.isEmpty(orderJson)) {
            //不为空，追加存储
            List<OrderInfo> orderList = JSON.parseArray(orderJson, OrderInfo.class);
            orderList.add(orderInfo);
            //将集合转为json字符串
            String orderStr = JSON.toJSONString(orderList);
            //将订单信息存储到redis中，并设置过期时间为，当天还剩多长时间
            stringRedisTemplate.opsForValue().set(orderKey, orderStr, DateUtils.timeRemaining(), TimeUnit.SECONDS);
        } else {
            //为空，重新存储
            List<OrderInfo> orderList = new ArrayList<>();
            orderList.add(orderInfo);
            //将集合转为json字符串
            String orderStr = JSON.toJSONString(orderList);
            //将订单信息存储到redis中，并设置过期时间为，当天还剩多长时间
            stringRedisTemplate.opsForValue().set(orderKey, orderStr, DateUtils.timeRemaining(), TimeUnit.SECONDS);
        }
        return AjaxResult.success("订单信息存储成功");
    }

    @Override
    public AjaxResult checkOrderAddr(OrderInfo newOrder) throws ParseException {
        //获取redis刷单次数key
        String orderCountKey = "method2-count-" + DateUtils.today() + newOrder.getDriverId();
        //获取redis刷单次数
        String orderCount = stringRedisTemplate.opsForValue().get(orderCountKey);
        if(!StringUtils.isEmpty(orderCount)&&Integer.valueOf(orderCount) >= 3){
            return AjaxResult.error(500, "警告，您的账号已被冻结，请联系客服");
        }
        //司机当天订单数据key
        String orderKey = "order-" + DateUtils.today() + newOrder.getDriverId();
        //获取当天的订单
        String orderJson = stringRedisTemplate.opsForValue().get(orderKey);
        if (!StringUtils.isEmpty(orderJson)) {
            //不为空，追加存储
            List<OrderInfo> orderList = JSON.parseArray(orderJson, OrderInfo.class);
            OrderInfo lastOrder = orderList.get(orderList.size() - 1);
            AjaxResult result = AddressUtils.receiveOrder(lastOrder, newOrder);
            String code = String.valueOf( result.get(CODE_TAG));
            switch (code){
                case "500":
                    if(StringUtils.isEmpty(orderCount)){
                        stringRedisTemplate.opsForValue().set(orderCountKey, String.valueOf(1), DateUtils.timeRemaining(), TimeUnit.SECONDS);
                    }else{
                        stringRedisTemplate.opsForValue().increment(orderCountKey, 1);
                    }
                    return AjaxResult.error(500,"警告：司机 " + newOrder.getDriverId() + " 可能存在刷单行为，订单 " + lastOrder.getId() + " 和 " + newOrder.getId() + " 距离过近！");
                case "404":
                    return AjaxResult.error(404,"订单信息尚未完善");
                default:
                    return AjaxResult.success();
            }
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult detectionMethod1(OrderInfo orderInfo) {

        Boolean flag = true;

        //司机id
        Long driverId = orderInfo.getDriverId();
        //乘客id
        String passengerId = orderInfo.getPassengerId();

        //时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());

        String key = "method2-"+format+":"+driverId;
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();

        Set<ZSetOperations.TypedTuple<String>> typedTuples = opsForZSet.rangeWithScores(key, 0, -1);

        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {

            //用户id
            String value = typedTuple.getValue();
            //计数
            Double score = typedTuple.getScore();

            if (value.equals(JSON.toJSONString(passengerId))){
                if (score == 2){
                    //警告
                    log.info("有连续相同订单，有刷单可能，警告-----------");
                    flag = false;
                } else if (score == 4) {
                    //封号
                    log.info("有连续相同订单，有刷单可能，警告不听，封号-----------");
                    return AjaxResult.error("512","封号处理");
                }
            }
        }

        opsForZSet.incrementScore(key,passengerId+"",1);
        stringRedisTemplate.expire(key,10, TimeUnit.MINUTES);

        if (flag){
            return AjaxResult.success();
        }else {
            return AjaxResult.error("500","有异常订单");
        }

    }

    @Override
    public AjaxResult detectionMethod3(OrderInfo orderInfo) {

        Map<String, Integer> mapCount = new HashMap<>();
        Map<String, Date> lastOrderTime = new HashMap<>();

        //根据司机id查询司机所有订单
        List<OrderInfo> orders = orderMapper.selectOrderByDriver(orderInfo.getDriverId());

        for (OrderInfo order : orders) {

            //拼接起始位置
            String startLocationKey = order.getDepLatitude() + ","+ order.getDepLongitude();
            //拼接结束位置
            String endLocationKey = order.getDestLatitude() +","+ order.getDestLongitude();

            Date orderTime = order.getOrderTime();

            //更新起始位置的最后一次订单时间
            lastOrderTime.put(startLocationKey,orderTime);
            //更新结束位置的最后一次订单时间
            lastOrderTime.put(endLocationKey,orderTime);

            //记录起始位置的重复次数
            mapCount.put(startLocationKey,mapCount.getOrDefault(startLocationKey,0)+1);
            //记录结束位置的重复次数
            mapCount.put(endLocationKey,mapCount.getOrDefault(endLocationKey,0)+1);

        }

        //遍历每个位置的重复次数
        for (Map.Entry<String, Integer> entry : mapCount.entrySet()) {

            String locationKey = entry.getKey();
            Integer count = entry.getValue();

            //获取最后一次订单的时间
            Date date = lastOrderTime.get(locationKey);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

            //检查短时间内相同位置的重复次数
            if (count > 5 && date != null){

                LocalDateTime thresholdTime = localDateTime.minusMinutes(10);

                Instant toInstant = thresholdTime.atZone(zoneId).toInstant();
                Date from = Date.from(toInstant);
                //查询区间
                List<OrderInfo> orderInfos = orderMapper.orderSelectDate(orderInfo.getDriverId(),from,date);

                int js = 0;
                for (OrderInfo info : orderInfos) {

                    String[] parts = locationKey.split(",");
                    String refLat = parts[0];
                    String refLon = parts[1];

                    if (kilometreUtils.isWithinRange(refLat,refLon,info.getDestLatitude(),info.getDestLongitude())){
                        js++;
                    } else if (kilometreUtils.isWithinRange(refLat,refLon,info.getDestLatitude(),info.getDestLongitude())) {
                        js++;
                    }

                }
               //结束数量、时间、
                if (js > 3){
                    return AjaxResult.error(512,"有异常订单，封号处理..");
                } else if (js >1) {
                    return AjaxResult.error(500,"有异常订单，警告处理..");
                }

            }

        }
        return AjaxResult.success();

    }

    @Transactional
    @Override
    public AjaxResult userCancelOrder(OrderInfo orderInfo) {

        //先判断订单状态、订单下单时间
        Integer orderStatus = orderInfo.getOrderStatus();
        Date orderTime = orderInfo.getOrderTime();
        Double price = orderInfo.getPrice();

        switch (orderStatus){
            case 1:
                //订单状态在未接单状态，可以直接取消
                orderMapper.userCancelOrder(Long.valueOf(orderInfo.getId()),9);

                break;
            case 2:

            case 3:
                Date date = new Date();
                Date endTime = new Date(orderTime.getTime() + 60 * 1000);
                long abs = Math.abs(orderTime.getTime() - date.getTime());
                if (abs < 1){
                    //司机已经接单，并且时间在一分钟内可以直接取消
                    orderMapper.userCancelOrder(Long.valueOf(orderInfo.getId()),9);

                }else {
                    //司机已经接单，但是时间在一分钟后，需支付一定金额（0.02%）
                    Double sum = price * 0.02;

                    //支付


                    //修改
                    orderMapper.userCancelOrder(Long.valueOf(orderInfo.getId()),7);


                }

                break;
            case 4:
                //司机已经到达目的地，需支付一定金额（0.1%）
                Double sum = price * 0.1;

                //支付

                //修改
                orderMapper.userCancelOrder(Long.valueOf(orderInfo.getId()),7);

                break;
        }

        return AjaxResult.success();

    }


}
