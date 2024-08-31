package com.ruoyi.order.controller;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 11:46
 */

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.pojo.DriverPoints;
import com.ruoyi.order.pojo.Passenger;
import com.ruoyi.order.pojo.vo.PointsVo;
import com.ruoyi.order.service.DriverPointsService;
import com.ruoyi.order.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : 成长
 * @date : 2024-08-22 11:46
 * @description :
 **/

@RestController
@RequestMapping("/driver")
@Slf4j
public class DriverPointsController {

    @Autowired
    private DriverPointsService driverPointsService;

    @Autowired
    private PassengerService passengerService;


    @PostMapping("/selectCountDriver/{driverId}/{sid}")
    public AjaxResult selectCountDriver(@PathVariable("driverId") Long driverId,
                                        @PathVariable("sid") Integer sid) {
        //根据司机id查询司机id数据
        DriverPoints driver = driverPointsService.selectByDriverId(driverId);

        //根据sid查询要加的分数
        PointsVo pointsVo = driverPointsService.selectByPonts(sid);
        pointsVo.setDriverScope(driver.getDriverScope());
        pointsVo.setDriverId(driver.getDriverId());

        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(pointsVo);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();
        //从规则计算获得的信用分
        Double scope = pointsVo.getDriverScope();
        driverPointsService.update(scope, driverId);
        log.info("===========司机收到评价" + pointsVo.getScoreName() + "========查看数据库");

        return AjaxResult.success(driver);
    }


    @PostMapping("/selectCountPassenger/{passengerId}")
    public AjaxResult selectCountPassenger(@PathVariable("passengerId") Long passengerId) {

        //根据乘客id获取乘客数据
        Passenger passenger = passengerService.selectByPassengerId(passengerId);

        passenger.setStatus(passenger.getStatus());

        Date createTime = passenger.getCreateTime();
        Date date = new Date();
        long time = createTime.getTime();
        long time1 = date.getTime();
        long time2 = time1 - time;

        log.info("相差天数" + time2 / 1000 / 24 / 60 / 60);

        passenger.setDay(time2/1000/24/60/60);

        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(passenger);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Double scope = passenger.getPassengerScope();

        passengerService.update(scope,passengerId);

        log.info("========触发规则成功,查看数据库====");

        return AjaxResult.success("操作成功");

    }


}
