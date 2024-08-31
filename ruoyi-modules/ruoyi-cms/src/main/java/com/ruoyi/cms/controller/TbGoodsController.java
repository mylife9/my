package com.ruoyi.cms.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ruoyi.cms.domain.GoodsEntity;
import com.ruoyi.cms.domain.TbGoods;
import com.ruoyi.cms.domain.TbItem;
import com.ruoyi.cms.service.ITbGoodsSerivce;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
public class TbGoodsController extends BaseController {

    @Autowired
    private ITbGoodsSerivce goodsSerivce;
    @Autowired
   private StringRedisTemplate stringRedisTemplate;

    @RequiresPermissions("cms:goods:add")
    @Log(title = "商品发布管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsEntity goodsEntity)
    {
        goodsSerivce.save(goodsEntity);
        return success();
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
        String objectName = "goods/" + uuid + extName;

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

    @GetMapping("/listAll")
    public List<Map> list(){
        return goodsSerivce.listAllItem();
    }

    @GetMapping("/listByTime")
    public List<Map> listByTime(String time){
        return goodsSerivce.listByTime(time);
    }

    @GetMapping("genPage/{goodsId}")
    public  AjaxResult genPage(@PathVariable Long goodsId) throws TemplateException, IOException {
        goodsSerivce.genPage(goodsId);
        return  success();
    }
    @GetMapping("genPage")
    public  AjaxResult genPage() throws TemplateException, IOException {
       List<TbGoods> list=goodsSerivce.findAllGoods();
        for (TbGoods goods : list) {
            goodsSerivce.genPage(goods.getId());
        }
        return  success();
    }
    @GetMapping("/find/{id}")
    public  AjaxResult find(@PathVariable Long id){
        Map<Object, Object> cache = stringRedisTemplate.opsForHash().entries("goods" + id);
        if (cache!=null && !cache.isEmpty()){
            //查到数据了
            cache.remove("lockNum");
            return  success(cache);
        }
        //没有查到数据库
      TbItem item=goodsSerivce.findItemById(id);
        cache=new HashMap<>();
        cache.put("price",item.getPrice().intValue()+"");
        cache.put("saleNum",item.getNum()+"");
        cache.put("lockNum","0");

        stringRedisTemplate.opsForHash().putAll("goods:"+id,cache);
        cache.remove("lockNum");

        return  success(cache);
    }
}
