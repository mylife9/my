package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.TbSpecification;
import com.ruoyi.cms.domain.TbSpecificationOption;
import com.ruoyi.cms.mapper.TbSpecificationMapper;
import com.ruoyi.cms.service.ITbSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-21
 */
@Service
public class TbSpecificationServiceImpl implements ITbSpecificationService
{
    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;

    /**
     * 查询规格管理
     * 
     * @param id 规格管理主键
     * @return 规格管理
     */
    @Override
    public TbSpecification selectTbSpecificationById(Long id)
    {
        TbSpecification tbSpecification = tbSpecificationMapper.selectTbSpecificationById(id);
        List<TbSpecificationOption> options = tbSpecificationMapper.selectTbSpecificationOptionsBySpecId(id);
        tbSpecification.setOptions(options);
        return tbSpecification;
    }

    /**
     * 查询规格管理列表
     * 
     * @param tbSpecification 规格管理
     * @return 规格管理
     */
    @Override
    public List<TbSpecification> selectTbSpecificationList(TbSpecification tbSpecification)
    {
        return tbSpecificationMapper.selectTbSpecificationList(tbSpecification);
    }

    /**
     * 新增规格管理
     * 
     * @param tbSpecification 规格管理
     * @return 结果
     */
    @Override
    public int insertTbSpecification(TbSpecification tbSpecification)
    {
        tbSpecificationMapper.insertTbSpecification(tbSpecification);

        for(TbSpecificationOption option : tbSpecification.getOptions()){
            option.setSpecId(tbSpecification.getId());
            tbSpecificationMapper.insertTbSpecificationOption(option);
        }




        return 1;
    }

    /**
     * 修改规格管理
     * 
     * @param tbSpecification 规格管理
     * @return 结果
     */
    @Override
    public int updateTbSpecification(TbSpecification tbSpecification)
    {
        int i = tbSpecificationMapper.updateTbSpecification(tbSpecification);

        //先删除从表数据
        tbSpecificationMapper.deleteTbSpecificationOptionByIdsAndSpecId(tbSpecification);
        for (TbSpecificationOption option : tbSpecification.getOptions()) {
            if(option.getId() == null){
                option.setSpecId(tbSpecification.getId());
                tbSpecificationMapper.insertTbSpecificationOption(option);
            }else{
                tbSpecificationMapper.updateTbSpecificationOptionById(option);
            }
        }


        return i;
    }

    /**
     * 批量删除规格管理
     * 
     * @param ids 需要删除的规格管理主键
     * @return 结果
     */
    @Override
    public int deleteTbSpecificationByIds(Long[] ids)
    {
        tbSpecificationMapper.deleteTbSpecificationByIds(ids);
        tbSpecificationMapper.deleteTbSpecificationOptionByIds(ids);

        return 1;
    }

    /**
     * 删除规格管理信息
     * 
     * @param id 规格管理主键
     * @return 结果
     */
    @Override
    public int deleteTbSpecificationById(Long id)
    {
        return tbSpecificationMapper.deleteTbSpecificationById(id);
    }

    @Override
    public List<TbSpecificationOption> selectTbSpecificationOptionsById(Long id) {
        return tbSpecificationMapper.selectTbSpecificationOptionsBySpecId(id);
    }
}
