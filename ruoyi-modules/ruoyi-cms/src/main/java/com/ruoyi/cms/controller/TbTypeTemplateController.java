package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.TbTypeTemplate;
import com.ruoyi.cms.service.ITbTypeTemplateService;
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
import java.util.List;

/**
 * 模版管理Controller
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@RestController
@RequestMapping("/template")
public class TbTypeTemplateController extends BaseController
{
    @Autowired
    private ITbTypeTemplateService tbTypeTemplateService;

    /**
     * 查询模版管理列表
     */
    @RequiresPermissions("cms:template:list")
    @GetMapping("/list")
    public TableDataInfo list(TbTypeTemplate tbTypeTemplate)
    {
        startPage();
        List<TbTypeTemplate> list = tbTypeTemplateService.selectTbTypeTemplateList(tbTypeTemplate);
        return getDataTable(list);
    }


    @RequiresPermissions("cms:template:list")
    @GetMapping("/list/all")
    public TableDataInfo listAll()
    {
        List<TbTypeTemplate> list = tbTypeTemplateService.selectTbTypeTemplateList(null);
        return getDataTable(list);
    }

    /**
     * 导出模版管理列表
     */
    @RequiresPermissions("cms:template:export")
    @Log(title = "模版管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbTypeTemplate tbTypeTemplate)
    {
        List<TbTypeTemplate> list = tbTypeTemplateService.selectTbTypeTemplateList(tbTypeTemplate);
        ExcelUtil<TbTypeTemplate> util = new ExcelUtil<TbTypeTemplate>(TbTypeTemplate.class);
        util.exportExcel(response, list, "模版管理数据");
    }

    /**
     * 获取模版管理详细信息
     */
    //@RequiresPermissions("cms:template:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbTypeTemplateService.selectTbTypeTemplateById(id));
    }

    /**
     * 新增模版管理
     */
    @RequiresPermissions("cms:template:add")
    @Log(title = "模版管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbTypeTemplate tbTypeTemplate)
    {
        return toAjax(tbTypeTemplateService.insertTbTypeTemplate(tbTypeTemplate));
    }

    /**
     * 修改模版管理
     */
    @RequiresPermissions("cms:template:edit")
    @Log(title = "模版管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbTypeTemplate tbTypeTemplate)
    {
        return toAjax(tbTypeTemplateService.updateTbTypeTemplate(tbTypeTemplate));
    }

    /**
     * 删除模版管理
     */
    @RequiresPermissions("cms:template:remove")
    @Log(title = "模版管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbTypeTemplateService.deleteTbTypeTemplateByIds(ids));
    }
}
