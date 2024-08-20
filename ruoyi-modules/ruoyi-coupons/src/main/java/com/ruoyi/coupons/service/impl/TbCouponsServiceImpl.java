package com.ruoyi.coupons.service.impl;

import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.mapper.TbCouponsMapper;
import com.ruoyi.coupons.service.ITbCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class TbCouponsServiceImpl implements ITbCouponsService {
    @Resource
    private TbCouponsMapper tbCouponsMapper;

    /**
     * 查询优惠券
     *
     * @param id 优惠券主键
     * @return 优惠券
     */
    @Override
    public TbCoupons selectTbCouponsById(Long id) {
        return tbCouponsMapper.selectTbCouponsById(id);
    }

    /**
     * 查询优惠券列表
     *
     * @param tbCoupons 优惠券
     * @return 优惠券
     */
    @Override
    public List<TbCoupons> selectTbCouponsList(TbCoupons tbCoupons) {
        return tbCouponsMapper.selectTbCouponsList(tbCoupons);
    }

    /**
     * 新增优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    @Override
    public int insertTbCoupons(TbCoupons tbCoupons) {

        tbCoupons.setCouponsStatus(Long.valueOf(0));

        return tbCouponsMapper.insertTbCoupons(tbCoupons);
    }

    /**
     * 修改优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    @Override
    public int updateTbCoupons(TbCoupons tbCoupons) {
        return tbCouponsMapper.updateTbCoupons(tbCoupons);
    }

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的优惠券主键
     * @return 结果
     */
    @Override
    public int deleteTbCouponsByIds(Long[] ids) {
        return tbCouponsMapper.deleteTbCouponsByIds(ids);
    }

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券主键
     * @return 结果
     */
    @Override
    public int deleteTbCouponsById(Long id) {
        return tbCouponsMapper.deleteTbCouponsById(id);
    }

    @Override
    public TbCoupons selectOne(Long id) {


        return tbCouponsMapper.selectOne(id);


    }

    @Override
    public void updateStatus(Long id, Integer status, Integer count) {
        tbCouponsMapper.updateStatus(id, status, count);
    }

    @Override
    public void updateCountById(Long id, Long couponCount) {
        tbCouponsMapper.updateCountById(id, couponCount);

    }


}
