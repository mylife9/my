package com.ruoyi.points.service.impl;


import com.ruoyi.points.domain.Compliance;
import com.ruoyi.points.mapper.ComplianceMapper;
import com.ruoyi.points.service.IComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 合规分Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@Service
@Transactional
public class ComplianceServiceImpl implements IComplianceService
{
    @Autowired
    private ComplianceMapper complianceMapper;

    /**
     * 查询合规分
     * 
     * @param id 合规分主键
     * @return 合规分
     */
    @Override
    public Compliance selectComplianceById(Long id)
    {
        return complianceMapper.selectComplianceById(id);
    }

    /**
     * 查询合规分列表
     * 
     * @param compliance 合规分
     * @return 合规分
     */
    @Override
    public List<Compliance> selectComplianceList(Compliance compliance)
    {
        return complianceMapper.selectComplianceList(compliance);
    }

    /**
     * 新增合规分
     * 
     * @param compliance 合规分
     * @return 结果
     */
    @Override
    public int insertCompliance(Compliance compliance)
    {
        return complianceMapper.insertCompliance(compliance);
    }

    /**
     * 修改合规分
     * 
     * @param compliance 合规分
     * @return 结果
     */
    @Override
    public int updateCompliance(Compliance compliance)
    {
        return complianceMapper.updateCompliance(compliance);
    }

    /**
     * 批量删除合规分
     * 
     * @param ids 需要删除的合规分主键
     * @return 结果
     */
    @Override
    public int deleteComplianceByIds(Long[] ids)
    {
        return complianceMapper.deleteComplianceByIds(ids);
    }

    /**
     * 删除合规分信息
     * 
     * @param id 合规分主键
     * @return 结果
     */
    @Override
    public int deleteComplianceById(Long id)
    {
        return complianceMapper.deleteComplianceById(id);
    }
}
