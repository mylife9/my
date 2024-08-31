package com.ruoyi.cms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.cms.domain.*;
import com.ruoyi.cms.mapper.TbBrandMapper;
import com.ruoyi.cms.mapper.TbGoodsMapper;
import com.ruoyi.cms.mapper.TbItemCatMapper;
import com.ruoyi.cms.service.ITbGoodsSerivce;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class TbGoodsSerivceImpl implements ITbGoodsSerivce {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public void save(GoodsEntity goodsEntity) {
        TbGoods goods = goodsEntity.getGoods();
        TbGoodsDesc goodsDesc = goodsEntity.getGoodsDesc();
        List<TbItem> items = goodsEntity.getItems();


        tbGoodsMapper.saveGoods(goods);

        goodsDesc.setGoodsId(goods.getId());
        tbGoodsMapper.saveGoodsDesc(goodsDesc);

        //根据tb_goods表的brand_id查询品牌名称
        TbBrand tbBrand = tbBrandMapper.selectTbBrandById(Long.parseLong(goods.getBrandId()+""));
        TbItemCat tbItemCat = tbItemCatMapper.selectTbItemCatById(goods.getCategory3Id());

        //获取上传的第一张图片路径
        String itemImages = goodsDesc.getItemImages();
        List<Map> images = JSONObject.parseArray(itemImages, Map.class);
        String url = (String) images.get(0).get("url");


        for(TbItem item : items){
            //处理商品title       品牌名称  商品名称  规格1    规格2   规格3
            String title = tbBrand.getBrandName()+" "+goods.getGoodsName();
            String spec = item.getSpec();

            Map specMap = JSONObject.parseObject(spec, Map.class);
            Iterator iterator = specMap.keySet().iterator();
            while (iterator.hasNext()){
                Object key = iterator.next();
                title+= " "+specMap.get(key);
            }
            item.setTitle(title);


            item.setImage(url);

            item.setCategoryId(Integer.valueOf(goods.getCategory3Id()+""));

            item.setStatus("0");
            item.setGoodsId(goods.getId());
            item.setSeller("2112旗舰店");
            item.setSellerId("2112");

            item.setCategory(tbItemCat.getName());
            item.setBrand(tbBrand.getBrandName());

        }

        tbGoodsMapper.saveItems(items);
    }

    @Override
    public List<Map> listAllItem() {
        return tbGoodsMapper.listAllItem();
    }

    @Override
    public List<Map> listByTime(String time) {
        Date date = new Date(Long.parseLong(time));


        return tbGoodsMapper.listByTime(date);
    }

    @Override
    public void genPage(Long goodsId) throws IOException, TemplateException {
        List<TbItem> itemList =tbGoodsMapper.findItemsByGoodsId(goodsId);
        TbGoodsDesc goodsDesc= tbGoodsMapper.findGoodsDescById(goodsId);
        TbGoods goods=tbGoodsMapper.findGoodsById(goodsId);
        for (TbItem tbItem : itemList) {
            //生成静态页
            //页面=freemarker(数据+模版)
            Configuration configuration = new Configuration();

//            String path = this.getClass().getResource("/").getPath();
            String path="D:\\ideaaa\\springboot\\shop-master\\ruoyi-modules\\ruoyi-cms\\src\\main\\resources\\";
            File file = new File(path+"freemarker");
            configuration.setDirectoryForTemplateLoading(file);
            //根据模版名称从模版文件夹中获取模版文件
            Template template = configuration.getTemplate("item.ftl");

            Map map=new HashMap<>();
            map.put("item",tbItem);
            map.put("goods",goods);
            map.put("goodsDesc",goodsDesc);
            map.put("itemList",itemList);

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));


            File pageFile = new File("E:\\nginx-1.26.1\\nginx-1.26.1\\html\\page\\"+tbItem.getId()+".html");
            FileOutputStream outputStream = new FileOutputStream(pageFile);


            IOUtils.copy(inputStream,outputStream);
        }
    }

    @Override
    public List<TbGoods> findAllGoods() {
        return tbGoodsMapper.findAllGoods();

    }

    @Override
    public TbItem findItemById(Long id) {

        return tbGoodsMapper.findItemById(id);
    }
}
