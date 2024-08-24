package com.ruoyi.approve.service.impl;

import java.util.List;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.ruoyi.approve.service.PinyinCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.approve.mapper.CarApproveMapper;
import com.ruoyi.approve.domain.TbCar;
import com.ruoyi.approve.service.ICarService;

import javax.annotation.Resource;

/**
 * 车辆管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
@Service
public class CarApproveServiceImpl implements ICarService
{
   @Resource
    CarApproveMapper approveMapper;


    @Resource
    PinyinCarService pinyinCarService;


    /**
     * 查询车辆管理
     *
     * @param id 车辆管理主键
     * @return 车辆管理
     */
    @Override
    public TbCar selectCarById(Long id)
    {
        return approveMapper.selectCarById(id);
    }

    /**
     * 查询车辆管理列表
     *
     * @param car 车辆管理
     * @return 车辆管理
     */
    @Override
    public List<TbCar> selectCarList(TbCar car)
    {
        return approveMapper.selectCarList(car);
    }

    /**
     * 新增车辆管理
     *
     * @param car 车辆管理
     * @return 结果
     */
    @Override
    public int insertCar(TbCar car)
    {
        if(!StringUtil.isEmpty(car.getBrand())){
            String pinyinCar = pinyinCarService.convertToPinyin(car.getBrand());
            car.setPinyinCar(pinyinCar);
        }
        return approveMapper.insertCar(car);
    }

    /**
     * 修改车辆管理
     *
     * @param car 车辆管理
     * @return 结果
     */
    @Override
    public int updateCar(TbCar car)
    {
        if(!StringUtil.isEmpty(car.getBrand())){
            String pinyinCar = pinyinCarService.convertToPinyin(car.getBrand());
            car.setPinyinCar(pinyinCar);
        }
        return approveMapper.updateCar(car);
    }

    /**
     * 批量删除车辆管理
     *
     * @param ids 需要删除的车辆管理主键
     * @return 结果
     */
    @Override
    public int deleteCarByIds(Long[] ids)
    {
        return approveMapper.deleteCarByIds(ids);
    }

    /**
     * 删除车辆管理信息
     *
     * @param id 车辆管理主键
     * @return 结果
     */
    @Override
    public int deleteCarById(Long id)
    {
        return approveMapper.deleteCarById(id);
    }

    @Override
    public int updateCarStatus(TbCar car) {
        return approveMapper.updateCarStatus(car);

    }

}
