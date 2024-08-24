package com.ruoyi.points.service;

import com.ruoyi.points.domain.Compliance;

import java.util.List;


/**
 * 合规分Service接口
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
public interface IComplianceService
{
    /**
     * 查询合规分
     * 
     * @param id 合规分主键
     * @return 合规分
     */
    public Compliance selectComplianceById(Long id);

    /**
     * 查询合规分列表
     * 
     * @param compliance 合规分
     * @return 合规分集合
     */
    public List<Compliance> selectComplianceList(Compliance compliance);

    /**
     * 新增合规分
     * 
     * @param compliance 合规分
     * @return 结果
     */
    public int insertCompliance(Compliance compliance);

    /**
     * 修改合规分
     * 
     * @param compliance 合规分
     * @return 结果
     */
    public int updateCompliance(Compliance compliance);

    /**
     * 批量删除合规分
     * 
     * @param ids 需要删除的合规分主键集合
     * @return 结果
     */
    public int deleteComplianceByIds(Long[] ids);

    /**
     * 删除合规分信息
     * 
     * @param id 合规分主键
     * @return 结果
     */
    public int deleteComplianceById(Long id);
}
