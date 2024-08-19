package com.ruoyi.coupons.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.coupons.domain.CouponsType;
import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.domain.User;
import com.ruoyi.coupons.service.ICouponsTypeService;
import com.ruoyi.coupons.service.ICouponsUseService;
import com.ruoyi.coupons.service.ITbCouponsService;
import com.ruoyi.coupons.utils.RedissonUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedissonUtils redissonUtils;
    RabbitTemplateConfigurer rabbitTemplateConfigurer;
    private Timer timer;
    private boolean isSending = false;
    @Autowired
    private ITbCouponsService tbCouponsService;
    private Map<Long, Timer> timerMap = new HashMap<>(); // 使用一个 Map 来存储不同 ID 对应的定时器
    private Map<Long, Boolean> sendingStatusMap = new HashMap<>(); // 存储每个 ID 的发送状态
    @Autowired
    private ICouponsUseService couponsUseService;

    /**
     * 查询优惠券列表
     */
    @RequiresPermissions("coupons:coupons:list")
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

    /**
     * @Description:优惠券的领取
     * @Author: dsh
     * @Date: 2024/8/19 星期一 22:53
     * * @param couponId:
     * @param userId:
     * * @return: java.lang.String
     *
     */
    @Transactional
    @GetMapping("/getCoupon/{couponId}/{userId}")
    public String getUserCoupon(@PathVariable Long couponId, @PathVariable Long userId) {
        return couponsUseService.getUserCoupon(couponId,userId);
    }
}
