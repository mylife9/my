package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.TbGoods;
import com.ruoyi.cms.domain.TbGoodsDesc;
import com.ruoyi.cms.domain.TbItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface TbGoodsMapper {

    void saveGoods(TbGoods goods);

    void saveGoodsDesc(TbGoodsDesc goodsDesc);

    void saveItems(List<TbItem> items);

    List<Map> listAllItem();

    List<Map> listByTime(Date date);

    @Select("select  * from  tb_item where goods_id =#{goodsId}")
    List<TbItem> findItemsByGoodsId(Long goodsId);

    @Select("select  * from tb_goods_desc where goods_id =#{goodsId} ")
    TbGoodsDesc findGoodsDescById(Long goodsId);
    @Select("select  * from tb_goods where id =#{goodsId} ")
    TbGoods findGoodsById(Long goodsId);

    @Select("select  * from tb_goods")
    List<TbGoods> findAllGoods();

    @Select("select  * from  tb_item where id =#{id}")
    TbItem findItemById(Long id);
}
