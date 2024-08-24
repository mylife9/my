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
import java.util.List;


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


    @PostMapping("test")
    public AjaxResult test(){
       List<ServicePoints> list = pointsService.list();
       return AjaxResult.success("查询成功").put("list",list);
    }



    /**
    * //TODO  无违规订单
    * @author 卫航
    * @date 2024/8/23 14:41
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
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

        List<ServicePoints> list = pointsService.list();

        for (ServicePoints points : list) {
            servicePoints.setSubtractPoints(points.getSubtractPoints());
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }


         log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    /**
    * //TODO 存在神访订单
    * @author 卫航
    * @date 2024/8/23 14:42
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
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
        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {

            servicePoints.setSubtractPoints(points.getSubtractPoints());
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }

    /**
    * //TODO 乘客行程后主动好评无附加内容
    * @author 卫航
    * @date 2024/8/23 14:42
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
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

        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {

            servicePoints.setSubtractPoints(points.getSubtractPoints());
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }

    /**
    * //TODO 乘客行程后主动好评带附加内容
    * @author 卫航
    * @date 2024/8/23 14:43
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
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

        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {

            servicePoints.setSubtractPoints(points.getSubtractPoints());
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }

    /**
    * //TODO 小红花
    * @author 卫航
    * @date 2024/8/23 14:43
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
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

        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {

            servicePoints.setSubtractPoints(points.getSubtractPoints());
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    /**
     * //TODO 存在客诉订单
     * @author 卫航
     * @date 2024/8/23 14:45
     * @return com.ruoyi.common.core.web.domain.AjaxResult
     */
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


        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }




        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    /**
     * //TODO 存在有责取消订单
     * @author 卫航
     * @date 2024/8/23 14:45
     * @return com.ruoyi.common.core.web.domain.AjaxResult
     */

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
        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }
    /**
     * //TODO 存在神访不合格订单
     * @author 卫航
     * @date 2024/8/23 14:45
     * @return com.ruoyi.common.core.web.domain.AjaxResult
     */

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

        List<ServicePoints> list = pointsService.list();
        for (ServicePoints points : list) {
            Double servicePoints1 = servicePoints.getServicePoints();
            servicePoints.setDriverId(points.getDriverId());
            Integer driverId = servicePoints.getDriverId();
            pointsService.updateServiceScope(servicePoints1,driverId);
        }

        log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",servicePoints.getServicePoints());
    }


   /**
   * //TODO 车型分 平价车
   * @author 卫航
   * @date 2024/8/23 14:51
    * @return com.ruoyi.common.core.web.domain.AjaxResult
   */
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

        List<ModelPoints> list = modelPointsService.list();
        for (ModelPoints points : list) {
            Double modelPoints1 = modelPoints.getModelPoints();
            modelPoints.setVehicleTypeId(points.getVehicleTypeId());
            Integer vehicleTypeId = points.getVehicleTypeId();
            modelPointsService.updateModelPoints(modelPoints1,vehicleTypeId);
        }

        log.info("数据库司机车型分产生变化");

        return AjaxResult.success("操作成功").put("list",modelPoints.getModelPoints());


    }

    /**
    * //TODO 车型分  商务车
    * @author 卫航
    * @date 2024/8/23 14:45
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
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

        List<ModelPoints> list = modelPointsService.list();
        for (ModelPoints points : list) {
            Double modelPoints1 = modelPoints.getModelPoints();
            modelPoints.setVehicleTypeId(points.getVehicleTypeId());
            Integer vehicleTypeId = points.getVehicleTypeId();
            modelPointsService.updateModelPoints(modelPoints1,vehicleTypeId);
        }

        log.info("数据库司机车型分产生变化");

        return AjaxResult.success("操作成功").put("list",modelPoints.getModelPoints());


    }

    /**
    * //TODO 合规分计算  上传身份证信息+驾驶证
    * @author 卫航
    * @date 2024/8/23 14:51
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
    @XxlJob("test11")
    public AjaxResult drools11() throws Exception {

        //创建合规分对象
        Compliance compliance = new Compliance();

        compliance.setIfNull(1);



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

        List<Compliance> list = complianceService.list();
        for (Compliance compliance1 : list) {
            Double complianceScope = compliance.getComplianceScope();
            compliance.setDriverId(compliance1.getDriverId());
            Integer driverId = compliance.getDriverId();
            complianceService.updateCompliance(complianceScope,driverId);
        }

        log.info("数据库司机合规分产生变化");

        return AjaxResult.success("操作成功");
    }

    /**
    * //TODO 合规分计算  没有上传身份证
    * @author 卫航
    * @date 2024/8/23 14:50
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
    @XxlJob("test12")
    public AjaxResult drools12() throws Exception {

        //创建合规分对象
        Compliance compliance = new Compliance();

        compliance.setIfNull(0);

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


        List<Compliance> list = complianceService.list();
        for (Compliance compliance1 : list) {
            Double complianceScope = compliance.getComplianceScope();
            compliance.setDriverId(compliance1.getDriverId());
            Integer driverId = compliance.getDriverId();
            complianceService.updateCompliance(complianceScope,driverId);
        }

        log.info("数据库司机合规分产生变化");

        return AjaxResult.success("操作成功");
    }

    /**
    * //TODO 出行分计算  根据结单时长+订单量
    * @author 卫航
    * @date 2024/8/23 14:49
     * @return com.ruoyi.common.core.web.domain.AjaxResult
    */
    @XxlJob("test13")
    public AjaxResult drools13() throws Exception {
        //创建出行分对象
        TripPoints tripPoints = new TripPoints();
        //设置参数
        tripPoints.setBonusPointId(1);


        List<TripPoints> list = tripService.list();

        for (TripPoints points1 : list) {
            tripPoints.setTripHour(points1.getTripHour());
            tripPoints.setNum(points1.getNum());


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

            //scope = 120
            Double tripScope = tripPoints.getTripScope();
            tripPoints.setDriverId(points1.getDriverId());
            Integer driverId1 = tripPoints.getDriverId();
             tripService.update(tripScope, driverId1);

            log.info("数据库司机出行分产生变化");
    }
            return AjaxResult.success("操作成功");
        }


    @PostMapping("/num/{hour}")
    public AjaxResult num(@PathVariable("hour")Integer hour){

        System.out.println(hour);
        TripPoints tripPoints = new TripPoints();
        tripPoints.setCreateTime(new Date());
        tripPoints.setTripHour(hour);
        tripPoints.setBonusPointId(1);

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

        Double tripScope = tripPoints.getTripScope();

        tripService.insert(tripScope,hour);

        return AjaxResult.success("成功");
    }


}
