package com.ruoyi.dirver.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.dirver.domain.DrivingLicense;
import com.ruoyi.dirver.service.DrivingLicenseService;
import com.ruoyi.dirver.utils.OSSFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

import static com.ruoyi.common.core.web.domain.AjaxResult.error;
import static com.ruoyi.common.core.web.domain.AjaxResult.success;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/22 21:48
 */
@RestController
@RequestMapping("/driving")
public class DrivingLicenseController {
    @Autowired
    private DrivingLicenseService drivingLicenseService;
    @Autowired
    private OSSFileUtil ossFileUtil;

    /**
     * 司机行驶证信息添加
     * @param driving
     * @return
     */
    @PostMapping("/saveDriving")
    public AjaxResult saveDriving(@RequestBody DrivingLicense driving){
        Integer integer = drivingLicenseService.save(driving);
        return integer>0?success():error();
    }

    /**
     * 图片上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        String s = ossFileUtil.uploadFile(file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("url",s);
        return success(map);
    }
}
