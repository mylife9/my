package com.ruoyi.cms.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ruoyi.cms.domain.TbBrand;
import com.ruoyi.cms.service.ITbBrandService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.Logical;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 品牌管理Controller
 * 
 * @author ruoyi
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/brand")
public class TbBrandController extends BaseController
{
    @Autowired
    private ITbBrandService tbBrandService;

    /**
     * 查询品牌管理列表
     */
    @RequiresPermissions("cms:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(TbBrand tbBrand)
    {
        startPage();
        List<TbBrand> list = tbBrandService.selectTbBrandList(tbBrand);
        return getDataTable(list);
    }

    @GetMapping("/get/{brandName}")
    public Map get(@PathVariable String brandName)
    {
        TbBrand tbBrand = tbBrandService.selectTbBrandByBrandName(brandName);
        Map map = new HashMap();
        map.put("brandName",tbBrand.getBrandName());
        map.put("url",tbBrand.getLogo());
        return map;
    }

    @RequiresPermissions("cms:brand:list")
    @GetMapping("/list/all")
    public TableDataInfo listAll()
    {
        List<TbBrand> list = tbBrandService.selectTbBrandList(null);
        return getDataTable(list);
    }

    /**
     * 导出品牌管理列表
     */
    @RequiresPermissions("cms:brand:export")
    @Log(title = "品牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbBrand tbBrand)
    {
        List<TbBrand> list = tbBrandService.selectTbBrandList(tbBrand);
        ExcelUtil<TbBrand> util = new ExcelUtil<TbBrand>(TbBrand.class);
        util.exportExcel(response, list, "品牌管理数据");
    }

    /**
     * 获取品牌管理详细信息
     */
    @RequiresPermissions("cms:brand:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbBrandService.selectTbBrandById(id));
    }

    /**
     * 新增品牌管理
     */
    @RequiresPermissions("cms:brand:add")
    @Log(title = "品牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbBrand tbBrand)
    {
        return toAjax(tbBrandService.insertTbBrand(tbBrand));
    }

    /**
     * 修改品牌管理
     */
    @RequiresPermissions(value = {"cms:brand:edit","cms:brand:approval"},logical = Logical.OR)
    @Log(title = "品牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbBrand tbBrand)
    {
        return toAjax(tbBrandService.updateTbBrand(tbBrand));
    }

    /**
     * 删除品牌管理
     */
    @RequiresPermissions("cms:brand:remove")
    @Log(title = "品牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbBrandService.deleteTbBrandByIds(ids));
    }


    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws com.aliyuncs.exceptions.ClientException {
        String uuid = UUID.randomUUID().toString();

        //获取文件拓展名
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf("."));

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        DefaultCredentialProvider credentialsProvider =
                CredentialsProviderFactory.newDefaultCredentialProvider("","");
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "brand2112";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "img/" + uuid + extName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        Object path = "https://brand2112.oss-cn-beijing.aliyuncs.com/"+objectName;

        return success(path);
    }

}
