package com.ruoyi.points.controller;



import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.points.domain.Compliance;
import com.ruoyi.points.service.IComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;

import java.util.List;

/**
 * 合规分Controller
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@RestController
@RequestMapping("/compliance")
public class ComplianceController extends BaseController
{
    @Autowired
    private IComplianceService complianceService;

    /**
     * 查询合规分列表
     */
    @RequiresPermissions("compliance:compliance:list")
    @GetMapping("/list")
    public TableDataInfo list(Compliance compliance)
    {
        startPage();
        List<Compliance> list = complianceService.selectComplianceList(compliance);
        return getDataTable(list);
    }

    /**
     * 导出合规分列表
     */
    @RequiresPermissions("compliance:compliance:export")
    @Log(title = "合规分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Compliance compliance)
    {
        List<Compliance> list = complianceService.selectComplianceList(compliance);
        ExcelUtil<Compliance> util = new ExcelUtil<Compliance>(Compliance.class);
        util.exportExcel(response, list, "合规分数据");
    }

    /**
     * 获取合规分详细信息
     */
    @RequiresPermissions("compliance:compliance:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(complianceService.selectComplianceById(id));
    }

    /**
     * 新增合规分
     */
    @RequiresPermissions("compliance:compliance:add")
    @Log(title = "合规分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Compliance compliance)
    {
        return toAjax(complianceService.insertCompliance(compliance));
    }

    /**
     * 修改合规分
     */
    @RequiresPermissions("compliance:compliance:edit")
    @Log(title = "合规分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Compliance compliance)
    {
        return toAjax(complianceService.updateCompliance(compliance));
    }

    /**
     * 删除合规分
     */
    @RequiresPermissions("compliance:compliance:remove")
    @Log(title = "合规分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(complianceService.deleteComplianceByIds(ids));
    }
}
