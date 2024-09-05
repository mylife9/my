package com.ruoyi.taxi.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.controller.WebSocketController;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.TaxiService;
import com.ruoyi.taxi.util.SentinelBlockHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class TaxiServiceImpl implements TaxiService {
    @Autowired
    TaxiMapper taxiMapper;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    WebSocketController webSocketController;
    @Resource
    PassengerUserMapper passengerUserMapper;
    @Resource
    private SentinelBlockHandler sys;

    @Override
    @SentinelResource(value = "query", fallback = "fallbackMethod", blockHandler = "blockHandlerMethod")
    public AjaxResult saveOrder(@Validated PassengerVo passengerVo) {
//        int i = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
        }

//        System.err.println("调用了");
//        if (true) {
//            throw new MyRuntimeException("Something went wrong in ServiceA!");
//        }
        return AjaxResult.success("结束方法");
//
//        //redis key值
//        String key = "Order";
//        //根据id获取用户信息
//        QueryWrapper<PassengerUser> query=new QueryWrapper<>();
//        query.eq("openid",passengerVo.getOpenid());
//        PassengerUser passengerUser = passengerUserMapper.selectOne(query);
//        //查询出用户最新的上一个订单
//       // OrderInfo orderStatus = taxiMapper.selectOrder(passengerUser.getOpenid());
//
//        //如果有进行判断是否未支付
////        if (Optional.ofNullable(orderStatus).isPresent()) {
////            return AjaxResult.error("您有未支付订单");
////        }
//
//        //进行司机家庭地址和目的地的计算
//        List<DriverUserWorkStatus> driverWorkList = taxiMapper.selectDriverWork();
//
//        List<Long> integersList = new ArrayList<>();
//
//
//        for (DriverUserWorkStatus driverWork : driverWorkList) {
//
//            //使用工具类计算上车位置和加位加位置的距离
//            Double newDistance = Utils.calculateDistance(passengerVo.getDestLongitude(), driverWork.getCurrentLongitude(), passengerVo.getDestLatitude(), driverWork.getCurrentLatitude());
//
//            // 增加1.5小时（1.5小时 = 1.5 * 60 * 60 * 1000 毫秒）
//            //判断司机1.5个小时内有没有预约·
//            long millisToAdd = (long) (1.5 * 60 * 60 * 1000);
//            Date endDataTime = new Date(Utils.currentDateTime.getTime() + millisToAdd);
//
//
//            OrderInfo orderInfo = taxiMapper.selectOrderInfo(driverWork.getDriverId(), Utils.currentDateTime, endDataTime);
//            //有订单
//            //java8 optional查询对象是否包含值
//            if (Optional.ofNullable(orderInfo).isPresent()) {
//                //跳出这个司机
//                continue;
//            }
//            //判断5公里以内的开启回家模式司机并且而在接单状态
//            if (newDistance < 5 && driverWork.getIsHeadingHome() == 1 && driverWork.getWorkStatus() == 3) {
//                Collections.shuffle(integersList);
//                integersList.add(driverWork.getDriverId());
//            }
//            //没有就距离用户3公里以内的
//            Double distance = Utils.calculateDistance(passengerVo.getDestLongitude(), driverWork.getAddrLongitude(), passengerVo.getDestLatitude(), driverWork.getAddrLatitude());
//            if (driverWork.getWorkStatus() == 3 && distance < 3) {
//                Collections.shuffle(integersList);
//                integersList.add(driverWork.getDriverId());
//            }
//        }
//        if (integersList.size() > 0) {
//            //获取司机的信息添加到订单表
//            passengerVo.setDriverId(integersList.get(0));
//            DriverUser driverUser = taxiMapper.driverSelectPhone(integersList.get(0));
//
//            //司机的手机号
//            passengerVo.setDriverPhone(Long.valueOf(driverUser.getDriverPhone()));
//            //订单状态为已接单状态
//            passengerVo.setOrderStatus(2);
//        }
//
//       // passengerVo.setPassengerPhone(passengerUser.getPhone());
//
////        String orderId = OrderUtil.createOrderId(passengerVo.getOpenid());
//
//        SnowflakeShardingKeyGenerator str = new SnowflakeShardingKeyGenerator();
//        System.err.println((Long) str.generateKey());
//        Long id = (Long) str.generateKey();
//        passengerVo.setId(id);
//        boolean isOdd = (id % 2 != 0);
//        if (isOdd) {
//            System.err.println("奇数"+"________________s_master库");
//        } else {
//            System.err.println("偶数"+"________________my_m库");
//        }
//        passengerVo.setPickUpPassengerTime(new Date());
//        passengerVo.setPickUpPassengerLongitude(passengerVo.getDepLongitude());
//        passengerVo.setPickUpPassengerLatitude(passengerVo.getDepLatitude());
//
//        //订单状态为未接单状态
//        if (passengerVo.getOrderStatus() == null) {
//            passengerVo.setOrderStatus(1);
//        }
//
//        taxiMapper.insert(passengerVo);
//        //taxiMapper.saveOrder(passengerVo);
//        //删除缓存
//        stringRedisTemplate.delete(key);
//
//        //使用websocket推送消息
//        webSocketController.sendMessage(JSON.toJSONString(passengerVo));
//
//        return AjaxResult.success("添加成功");
    }

    // Fallback 方法示例：返回类型和参数类型需与主方法匹配
    public AjaxResult fallbackMethod(PassengerVo passengerVo, Throwable ex) {
        // 返回类型要与主方法相同
        // 这里可以返回一个空的 List 或其他合适的默认值
        // 备用方法，当 `processRequest` 方法降级时调用
        return AjaxResult.error("sentinel异常方法+111111111111111111");
    }

    // BlockHandler 方法示例：返回类型和参数类型需与主方法匹配
    // 流控处理方法，

    @SneakyThrows
    public AjaxResult blockHandlerMethod(PassengerVo passengerVo, BlockException ex) {
        // 返回类型要与主方法相同
        // 这里可以返回一个空的 List 或其他合适的默认值

        return sys.handle(ex);

    }

}