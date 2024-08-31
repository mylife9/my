package com.ruoyi.order.service.impl;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 11:46
 */

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.mapper.DriverPointsMapper;
import com.ruoyi.order.pojo.DriverPoints;
import com.ruoyi.order.pojo.vo.PointsVo;
import com.ruoyi.order.service.DriverPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 成长
 * @date : 2024-08-22 11:46
 * @description :
 *
 **/
@Service
public class DriverPointsServiceImpl implements DriverPointsService {
    @Autowired
    DriverPointsMapper driverPointsMapper;
    @Override
    public void update(Double driverScope, Long driverId) {
         driverPointsMapper.update(driverScope,driverId);
    }

    @Override
    public DriverPoints selectById(Long driverId) {
       return driverPointsMapper.selectById(driverId);
    }

    @Override
    public DriverPoints selectByDriverId(Long driverId) {
        return driverPointsMapper.selectByDriverId(driverId);
    }

    @Override
    public PointsVo selectByPonts(Integer sid) {
        return driverPointsMapper.selectByPonts(sid);
    }


}
