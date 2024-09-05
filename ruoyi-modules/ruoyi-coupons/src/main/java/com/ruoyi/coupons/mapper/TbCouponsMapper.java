package com.ruoyi.coupons.mapper;

import com.ruoyi.coupons.domain.TbCoupons;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 优惠券Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@Mapper
public interface TbCouponsMapper {

    /**
     * 导出优惠券列表
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
     * 删除优惠券
     *
     * @param id 优惠券主键
     * @return 结果
     */
    public int deleteTbCouponsById(Long id);

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbCouponsByIds(Long[] ids);

    void updateCountById(@Param("id") Long id, @Param("couponCount") Long couponCount);

    @Update("UPDATE tb_coupons SET receive_count = receive_count -1 WHERE id = #{couponId}")
    void updateByReceiveCount(Long couponId);

    List<TbCoupons> couponsList(TbCoupons tbCoupons);

    TbCoupons couponsInfo(Long id);

    int startCoupons(@Param("ids")Long[] ids,@Param("couponsStatus") int couponsStatus);

    List<TbCoupons> findStartCouponsByIds(@Param("ids") Long[] ids);




}