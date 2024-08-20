package com.ruoyi.drools.controller;


import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.drools.domain.Points;

import com.ruoyi.drools.service.PointsService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    PointsService pointsService;



    @XxlJob("test1")
    public AjaxResult drools1() throws Exception  {
        Points points = new Points();
        points.setOriginalIntegral1(0D);
        points.setDriverId(1);
        points.setEventType1(1);
        points.setServiceScore(points.getServiceScore());

        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
         kieSession.dispose();
        Double scope=points.getServiceScore();

         pointsService.upate1(scope);
         log.info("数据库司机服务分产生变化");

        return AjaxResult.success("操作成功").put("list",points.getServiceScore());
    }
    @XxlJob("test2")
    public AjaxResult drools2() {
        Points points = new Points();
        points.setOriginalIntegral1(0D);

        points.setEventType1(2);
        points.setTripScore(points.getTripScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Double scope=points.getTripScore();

        pointsService.upate3(scope);
        log.info("数据库司机出行分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getTripScore());
    }
    @XxlJob("test3")
    public AjaxResult drools3() {
        Points points = new Points();
        points.setOriginalIntegral1(0D);

        points.setEventType1(3);
        points.setServiceScore(points.getServiceScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();
        Double scope=points.getServiceScore();

        pointsService.upate1(scope);
        log.info("数据库司机服务分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getServiceScore());
    }
    @XxlJob("test4")
    public AjaxResult drools4() {
        Points points = new Points();
        points.setOriginalIntegral1(0D);

        points.setEventType1(4);
        points.setServiceScore(points.getServiceScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();
        Double scope=points.getServiceScore();

        pointsService.upate1(scope);
        log.info("数据库司机服务分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getServiceScore());
    }
    @XxlJob("test5")
    public AjaxResult drools5() {
        Points points = new Points();
        points.setOriginalIntegral1(0D);

        points.setEventType1(5);
        points.setServiceScore(points.getServiceScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Double scope=points.getServiceScore();

        pointsService.upate1(scope);
        log.info("数据库司机服务分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getServiceScore());
    }
    @XxlJob("test6")
    public AjaxResult drools6() {
        Points points = new Points();
        points.setOriginalIntegral1(0D);

        points.setEventType1(6);
        points.setComplianceScore(points.getComplianceScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Double scope=points.getComplianceScore();
        pointsService.update2(scope);



        log.info("数据库司机合规分产生变化");




        return AjaxResult.success("操作成功").put("list",points.getComplianceScore());
    }
    @XxlJob("test7")
    public AjaxResult drools7() {
        Points points = new Points();
        points.setOriginalIntegral(0D);

        points.setEventType(1);
        points.setServiceScore(points.getServiceScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();

        Double scope=points.getServiceScore();

        pointsService.upate1(scope);
        log.info("数据库司机服务分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getServiceScore());
    }
    @XxlJob("test8")
    public AjaxResult drools8() {
        Points points = new Points();
        points.setOriginalIntegral(0D);

        points.setEventType(2);
        points.setTripScore(points.getTripScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();
        Double scope=points.getTripScore();

        pointsService.upate3(scope);
        log.info("数据库司机出行分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getTripScore());
    }
    @XxlJob("test9")
    public AjaxResult drools9() {
        Points points = new Points();
        points.setOriginalIntegral(0D);

        points.setEventType(3);
        points.setComplianceScore(points.getComplianceScore());
        //获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        //获取kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //创建 kieSession 对象来管理规则的应用和执行过程
        KieSession kieSession = kieContainer.newKieSession();
        //insert fact
        kieSession.insert(points);
        //触发规则
        kieSession.fireAllRules();
        //关闭kieSession
        kieSession.dispose();
        Double scope=points.getComplianceScore();

        pointsService.update2(scope);
        log.info("数据库司机合规分产生变化");


        return AjaxResult.success("操作成功").put("list",points.getComplianceScore());
    }





}
