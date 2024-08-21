package com.ruoyi.driver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.sign.Base64;
import com.ruoyi.driver.config.HttpClientUtil;
import com.ruoyi.driver.config.ShuMaiConfig;
import com.ruoyi.driver.domain.DicDistrict;
import com.ruoyi.driver.service.DicDistrictService;
import com.ruoyi.driver.service.PinyinService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
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
import com.ruoyi.driver.domain.Car;
import com.ruoyi.driver.service.ICarService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 车辆Controller
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/driver/driver")
public class CarController extends BaseController
{
    @Autowired
    private ICarService carService;

    @Autowired
    PinyinService pinyinService;

    @Autowired
    DicDistrictService dicDistrictService;

    /**
     * 查询车辆列表
     */
    @RequiresPermissions("driver:driver:list")
    @GetMapping("/list")
    public TableDataInfo list(Car car)
    {
        startPage();
        List<Car> list = carService.selectCarList(car);
        return getDataTable(list);
    }

    /**
     * 导出车辆列表
     */
    @RequiresPermissions("driver:driver:export")
    @Log(title = "车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Car car)
    {
        List<Car> list = carService.selectCarList(car);
        ExcelUtil<Car> util = new ExcelUtil<Car>(Car.class);
        util.exportExcel(response, list, "车辆数据");
    }

    /**
     * 获取车辆详细信息
     */
    @RequiresPermissions("driver:driver:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carService.selectCarById(id));
    }

    /**
     * 新增车辆
     */
    @RequiresPermissions("driver:driver:add")
    @Log(title = "车辆", businessType = BusinessType.INSERT)
    @RequestMapping
    public AjaxResult add(@RequestBody Car car)
    {

        if (!StringUtils.isEmpty(car.getModel())) {
            String pinyinCar = pinyinService.convertToPinyin(car.getModel());
            car.setModel(pinyinCar);
        }

        return toAjax(carService.insertCar(car));
    }

    /**
     * 修改车辆
     */
    @RequiresPermissions("driver:driver:edit")
    @Log(title = "车辆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Car car)
    {
        if (!StringUtils.isEmpty(car.getModel())) {
            String pinyinCar = pinyinService.convertToPinyin(car.getModel());
            car.setModel(pinyinCar);
        }
        return toAjax(carService.updateCar(car));
    }

    /**
     * 删除车辆
     */
    @RequiresPermissions("driver:driver:remove")
    @Log(title = "车辆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carService.deleteCarByIds(ids));
    }

    @RequestMapping("/ocr")
    public void ocr(@RequestBody MultipartFile file){
        String encode = null;

        try {
          encode = Base64.encode(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long l = System.currentTimeMillis();
        String sign = ShuMaiConfig.appid+"&"+l+"&"+ShuMaiConfig.appSecurity;
        String s = Md5Crypt.md5Crypt(sign.getBytes());

        System.out.println(s);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appid",ShuMaiConfig.appid);
        jsonObject.put("timestamp",l);
        jsonObject.put("sign",sign);
        jsonObject.put("imageUrl",encode);
        String post = HttpClientUtil.post(ShuMaiConfig.idcarOcrUrl, null, jsonObject.toString());
        System.out.println(post);
    }
   /* @RequiresPermissions("driver:driver:list")*/
    @GetMapping("/dicList")
    public TableDataInfo dicList(){
        List<DicDistrict> dicDistricts=dicDistrictService.selectDic();


        HashMap<String, DicDistrict> map = new HashMap<>();
        for (DicDistrict dicDistrict : dicDistricts) {
            map.put(dicDistrict.getAddressCode(),dicDistrict);
        }

        List arrayList = new ArrayList();
        for (DicDistrict dicDistrict : dicDistricts) {
            if (dicDistrict.getParentAddressCode().equals("0")){
                arrayList.add(dicDistrict);
            }else if (dicDistrict.getParentAddressCode().equals("100000")){
                arrayList.add(dicDistrict);
            }else {
                String addressCode = dicDistrict.getParentAddressCode();
                DicDistrict district = map.get(addressCode);
                List<DicDistrict> children = district.getChildren();
                if (children==null){
                    children=new ArrayList<>();
                    district.setChildren(children);
                }
                children.add(dicDistrict);
            }

        }
        return getDataTable(arrayList);
    }
}
