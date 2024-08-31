package com.ruoyi.cms.utils;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.stereotype.Component;

@Component
public class ZhifubaoUtill {

    String privateKey = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCGKMlDI36sR9Qso8bFjsAg00hvRfz/YqPM0FqsVP12A1sMZ2idgoGGHVVqsl0bNrxQNUMcxoNyS+20HZxsQACSw6uqWEXwPWt0Cx3jO1wdTp7IxSfuSgJVdWXPaAK/pF+KF8q67z8L1cdo3CGCwdVhjMN/RKMzeTwik5KwqSz1n33meQqw6N1EUuDmqUdkDIzDDbiiiFX6aKHTeH0VZhoeMRbScY7ErLph9SsvKE0JIy+mG/V7nwQzIP1eJGJFFgdOdhP2j6RDU6P94M9VfvDqeHDs6A2fl/2AAWTONlo0q5qSnagsfFx0PmbHET0fJaIg7DERySUPA3fmPF8OOmRLAgMBAAECggEAGmtpxwj0ZyT2CZxvBnSoLU6uUwtrWTepninh0VIjX9wlT/oFRg9vRmP8cDiT0UokJ391qfMF67o8I145ca/g7QYPhko/66GMyZtu1VLZPwQ+jLcWt/xnHUEzPXb0I51RGza4O7vlpIUC8gl3p/JX/Q/lZ+iyYu6bAwwgtR17txrZbH2ncv2g3Z+S4sFiwR0qRyMJ3SV7KSn1t0exxnpCJJTNvxecMyVsashc8EeUilbJx0PrE08wrxTzDwMqhVoSBRWQlLINNkAMjWy8KeX5pquS0WCXznSlO654Y6pNCEBixN+vIzBGpORcajfFX7LtFq/qaZkrUj8/B70SiIGDsQKBgQDckpsyke7LB1Kf/60bDXefXTzb6k7CbF+0/s0A0AhY/HMgYJvj4tp1hGpcwKIcfhfXI4CG5nSSEgpRfhggBJZMvvVdV31uD6FG1XXz1iJnqaXEqfJm82OoWZbxwrMkwlAay8klvAbebU87ATEH2ls/p00ltNyn3Cggtylg/QTcpQKBgQCbtRR5GFSQkSzVi3UkJ+4AeUS5Avqclk3RJ7bFV6dzowM4+c0xHYjOXNUd112W5arSni9SCNew9qW5Uxc3pj6ZxN6A3IJ72+seK4aitw61rCZPfaU2qMiheLNI6UMqSb7Q9Y3auw2ObYtfDoK15OYPSquE4ucTU7uycsqWLQ66LwJ/IL2iS99dksWDfjx1dY6KMx2j6wDcr/9mNXec9oHqu0q9zBCLE0iZJNiC8UGmzpPo9gZPDbcnlocXAQG2kwobRUkTIsfZ1nx7dAg7efZPJRQJhyey05VNNbAWt8wuy0YMne2h7ff7DQksuZVzsba31LsAuJggKMqFKlYFzl/AWQKBgA+VVOI1tCjvzpvkpCmlMWWsvVsC2FAZntoi7mDbv++LzgPCxG149jspAU9GQaCbhbgkirncFrIoTTe6ACXapoM/N8GP0CNTRDm1w3i/d2JpQ0BLY+CPjdzTNqZCai1uSgGhwbu1YsrxXbR6wEwyB3BaWcYn61Z819kpMetLwSCTAoGBALJhC1LnemyJf2OelcgP7BRSRv1bmdDOArYgjX+COwsPnTY6cjCI0YUPR34Pr32Nn7i0q4BHndJEcBven0/m9uDEro6wwYwi/W0iskW4rOz+wMLgFTjcJA3L3Mm1Gv5H83KshW1VcxqeiqtFCKABe4c4liwRBqTE/MYwb1WZ3pq+";
    String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgCOkwboYS9yBvgdMZDMe9ghs7WOJJ5b6P+Z+qyJnUizOMJTrdq1fbxlYCsc2B89wYfchduMkaM1vsCppjM7P/vkLIeu0mwk12JEttSb5rO+EKafEIIrB1KEFbk3W+rrUpwCWSkzGunXyHQ1lN7EQrMxZ6QmBoxJiqiHfFWoFb69PUZdfEaMeLklkUJh+3rXiwA+cPZoM/RVCIjkpT5iu+QRIKFzuMymDZET4Ypk+0iozhJzOIa1h6VSf3WVS6+e1n7LoaD+2AUn6Y1M8l8z0vHTkEnrrPWxAEzMARPkfl/TWcsa9bgoE43Ebm2g4JWfvP3dugAiYM0vzFlUj6i9KbwIDAQAB";
    //生成支付订单接口
    public String pay(String Code,String Money,String Name) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000139674410");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(Code);
        model.setTotalAmount(Money);
        model.setSubject(Name);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());
       return response.getBody();
    }


    //查询接口
    public Boolean queryResult(String tradeNo) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000139674410");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(tradeNo);
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        return response.isSuccess();
    }
}
