package com.ruoyi.order.service;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 11:45
 */

import com.ruoyi.order.pojo.DriverPoints;
import com.ruoyi.order.pojo.vo.PointsVo;

/**
 * @author : 成长
 * @date : 2024-08-22 11:45
 * @description :
 *
 **/
public interface DriverPointsService {
    void update(Double driverScope, Long driverId);



    DriverPoints selectById(Long driverId);

    DriverPoints selectByDriverId(Long driverId);

    PointsVo selectByPonts(Integer sid);
}
