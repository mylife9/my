package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.domain.Points;
import com.ruoyi.drools.mapper.PointsMapper;
import com.ruoyi.drools.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:04
 * @description :
 **/
@Service
@Transactional
public class PointsServiceImpl implements PointsService {

    @Autowired
    PointsMapper pointsMapper;
    @Override
    public List<Points> list() {
        return pointsMapper.list();
    }

    @Override
    public void upate1(Double scope) {
        pointsMapper.update1(scope);
    }

    @Override
    public void update2(Double scope) {
        pointsMapper.update2(scope);
    }

    @Override
    public void upate3(Double scope) {
        pointsMapper.update3(scope);
    }


}
