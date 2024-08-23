package com.ruoyi.taxi.utils;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.taxi.config.ShuMaiConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-21 16:04
 */
@Component
public class OcrUtil {
    @Autowired
    OSSFileUtil ossFileUtil;

    /**
     * @Description: 身份证ocr识别
     * @Author: yang
     * @Date: 2024/8/21 16:06
     * @param file:
     * @return: java.lang.String
     */
    public Map ocrIdCard(MultipartFile file) throws IOException {
        long timestamp = System.currentTimeMillis();
        String signStr = ShuMaiConfig.appid+"&"+timestamp+"&"+ShuMaiConfig.appSecurity;
        String sign = DigestUtils.md5Hex(signStr.getBytes());

        String ossUrl = ossFileUtil.uploadFile(file);

        String url = ShuMaiConfig.idcarOcrUrl+
                "?appid="+ShuMaiConfig.appid+
                "&timestamp="+timestamp+
                "&sign="+sign+
                "&url="+ossUrl;

        String result = HttpClientUtil.post(url, null,null);
        Map map = new HashMap<String,Object>();

        map.put("url",ossUrl);
        map.put("result",result);

        return map;
    }

    /**
     * @Description:驾驶证ocr识别
     * @Author: yang
     * @Date: 2024/8/21 16:10
     * @param file:
     * @return: java.lang.String
     */
    public Map ocrDriver(MultipartFile file) throws IOException {
        long timestamp = System.currentTimeMillis();
        String signStr = ShuMaiConfig.appid+"&"+timestamp+"&"+ShuMaiConfig.appSecurity;
        String sign = DigestUtils.md5Hex(signStr.getBytes());

        String ossUrl = ossFileUtil.uploadFile(file);

        String url = ShuMaiConfig.driverOcrUrl+
                "?appid="+ShuMaiConfig.appid+
                "&timestamp="+timestamp+
                "&sign="+sign+
                "&url="+ossUrl;

        String result = HttpClientUtil.post(url, null,null);
        Map map = new HashMap<String,Object>();

        map.put("url",ossUrl);
        map.put("result",result);

        return map;
    }

    /**
     * @Description: 人脸识别
     * @Author: yang
     * @Date: 2024/8/21 16:10
     * @param file:
     * @return: java.lang.String
     *
     */
    public Map ocrFace(String videoUrl) throws IOException {
        long timestamp = System.currentTimeMillis();
        String signStr = ShuMaiConfig.appid+"&"+timestamp+"&"+ShuMaiConfig.appSecurity;
        String sign = DigestUtils.md5Hex(signStr.getBytes());

        String url = ShuMaiConfig.faceOcrUrl+
                "?appid="+ShuMaiConfig.appid+
                "&timestamp="+timestamp+
                "&sign="+sign+
                "&motions=MOUTH"+
                "&url="+videoUrl;

        String result = HttpClientUtil.post(url, null,null);

        System.out.println(result);

        JSONObject jsonObject = JSONObject.parseObject(result);
        Map data = JSONObject.parseObject(jsonObject.get("data").toString(), Map.class);

        Map map = new HashMap<String,Object>();
        map.put("result",data);


        return map;
    }
}
