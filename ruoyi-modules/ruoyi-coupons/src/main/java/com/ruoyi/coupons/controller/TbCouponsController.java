package com.ruoyi.coupons.controller;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.coupons.config.RedisConfig;
import com.ruoyi.coupons.domain.CouponsType;
import com.ruoyi.coupons.domain.CouponsUse;
import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.domain.User;
import com.ruoyi.coupons.service.ICouponsTypeService;
import com.ruoyi.coupons.service.ICouponsUseService;
import com.ruoyi.coupons.service.ITbCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 优惠券Controller
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/coupons")
public class TbCouponsController extends BaseController {

    @Autowired
    ICouponsTypeService couponsTypeService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private JedisPool jedisPool; // Spring 会自动注入这个 Bean

    RabbitTemplateConfigurer rabbitTemplateConfigurer;
    private Timer timer;
    private boolean isSending = false;

    // 用户领取优惠券
//    ApplicationContext context = new AnnotationConfigApplicationContext();
//    JedisPool jedisPool = (JedisPool)context.getBean("redisPoolFactory");


    @Autowired
    private ITbCouponsService tbCouponsService;
    private Map<Long, Timer> timerMap = new HashMap<>(); // 使用一个 Map 来存储不同 ID 对应的定时器
    private Map<Long, Boolean> sendingStatusMap = new HashMap<>(); // 存储每个 ID 的发送状态
    @Autowired
    private ICouponsUseService couponsUseService;

    /**
     * 查询优惠券列表
     */
//    @RequiresPermissions("coupons:coupons:list")
    @GetMapping("/list")
    public TableDataInfo list(TbCoupons tbCoupons) {
        startPage();
        List<TbCoupons> list = tbCouponsService.selectTbCouponsList(tbCoupons);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 导出优惠券列表
     */
    @RequiresPermissions("coupons:coupons:export")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbCoupons tbCoupons) {
        List<TbCoupons> list = tbCouponsService.selectTbCouponsList(tbCoupons);
        System.out.println(list);
        ExcelUtil<TbCoupons> util = new ExcelUtil<TbCoupons>(TbCoupons.class);
        util.exportExcel(response, list, "优惠券数据");
    }

    /**
     * 获取优惠券详细信息
     */
    @RequiresPermissions("coupons:coupons:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tbCouponsService.selectTbCouponsById(id));
    }

