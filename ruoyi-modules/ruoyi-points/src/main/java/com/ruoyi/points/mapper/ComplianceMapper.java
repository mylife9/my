package com.ruoyi.points.mapper;


import com.ruoyi.points.domain.Compliance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 合规分Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@Mapper
public interface ComplianceMapper
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
     * 删除合规分
     * 
     * @param id 合规分主键
     * @return 结果
     */
    public int deleteComplianceById(Long id);

    /**
     * 批量删除合规分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteComplianceByIds(Long[] ids);
}
