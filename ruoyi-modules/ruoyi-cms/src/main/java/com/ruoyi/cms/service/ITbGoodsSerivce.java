package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.GoodsEntity;
import com.ruoyi.cms.domain.TbGoods;
import com.ruoyi.cms.domain.TbItem;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ITbGoodsSerivce {

    void save(GoodsEntity goodsEntity);

    List<Map> listAllItem();

    List<Map> listByTime(String time);

    void genPage(Long goodsId) throws IOException, TemplateException;

       List<TbGoods> findAllGoods();

    TbItem findItemById(Long id);
}
