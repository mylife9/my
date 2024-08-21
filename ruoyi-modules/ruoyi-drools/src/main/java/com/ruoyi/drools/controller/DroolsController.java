package com.ruoyi.drools.controller;


import com.ruoyi.common.core.web.domain.AjaxResult;


import com.ruoyi.drools.domain.Compliance;
import com.ruoyi.drools.domain.ModelPoints;
import com.ruoyi.drools.domain.ServicePoints;
import com.ruoyi.drools.domain.TripPoints;
import com.ruoyi.drools.service.ComplianceService;
import com.ruoyi.drools.service.ModelPointsService;
import com.ruoyi.drools.service.ServicePointsService;
import com.ruoyi.drools.service.TripService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * @author : 暴龙兽
 * @date : 2024-08-19 14:21
 * @description :
 **/
@RestController
@RequestMapping("/drools")
@Slf4j
public class DroolsController {


    @Autowired
    ServicePointsService pointsService;


    @Autowired
    ModelPointsService modelPointsService;

    @Autowired
    ComplianceService complianceService;

    @Autowired
    TripService tripService;



    @XxlJob("test1")
    public AjaxResult drools1() throws Exception  {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setBonusPointId(1);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

         log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    @XxlJob("test2")
    public AjaxResult drools2() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setBonusPointId(2);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    @XxlJob("test3")
    public AjaxResult drools3() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setBonusPointId(3);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    @XxlJob("test4")
    public AjaxResult drools4() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setBonusPointId(4);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    @XxlJob("test5")
    public AjaxResult drools5() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setBonusPointId(5);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    @XxlJob("test6")
    public AjaxResult drools6() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setSubtractPointsId(1);

        servicePoints.setSubtractPoints(0d);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }

    @XxlJob("test7")
    public AjaxResult drools7() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setSubtractPointsId(2);

        servicePoints.setSubtractPoints(0d);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }

    @XxlJob("test8")
    public AjaxResult drools8() {
        ServicePoints servicePoints = new ServicePoints();
        servicePoints.setSubtractPointsId(3);

        servicePoints.setSubtractPoints(0d);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(servicePoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);

        Double servicePoints1 = servicePoints.getServicePoints();

        servicePoints.setDriverId(jobParam);

        Integer driverId = servicePoints.getDriverId();

        pointsService.updateServiceScope(servicePoints1,driverId);

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }


    @XxlJob("test9")
    public AjaxResult drools9() {
        ModelPoints modelPoints = new ModelPoints();

        modelPoints.setVehicleTypeId(1);

        modelPoints.setModelPoints(0d);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(modelPoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);


        Double modelPoints1 = modelPoints.getModelPoints();

        modelPoints.setVehicleTypeId(jobParam);

        Integer vehicleTypeId = modelPoints.getVehicleTypeId();

        modelPointsService.updateModelPoints(modelPoints1,vehicleTypeId);

        log.info("数据库司机车型分产生变化");

        return AjaxResult.success("操作成功").put("list",modelPoints.getModelPoints());


    }

    @XxlJob("test10")
    public AjaxResult drools10() {
        ModelPoints modelPoints = new ModelPoints();

        modelPoints.setVehicleTypeId(2);

        modelPoints.setModelPoints(0d);
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(modelPoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Integer jobParam = Integer.valueOf(XxlJobHelper.getJobParam());

        log.info("参数是"+jobParam);


        Double modelPoints1 = modelPoints.getModelPoints();

        modelPoints.setVehicleTypeId(jobParam);

        Integer vehicleTypeId = modelPoints.getVehicleTypeId();

        modelPointsService.updateModelPoints(modelPoints1,vehicleTypeId);

        log.info("数据库司机车型分产生变化");

        return AjaxResult.success("操作成功").put("list",modelPoints.getModelPoints());


    }
    @XxlJob("test11")
    public AjaxResult drools11() throws Exception {
        //xxl-job传入一个数组
        String jobParam = XxlJobHelper.getJobParam();

        //输出出来数组
        System.out.println("xxl-job接参...[{}]" + jobParam);

        //根据逗号切割
        String[] split = jobParam.split(",");


        //创建合规分对象
        Compliance compliance = new Compliance();

        compliance.setIfNull(1);

        //设置参数

        compliance.setDriverId(Integer.valueOf(split[0]));

        compliance.setDriverCard(split[1]);
        compliance.setIdCard(split[2]);


        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(compliance);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();


        Double scope = compliance.getComplianceScope();

        String driverCard = compliance.getDriverCard();

        Integer driverId = compliance.getDriverId();

        String idCard = compliance.getIdCard();

        complianceService.inserts(scope, driverId, driverCard,idCard);


        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功");
    }

    @XxlJob("test12")
    public AjaxResult drools12() throws Exception {
        //xxl-job传入一个数组
        String jobParam = XxlJobHelper.getJobParam();

        //输出出来数组
        System.out.println("xxl-job接参...[{}]" + jobParam);

        //根据逗号切割
        String[] split = jobParam.split(",");


        //创建合规分对象
        Compliance compliance = new Compliance();

        compliance.setIfNull(0);

        //设置参数

        compliance.setDriverId(Integer.valueOf(split[0]));

        compliance.setDriverCard(split[1]);



        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(compliance);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();


        Double scope = compliance.getComplianceScope();

        String driverCard = compliance.getDriverCard();

        Integer driverId = compliance.getDriverId();



        complianceService.insert(scope, driverId, driverCard);


        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功");
    }
    @XxlJob("test13")
    public AjaxResult drools13() throws Exception {
        //xxl-job传入一个数组
        String jobParam = XxlJobHelper.getJobParam();

        //输出出来数组
        System.out.println("xxl-job接参..." + jobParam);

        //根据逗号切割
        String[] split = jobParam.split(",");


        //创建出行分对象
        TripPoints tripPoints = new TripPoints();

        //设置参数
        tripPoints.setBonusPointId(1);

        tripPoints.setDriverId(Integer.valueOf(split[0]));

        tripPoints.setTripHour(Integer.valueOf(split[1]));

        tripPoints.setNum(Integer.valueOf(split[2]));

        tripPoints.setCreateTime(new Date());


        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(tripPoints);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();


        Double scope = tripPoints.getTripScope();
        Integer num = tripPoints.getNum();
        Integer hour = tripPoints.getTripHour();
        Integer driverId = tripPoints.getDriverId();

        //修改
        tripService.update(scope, num, hour, driverId);


        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功");
    }

}
