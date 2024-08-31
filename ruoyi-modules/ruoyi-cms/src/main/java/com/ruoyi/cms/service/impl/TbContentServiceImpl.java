package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.TbContent;
import com.ruoyi.cms.mapper.TbContentMapper;
import com.ruoyi.cms.service.ITbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-22
 */
@Service
public class TbContentServiceImpl implements ITbContentService 
{
    @Autowired
    private TbContentMapper tbContentMapper;

    /**
     * 查询广告信息
     * 
     * @param id 广告信息主键
     * @return 广告信息
     */
    @Override
    public TbContent selectTbContentById(Long id)
    {
        return tbContentMapper.selectTbContentById(id);
    }

    /**
     * 查询广告信息列表
     * 
     * @param tbContent 广告信息
     * @return 广告信息
     */
    @Override
    public List<TbContent> selectTbContentList(TbContent tbContent)
    {
        return tbContentMapper.selectTbContentList(tbContent);
    }

    /**
     * 新增广告信息
     * 
     * @param tbContent 广告信息
     * @return 结果
     */
    @Override
    public int insertTbContent(TbContent tbContent)
    {
        return tbContentMapper.insertTbContent(tbContent);
    }

    /**
     * 修改广告信息
     * 
     * @param tbContent 广告信息
     * @return 结果
     */
    @Override
    public int updateTbContent(TbContent tbContent)
    {
        return tbContentMapper.updateTbContent(tbContent);
    }

    /**
     * 批量删除广告信息
     * 
     * @param ids 需要删除的广告信息主键
     * @return 结果
     */
    @Override
    public int deleteTbContentByIds(Long[] ids)
    {
        return tbContentMapper.deleteTbContentByIds(ids);
    }

    /**
     * 删除广告信息信息
     * 
     * @param id 广告信息主键
     * @return 结果
     */
    @Override
    public int deleteTbContentById(Long id)
    {
        return tbContentMapper.deleteTbContentById(id);
    }

    @Override
    public List<TbContent> findContentList() {
        return tbContentMapper.findContentList();
    }
}
