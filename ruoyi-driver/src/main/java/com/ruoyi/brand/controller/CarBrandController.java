package com.ruoyi.brand.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.brand.domain.CarBrand;
import com.ruoyi.brand.service.ICarBrandService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 品牌管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-18
 */
@RestController
@RequestMapping("/brand/brand")
public class CarBrandController extends BaseController
{
    @Autowired
    private ICarBrandService carBrandService;

    /**
     * 查询品牌管理列表
     */
    @RequiresPermissions("brand:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(CarBrand carBrand)
    {
        startPage();
        List<CarBrand> list = carBrandService.selectCarBrandList(carBrand);
        return getDataTable(list);
    }

    /***
     *@Description: 查询一级品牌
     * @return com.ruoyi.common.core.web.page.TableDataInfo
     * @author FFwy
     *@date 2024/8/21 11:48
     *
     */
    @RequiresPermissions("brand:brand:getBrandOne")
    @GetMapping("/getBrandOne")
    public TableDataInfo getBrandOne()
    {
        List<CarBrand> list = carBrandService.selectBrandOne();
        return getDataTable(list);
    }

    /**
     * 导出品牌管理列表
     */
    @RequiresPermissions("brand:brand:export")
    @Log(title = "品牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarBrand carBrand)
    {
        List<CarBrand> list = carBrandService.selectCarBrandList(carBrand);
        ExcelUtil<CarBrand> util = new ExcelUtil<CarBrand>(CarBrand.class);
        util.exportExcel(response, list, "品牌管理数据");
    }

    /**
     * 获取品牌管理详细信息
     */
    @RequiresPermissions("brand:brand:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carBrandService.selectCarBrandById(id));
    }

    /**
     * 新增品牌管理
     */
    @RequiresPermissions("brand:brand:add")
    @Log(title = "品牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarBrand carBrand)
    {
        return toAjax(carBrandService.insertCarBrand(carBrand));
    }

    /**
     * 修改品牌管理
     */
    @RequiresPermissions("brand:brand:edit")
    @Log(title = "品牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarBrand carBrand)
    {
        return toAjax(carBrandService.updateCarBrand(carBrand));
    }

    /**
     * 删除品牌管理
     */
    @RequiresPermissions("brand:brand:remove")
    @Log(title = "品牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carBrandService.deleteCarBrandByIds(ids));
    }
}
