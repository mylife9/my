package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.TbContent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 广告信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-22
 */
public interface TbContentMapper 
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
     * 删除广告信息
     * 
     * @param id 广告信息主键
     * @return 结果
     */
    public int deleteTbContentById(Long id);

    /**
     * 批量删除广告信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbContentByIds(Long[] ids);

    @Select("select * from tb_content where status=1 and category_id=1 order by sort_order asc")
    List<TbContent> findContentList();
}
