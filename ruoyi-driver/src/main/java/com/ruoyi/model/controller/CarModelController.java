package com.ruoyi.model.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.brand.domain.CarBrand;
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
import com.ruoyi.model.domain.CarModel;
import com.ruoyi.model.service.ICarModelService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 车型管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@RestController
@RequestMapping("/model/model")
public class CarModelController extends BaseController {
    @Autowired
    private ICarModelService carModelService;

    /**
     * 查询车型管理列表
     */
    @RequiresPermissions("model:model:list")
    @GetMapping("/list")
    public TableDataInfo list(CarModel carModel) {
        startPage();
        List<CarModel> list = carModelService.selectCarModelList(carModel);
        return getDataTable(list);
    }

    /**
     * 导出车型管理列表
     */
    @RequiresPermissions("model:model:export")
    @Log(title = "车型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarModel carModel) {
        List<CarModel> list = carModelService.selectCarModelList(carModel);
        ExcelUtil<CarModel> util = new ExcelUtil<CarModel>(CarModel.class);
        util.exportExcel(response, list, "车型管理数据");
    }

    /**
     * 获取车型管理详细信息
     */
    @RequiresPermissions("model:model:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(carModelService.selectCarModelById(id));
    }

    /**
     * 新增车型管理
     */
    @RequiresPermissions("model:model:add")
    @Log(title = "车型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarModel carModel) {
        return toAjax(carModelService.insertCarModel(carModel));
    }

    /**
     * 修改车型管理
     */
    @RequiresPermissions("model:model:edit")
    @Log(title = "车型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarModel carModel) {
        return toAjax(carModelService.updateCarModel(carModel));
    }

    /**
     * 删除车型管理
     */
    @RequiresPermissions("model:model:remove")
    @Log(title = "车型管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(carModelService.deleteCarModelByIds(ids));
    }

    /**
     * 回显车辆品牌及名称
     */
    @RequiresPermissions("model:model:list")
    @GetMapping("/brandList")
    public TableDataInfo brandList() {
        List<CarBrand> list = carModelService.brandList();
        return getDataTable(list);
    }

}
