package com.ruoyi.driver.service.impl;

import com.ruoyi.driver.domain.DicDistrict;
import com.ruoyi.driver.mapper.DictionMapper;
import com.ruoyi.driver.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicDistrictServiceImpl implements DicDistrictService {

    @Autowired
    DictionMapper dictionMapper;
    @Override
    public List<DicDistrict> selectDic() {

        return dictionMapper.selectDic();
    }
}
