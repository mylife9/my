package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.TbItemCat;
import com.ruoyi.cms.service.ITbItemCatService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类目管理Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/category")
public class TbItemCatController extends BaseController
{
    @Autowired
    private ITbItemCatService tbItemCatService;

    /**
     * 查询商品类目管理列表
     */
    @RequiresPermissions("cms:category:list")
    @GetMapping("/list")
    public TableDataInfo list(TbItemCat tbItemCat)
    {
        List<TbItemCat> list = tbItemCatService.selectTbItemCatList(tbItemCat);
        return getDataTable(list);
    }

    @GetMapping("/getTemplateId")
    public Long getTemplateId(String name)
    {
        return tbItemCatService.getTemplateId(name);
    }


    @GetMapping("/listTree")
    public TableDataInfo listTree()
    {
        List<TbItemCat> list = tbItemCatService.selectTbItemCatList(null);

        //1、把所有一级分类装入一个集合
        //2、把所有对象装到父级的children集合里

        Map<Long,TbItemCat> map = new HashMap<>();
        //1200次
        for(TbItemCat itemCat : list){
            map.put(itemCat.getId(), itemCat);
        }

        List result = new ArrayList();
        for(TbItemCat itemCat : list){
            if(itemCat.getParentId()==0){
                //一级分类
                result.add(itemCat);
            }else{
                //不是一级，装入父级对象的children集合里
                Long parentId = itemCat.getParentId();
                TbItemCat parent = map.get(parentId);
                List<TbItemCat> children = parent.getChildren();
                if(children == null){
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(itemCat);
            }
        }

        return getDataTable(result);
    }

    /**
     * 导出商品类目管理列表
     */
    @RequiresPermissions("cms:category:export")
    @Log(title = "商品类目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbItemCat tbItemCat)
    {
        List<TbItemCat> list = tbItemCatService.selectTbItemCatList(tbItemCat);
        ExcelUtil<TbItemCat> util = new ExcelUtil<TbItemCat>(TbItemCat.class);
        util.exportExcel(response, list, "商品类目管理数据");
    }

    /**
     * 获取商品类目管理详细信息
     */
    @RequiresPermissions("cms:category:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbItemCatService.selectTbItemCatById(id));
    }

    /**
     * 新增商品类目管理
     */
    @RequiresPermissions("cms:category:add")
    @Log(title = "商品类目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbItemCat tbItemCat)
    {
        return toAjax(tbItemCatService.insertTbItemCat(tbItemCat));
    }

    /**
     * 修改商品类目管理
     */
    @RequiresPermissions("cms:category:edit")
    @Log(title = "商品类目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbItemCat tbItemCat)
    {
        return toAjax(tbItemCatService.updateTbItemCat(tbItemCat));
    }

    /**
     * 删除商品类目管理
     */
    @RequiresPermissions("cms:category:remove")
    @Log(title = "商品类目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbItemCatService.deleteTbItemCatByIds(ids));
    }
}
