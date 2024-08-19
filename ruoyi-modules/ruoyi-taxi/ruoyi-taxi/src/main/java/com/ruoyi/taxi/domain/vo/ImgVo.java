package com.ruoyi.taxi.domain.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-19 15:38
 */
@Data
@ToString
public class ImgVo {
    private String appid;
    private Long timestamp;
    private String sign;
    private String image;
}
