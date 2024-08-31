package com.ruoyi.cms.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/31 19:21
 * @注释
 */
public class WxpayRequest {

    private String appid;
    @XStreamAlias("mch_id")
    private  String mchId;

    @XStreamAlias("nonce_str")
    private  String nonceStr;

    private  String sign;

    @XStreamAlias("body")
    private  String body;

    @XStreamAlias("out_trade_no")
    private  String outTradeNo;


    @XStreamAlias("total_fee")
    private  Integer 	totalFee;

    @XStreamAlias("spbill_create_ip")
    private  String 	spbillCreateIp;

    @XStreamAlias("notify_url")
    private  String 	notifyUrl;
    @XStreamAlias("trade_type")
    private  String 	tradeType;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
