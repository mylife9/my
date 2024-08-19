package com.ruoyi.coupons.service;

import com.ruoyi.coupons.domain.TbCoupons;

import java.util.List;

/**
 * 优惠券Service接口
 *
 * @author ruoyi
 * @date 2024-08-16
 */
public interface ITbCouponsService {
    /**
     * 查询优惠券
     *
     * @param id 优惠券主键
     * @return 优惠券
     */
    public TbCoupons selectTbCouponsById(Long id);

    /**
     * 查询优惠券列表
     *
     * @param tbCoupons 优惠券
     * @return 优惠券集合
     */
    public List<TbCoupons> selectTbCouponsList(TbCoupons tbCoupons);

    /**
     * 新增优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    public int insertTbCoupons(TbCoupons tbCoupons);

    /**
     * 修改优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    public int updateTbCoupons(TbCoupons tbCoupons);

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的优惠券主键集合
     * @return 结果
     */
    public int deleteTbCouponsByIds(Long[] ids);

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券主键
     * @return 结果
     */
    public int deleteTbCouponsById(Long id);


    TbCoupons selectOne(Long id);

    void updateStatus(Long id, Integer status, Integer count);


    void updateCountById(Long id, Long couponCount);
}
