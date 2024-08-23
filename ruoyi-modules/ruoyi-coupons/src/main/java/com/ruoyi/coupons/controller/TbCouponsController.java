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
import com.ruoyi.coupons.service.ICouponsTypeService;
import com.ruoyi.coupons.service.ITbCouponsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ruoyi.common.core.web.domain.AjaxResult.DATA_TAG;

/**
 * 优惠券Controller
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/coupons")
public class TbCouponsController extends BaseController {
    @Resource
    private ICouponsTypeService couponsTypeService;
    @Resource
    private ITbCouponsService tbCouponsService;

    /**
     * 查询优惠券列表
     */
    @RequiresPermissions("coupons:coupons:list")
    @GetMapping("/list")
    public TableDataInfo couponsList(TbCoupons tbCoupons) {
        startPage();
        AjaxResult result = tbCouponsService.couponsList(tbCoupons);
        List<TbCoupons> couponsList = (List<TbCoupons>) result.get(DATA_TAG);
        return getDataTable(couponsList);
    }

    /**
     * 查询优惠券信息
     */
    @RequiresPermissions("coupons:coupons:query")
    @GetMapping("/query/{id}")
    public AjaxResult query(@PathVariable("id") Long id) {
        return tbCouponsService.couponsInfo(id);
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
     * 新增优惠券
     */
    @RequiresPermissions("coupons:coupons:add")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TbCoupons tbCoupons) {
        return toAjax(tbCouponsService.insertTbCoupons(tbCoupons));
    }

    /**
     * 修改优惠券
     */
    @RequiresPermissions("coupons:coupons:edit")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody TbCoupons tbCoupons) {
        return toAjax(tbCouponsService.updateTbCoupons(tbCoupons));
    }

    /**
     * 删除优惠券
     */
    @RequiresPermissions("coupons:coupons:remove")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbCouponsService.deleteTbCouponsByIds(ids));
    }

    /**
     * @param
     * @return @return com.ruoyi.common.core.web.page.TableDataInfo
     * @Author M
     * @Description //TODO 查找优惠券所有类型
     * @Date 2024/8/17 16:12:09
     **/
    @GetMapping("/listType")
    public TableDataInfo listType() {
        List<CouponsType> list = couponsTypeService.selectListType();
        return getDataTable(list);
    }

    /**
     * @Description:发放优惠券
     * @Author: M
     * @Date: 2024/8/20 星期二 17:04
     * * @param tbCoupons:
     * * @return: com.ruoyi.common.core.web.domain.AjaxResult
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/startCoupons/{ids}")
    public AjaxResult startCoupons(@PathVariable Long[] ids) {
        return tbCouponsService.startCoupons(ids);
    }

    /**
     * @Description:停止发放优惠券
     * @Author: M
     * @Date: 2024/8/20 星期二 17:09
     * * @param null:
     * * @return: null
     */
    @GetMapping("/closeCoupons/{ids}")
    public AjaxResult closeCoupons(@PathVariable Long[] ids) {
        return tbCouponsService.closeCoupons(ids);
    }

    /**
     * @param userId: * @return: java.lang.String
     * @Description:优惠券的领取
     * @Author:M
     * @Date: 2024/8/19 星期一 22:53
     * * @param couponId:
     */

    @GetMapping("/getCoupon/{couponId}/{userId}")
    public AjaxResult getUserCoupon(@PathVariable Long couponId, @PathVariable Long userId) {
        return tbCouponsService.getUserCoupon(couponId, userId);
    }

    /**
     * @Description:订单使用优惠券
     * @Author: M
     * @Date: 2024/8/20 星期二 21:02
     * * @param couponId:
     * * @return: com.ruoyi.common.core.web.domain.AjaxResult
     */
    @PostMapping("/useCoupon/{orderId}/{userId}/{couponsId}")
    public AjaxResult useCoupon(@PathVariable Long orderId,
                                @PathVariable Integer userId,
                                @PathVariable Integer couponsId) {
        return tbCouponsService.useCoupon(orderId, userId, couponsId);
    }

    /**
     * @Description:查询用户可用优惠券
     * @Author: M
     * @Date: 2024/8/20 星期二 20:43
     * * @param userId:
     * * @return: com.ruoyi.common.core.web.domain.AjaxResult
     */
    @GetMapping("/usableCoupon/{userId}")
    public AjaxResult usableCoupon(@PathVariable("userId") @Validated Long userId) {
        return tbCouponsService.usableCoupon(userId);
    }








}
