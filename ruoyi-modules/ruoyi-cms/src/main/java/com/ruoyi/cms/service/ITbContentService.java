package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.TbContent;

import java.util.List;

/**
 * 广告信息Service接口
 * 
 * @author ruoyi
 * @date 2024-07-22
 */
public interface ITbContentService 
{
    /**
     * 查询广告信息
     * 
     * @param id 广告信息主键
     * @return 广告信息
     */
    public TbContent selectTbContentById(Long id);

    /**
     * 查询广告信息列表
     * 
     * @param tbContent 广告信息
     * @return 广告信息集合
     */
    public List<TbContent> selectTbContentList(TbContent tbContent);

    /**
     * 新增广告信息
     * 
     * @param tbContent 广告信息
     * @return 结果
     */
    public int insertTbContent(TbContent tbContent);

    /**
     * 修改广告信息
     * 
     * @param tbContent 广告信息
     * @return 结果
     */
    public int updateTbContent(TbContent tbContent);

    /**
     * 批量删除广告信息
     * 
     * @param ids 需要删除的广告信息主键集合
     * @return 结果
     */
    public int deleteTbContentByIds(Long[] ids);

    /**
     * 删除广告信息信息
     * 
     * @param id 广告信息主键
     * @return 结果
     */
    public int deleteTbContentById(Long id);

    List<TbContent> findContentList();
}