    /**
     * 新增优惠券
     */
    @RequiresPermissions("coupons:coupons:add")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbCoupons tbCoupons) {
        return toAjax(tbCouponsService.insertTbCoupons(tbCoupons));
    }

    /**
     * 修改优惠券
     */
    @RequiresPermissions("coupons:coupons:edit")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbCoupons tbCoupons) {
        return toAjax(tbCouponsService.updateTbCoupons(tbCoupons));
    }

    /**
     * 删除优惠券
     */
    @RequiresPermissions("coupons:coupons:remove")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbCouponsService.deleteTbCouponsByIds(ids));
    }

    /*public String updateCoupons(@RequestBody TbCoupons tbCoupons) {

        Integer status = tbCoupons.getStatus();
        TbCoupons tbCouponsOne = tbCouponsService.selectOne(tbCoupons.getId());

        if (!isSending) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // 这里模拟发送优惠券的逻辑
                    if (status == 1) {
                        //查询当前对象
                        System.out.println(tbCouponsOne);
                        //修改状态
                        tbCouponsService.updateStatus(tbCoupons.getId(), tbCoupons.getStatus());
                        String couponsKey = "coupons:" + tbCouponsOne.getId();
                        Map entries = stringRedisTemplate.opsForHash().entries(couponsKey);

                        if (entries == null || entries.isEmpty()) {
                            entries.put("typeName", tbCouponsOne.getTypeName());
                            entries.put("count", String.valueOf(tbCouponsOne.getReceiveCount()));
                            stringRedisTemplate.opsForHash().putAll(couponsKey, entries);
                        }

                        Long count = stringRedisTemplate.opsForHash().increment(couponsKey, "count", tbCouponsOne.getReceiveCount() * -1);
                        if (count < 0) {
                            stringRedisTemplate.opsForHash().increment(couponsKey, "count", tbCouponsOne.getReceiveCount());
                            tbCouponsService.updateStatus(tbCoupons.getId(), 0);
                        }

                    }

                }

            };
            timer.schedule(task, 0, 5000);
            isSending = true;
            return "开始发送";
        }


        tbCouponsService.updateStatus(tbCoupons.getId(), tbCoupons.getStatus());
        if (isSending && timer != null) {
            timer.cancel();
            isSending = false;
            return "停止发送优惠券";
        }
        return "停止发送";
    }*/

    /**
     * @param
     * @return @return com.ruoyi.common.core.web.page.TableDataInfo
     * @Author M
     * @Description //TODO 查找类型
     * @Date 2024/8/17 16:12:09
     **/
    @GetMapping("/listType")
    public TableDataInfo listType() {
        List<CouponsType> list = couponsTypeService.selectListType();
        return getDataTable(list);
    }

    /**
     * @Description: 优惠劵发放主逻辑 基于Redis实现分布式锁+幂等性 + Redis连接池实现
     * @Author: MaoYiFan
     * @Date: 2024/8/19 22:48
     * @param user: 用户数据
     * @param eventsPromoCodeMark: 本次活动唯一标记
     * @param id:
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *
     */
    @PostMapping("/userReceiveCoupon/{id}/{eventsPromoCodeMark}")
    public AjaxResult redisAcquireLockLock(User user, @PathVariable("eventsPromoCodeMark") String eventsPromoCodeMark,@PathVariable("id") Long id){
        String tokenKey = "CouponKey-" + id;
        TbCoupons tbCoupons = couponsTypeService.selectCoupons(id);
        stringRedisTemplate.opsForHash().put(tokenKey,"couposName",tbCoupons.getCouposName());
        stringRedisTemplate.opsForHash().put(tokenKey,"receiveCount",String.valueOf(tbCoupons.getReceiveCount()));



        if (!jedisPool.isClosed()) {
            try (Jedis jedis = jedisPool.getResource()) {
                // 从 Redis 连接池 (jedisPool) 中获取一个 Jedis 实例的方法。这个连接池用于管理与 Redis 服务器的连接，通过复用连接来提高性能。
                Jedis jedisResources = jedisPool.getResource();
                // 执行 Redis 操作
                String lock = 3 + eventsPromoCodeMark;//设置本次活动优惠码锁（保证幂等性）：userId + 优惠码活动的key
                String userKey = lock + "receiveCoupon";//设置用户优惠码领取key（自定义，只要保证一个用户在一个活动的唯一性即可）

                // 获取缓存数量
                Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(tokenKey);
                Integer count = Integer.valueOf((String) entries.get("receiveCount")) ;

                //优惠码领取库存校验
                if(count <= 0){
                    System.out.println("优惠券已经领取完了");
                    jedisResources.close();
                    return AjaxResult.error("优惠券已经领取完了");
                }

                //为了避免竞争（同一个用户，多个设备同时领取）.使用Redis.setNX() 实现分布式锁（重复数据插入可用其来实现排他锁）
                boolean flag = acquireLock(lock);
                if(flag){
                    //1,获取锁成功,进行用户已领取标记，查询用户是否已经领取过
                    Boolean isExists = jedisResources.exists(userKey);
                    if(!isExists){
                        //2,先使用redis的decr可以实现原子性的递增递减操作控制优惠码不超送,
                        Long success = jedisResources.decrBy(String.valueOf(count),-1);
                        // 先减库存后发码（减库存后返回的现有库存数量大于等于0说明本次抢码成功，再进行发送优惠码，否则库存已经空了就不进行发送优惠码）
                        if(success >= 0L){
                            //3,再进行优惠码分发（可异步执行通过MQ进行发放，如果减库存成功，发放失败，进行发放补偿性操作）
                            jedisResources.set(userKey, "received");
                            stringRedisTemplate.opsForHash().increment(tokenKey, "receiveCount", -1);
                            tbCouponsService.updateByReceiveCount(id);
                            System.out.println("领取成功");
                            long oldValue = Long.valueOf(jedisResources.get(lock));//获取锁的旧值
                            if (oldValue > System.currentTimeMillis()) {//检查处理时间是否小于超时时间,释放锁
                                jedisResources.del(lock);
                            }
                            return AjaxResult.success("领取成功");
                        }
                    } else {
                        System.out.println("已经领取过了");
                        return AjaxResult.error("已经领取过了");
                    }
                } else {
                    System.out.println("请重试");
                    return AjaxResult.error("请重试");
                }
                jedisResources.close();
                return AjaxResult.success();
            }
        } else {
            // 如果连接池已关闭，重新初始化
            jedisPool = new JedisPool(redisConfig.jedisPoolConfig(), "localhost", 6379);

            // 从 Redis 连接池 (jedisPool) 中获取一个 Jedis 实例的方法。这个连接池用于管理与 Redis 服务器的连接，通过复用连接来提高性能。
            Jedis jedisResources = jedisPool.getResource();
            // 执行 Redis 操作
            String lock = 3 + eventsPromoCodeMark;//设置本次活动优惠码锁（保证幂等性）：userId + 优惠码活动的key
            String userKey = lock + "receiveCoupon";//设置用户优惠码领取key（自定义，只要保证一个用户在一个活动的唯一性即可）

            // 获取缓存数量
            Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(tokenKey);
            Integer count = Integer.valueOf((String) entries.get("receiveCount")) ;

            //优惠码领取库存校验
            if(count <= 0){
                System.out.println("优惠券已经领取完了");
                jedisResources.close();
                return AjaxResult.error("优惠券已经领取完了");
            }

            //为了避免竞争（同一个用户，多个设备同时领取）.使用Redis.setNX() 实现分布式锁（重复数据插入可用其来实现排他锁）
            boolean flag = acquireLock(lock);
            if(flag){
                //1,获取锁成功,进行用户已领取标记，查询用户是否已经领取过
                Boolean isExists = jedisResources.exists(userKey);
                if(!isExists){
                    //2,先使用redis的decr可以实现原子性的递增递减操作控制优惠码不超送,
                    Long success = jedisResources.decrBy(String.valueOf(count),-1);
                    // 先减库存后发码（减库存后返回的现有库存数量大于等于0说明本次抢码成功，再进行发送优惠码，否则库存已经空了就不进行发送优惠码）
                    if(success >= 0L){
                        //3,再进行优惠码分发（可异步执行通过MQ进行发放，如果减库存成功，发放失败，进行发放补偿性操作）
                        jedisResources.set(userKey, "received");
                        stringRedisTemplate.opsForHash().increment(tokenKey, "receiveCount", -1);
                        tbCouponsService.updateByReceiveCount(id);
                        System.out.println("领取成功");
                        long oldValue = Long.valueOf(jedisResources.get(lock));//获取锁的旧值
                        if (oldValue > System.currentTimeMillis()) {//检查处理时间是否小于超时时间,释放锁
                            jedisResources.del(lock);
                        }
                        return AjaxResult.success("领取成功");
                    }
                } else {
                    System.out.println("已经领取过了");
                    return AjaxResult.error("已经领取过了");
                }
            } else {
                System.out.println("请重试");
                return AjaxResult.error("请重试");
            }
            jedisResources.close();
            return AjaxResult.success();
        }
    }



    /**
     * @Description: 获取锁方法
     * @Author: MaoYiFan
     * @Date: 2024/8/19 22:58
     * @param lock: 锁的k
     * @return: boolean 本次获锁是否成功
     *
     */
    public  boolean acquireLock(String lock) {
        boolean success;//设置获锁成功标记
        Jedis jedis = jedisPool.getResource();
        int expired = 60 * 1000;//强制过期时间：60 秒
        long value = System.currentTimeMillis() + expired;
        long acquired = jedis.setnx(lock, String.valueOf(value)); //通过SETNX试图获取一个lock
        if (1 == acquired) {//setnx 返回值为1则表示设置成功，则成功获取一个锁
            success = true;
        } else {//setnx 失败，说明锁仍然被其他对象保持，检查其是否已经超时
            long oldValue = Long.valueOf(jedis.get(lock));//获取锁的旧值
            if (oldValue < System.currentTimeMillis()) {//检查是否超时
                String getOldValue = jedis.getSet(lock, String.valueOf(value));//超时，进行锁覆盖，并获取覆盖值之后的获取到的旧值
                // 进行最终获取锁是否成功原子性校验(覆盖旧值之前获取到的旧值 和 覆盖值之后的获取到的旧值 相同 则本次获锁成功，否则 失败已经被别的对象获取到)
                if (Long.valueOf(getOldValue) == oldValue) {
                    success = true;
                } else {// 已被其他进程捷足先登了
                    success = false;
                }
            } else {//未超时，则直接返回获取锁失败
                success = false;
            }
        }
        jedisPool.close();
        return success;
    }

    /**
     * @param tbCoupons
     * @return @return java.lang.String
     * @Author M
     * @Description //TODO 把当前的优惠券存入到redis中
     * @Date 2024/8/18 21:12:09
     **/
    @Transactional
    @PostMapping("/updateCoupons")
    public String updateCoupons(@RequestBody TbCoupons tbCoupons) {


        Integer status = tbCoupons.getStatus();
        // 查询当前对象
        TbCoupons tbCouponsOne = tbCouponsService.selectOne(tbCoupons.getId());

        Long id = tbCouponsOne.getId(); // 获取当前优惠券的 ID
        String couponsKey = "coupons:" + tbCouponsOne.getId();
        //数据存入到redis
        if (status == 1) {
            stringRedisTemplate.opsForHash().put(couponsKey, "total", String.valueOf(tbCouponsOne.getReceiveCount()));
            stringRedisTemplate.opsForHash().put(couponsKey, "count", String.valueOf(tbCouponsOne.getReceiveCount()));
            stringRedisTemplate.opsForHash().put(couponsKey, "typeName", tbCouponsOne.getTypeName());

            //查询用户
            User user1 = new User(1L, "张三", null);
            User user2 = new User(2L, "李四", null);
            List<User> arrayList = new ArrayList();
            arrayList.add(user1);
            arrayList.add(user2);


            for (User user : arrayList) {
                String userKey = "user:" + user.getId() + ":couponNumber:" + tbCouponsOne.getCouponsNumber();


                Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(userKey);
                /*  if (entries == null || entries.isEmpty()) {*/
                String couponsNumber = (String) entries.get("couponsNumber");
                if (couponsNumber != null && couponsNumber.equals(tbCouponsOne.getCouponsNumber())) {
                    return "该类型的券已经领过";
                }

                /*   String total = (String) entries.get("total");*/
                String total = (String) stringRedisTemplate.opsForHash().get(couponsKey, "total");


                Long count = stringRedisTemplate.opsForHash().increment(couponsKey, "count", -3);

                if (count < 0) {
                    stringRedisTemplate.opsForHash().put(couponsKey, "count", total);
                    return "券的数量不足";
                }
                stringRedisTemplate.opsForHash().putAll(userKey, entries);
                entries.put("userId", user.getId() + "");
                entries.put("couponsId", String.valueOf(id));
                entries.put("couponsNumber", tbCouponsOne.getCouponsNumber());
                entries.put("num", "3");

                Integer num = Integer.valueOf(String.valueOf(entries.get("num")));

                if (count > 0) {

                    for (Integer integer = 0; integer < num; integer++) {

                        int i = couponsUseService.associateUserWithCoupon(user.getId(), id);
                    }
                }


                tbCouponsService.updateCountById(id, count);


                /*}*/


                /*     continue;*/
            }


            return "数据已存储到 Redis";
        } else {
            return "状态不符合存储条件";
        }



      /*  Integer status = tbCoupons.getStatus();
        // 查询当前对象
        TbCoupons tbCouponsOne = tbCouponsService.selectOne(tbCoupons.getId());

        Long id = tbCouponsOne.getId(); // 获取当前优惠券的 ID
        String couponsKey = "coupons:" + tbCouponsOne.getId();
        if (!sendingStatusMap.getOrDefault(id, false)) { // 如果当前 ID 未在发送
            Timer timer = new Timer();
            AtomicLong count = new AtomicLong(tbCouponsOne.getReceiveCount());
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // 这里模拟发送优惠券的逻辑
                    if (status == 1) {
                        // 修改状态
                        tbCouponsService.updateStatus(tbCoupons.getId(), tbCoupons.getStatus(), null);


                        long currentCount = count.decrementAndGet();
                        if (currentCount < 0) {
                            count.set(0);
                            tbCouponsService.updateStatus(tbCoupons.getId(), 0, null);
                            return;
                        }

                        stringRedisTemplate.opsForHash().put(couponsKey, "total", String.valueOf(tbCouponsOne.getReceiveCount()));
                        stringRedisTemplate.opsForHash().put(couponsKey, "count", String.valueOf(count.get()));
                        stringRedisTemplate.opsForHash().put(couponsKey, "typeName", tbCouponsOne.getTypeName());
                        stringRedisTemplate.opsForHash().put(couponsKey, "sendCount", );

                    }
                }
            };
            timer.schedule(task, 0, 5000);
            timerMap.put(id, timer); // 将定时器放入 Map 中
            sendingStatusMap.put(id, true); // 设置发送状态为 true
            return "开始发送";
        } else if (sendingStatusMap.get(id) && timerMap.get(id) != null) { // 如果当前 ID 正在发送
            timerMap.get(id).cancel(); // 取消当前 ID 对应的定时器
            timerMap.remove(id); // 从 Map 中移除定时器
            sendingStatusMap.put(id, false); // 设置发送状态为 false

            Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(couponsKey);
            Integer count = Integer.valueOf(String.valueOf(entries.get("count")));

            tbCouponsService.updateStatus(tbCoupons.getId(), 0, count);

            return "停止发送";
        }
        return "无效操作";*/
    }


    @Transactional
    @GetMapping("/getCoupon/{couponId}")
    public String getUserCoupon(@PathVariable Long couponId/*, @RequestHeader("token") String token*/) {
        // 检查优惠券是否存在
        TbCoupons coupon = tbCouponsService.selectOne(couponId);
        if (coupon == null) {
            return "优惠券不存在";
        }


        // 假设通过某种方式获取到当前用户的 ID
   /*     Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();*/


        Long userId = 1L;  // 实际应用中应从登录信息等获取

        // 检查用户是否已领取过该优惠券以及领取次数是否达到限制
        int maxAllowedReceives = 3;
        //查询出当前的优惠券领取了多少张

        int receivedCountForUser = couponsUseService.getReceivedCountForUser(userId, couponId);
        //判断如果查询出的条数超过我所限制的张数
        if (receivedCountForUser >= maxAllowedReceives) {
            return "您不符合领取条件";
        }


        String couponsKey = "coupons:" + couponId;


        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(couponsKey);
        //获取redis中的数量
        Integer count = Integer.valueOf(String.valueOf(entries.get("count")));

        // 业务逻辑：更新优惠券的领取数量
        Long couponCount = stringRedisTemplate.opsForHash().increment(couponsKey, "count", -1);

        if (couponCount < 0) {
            //如果领取的数量最终都小于0了，就让值=0
            Long num = stringRedisTemplate.opsForHash().increment(couponsKey, "count", 0);
        }

        // 将优惠券与用户关联（例如在数据库中记录用户领取的优惠券）
        //存入数据
        int i = couponsUseService.associateUserWithCoupon(userId, couponId);
        //如果插入的数据条数是1或者是>1的数 那么就说明优惠券领取成功了
        if (i >= 1) {
            //如果能优惠券能领取成功 ， 那么数据库中的值就得和redis中的值是一致的
            tbCouponsService.updateCountById(coupon.getId(), couponCount);
        }

        return "领取成功";
    }


}
