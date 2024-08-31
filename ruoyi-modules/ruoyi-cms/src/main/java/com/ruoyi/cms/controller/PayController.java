package com.ruoyi.cms.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ruoyi.cms.config.AlipayConfig;
import com.ruoyi.cms.config.Constants;
import com.ruoyi.cms.domain.Order;
import com.ruoyi.cms.domain.WxpayRequest;
import com.ruoyi.cms.service.impl.OrderServiceImpl;
import com.ruoyi.cms.utils.QRCodeUtil;
import com.ruoyi.cms.utils.WxPayUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/31 17:11
 * @注释
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    WxPayUtils wxPayUtils;
    @Autowired
    private OrderServiceImpl orderService;
    @GetMapping("/wxpay")
    public  void wxpay(String id,HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, IOException, DocumentException {
        System.out.println("收到支付请求"+id);
        //根据ID查询出订单金额
        Order order=orderService.findOederById(id);

        //将订单信息发送给微信支付平台
        String url="https://api.mch.weixin.qq.com/pay/unifiedorder";
        //1.封装请求报文
        WxpayRequest wxpayRequest = new WxpayRequest();
        wxpayRequest.setAppid(Constants.APP_ID);
        wxpayRequest.setMchId(Constants.MCH_ID);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        wxpayRequest.setNonceStr(uuid);
        wxpayRequest.setBody("拼夕夕商城订单");
        wxpayRequest.setOutTradeNo("pxx"+id);
        wxpayRequest.setTotalFee(1);

        String ip = request.getRemoteAddr();
        wxpayRequest.setSpbillCreateIp(ip);

        wxpayRequest.setNotifyUrl("http://pqwxpay.free.idcfengye.com/pay/wxresult");
        wxpayRequest.setTradeType("NATIVE");

        Map  map = new HashMap<>();

        //生成签名
        Class<? extends WxpayRequest> aClass = wxpayRequest.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            XStreamAlias annotation = field.getAnnotation(XStreamAlias.class);
            field.setAccessible(true);
            if (annotation==null){
                map.put(field.getName(),field.get(wxpayRequest));
            }else {
                map.put(annotation.value(),field.get(wxpayRequest));
            }
        }
        map.remove("sign");
        Set<String> set = map.keySet();
        List<String> keyList = new ArrayList(set);
        Collections.sort(keyList);


        String signStr="";
        for (String  key : keyList) {
            signStr+=key+"="+map.get(key)+"&";
        }
        signStr+="key="+Constants.API_KEY;
        String sign = DigestUtils.md5Hex(signStr).toUpperCase();

        wxpayRequest.setSign(sign);

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.alias("xml", WxpayRequest.class);
        String requestXML = xStream.toXML(wxpayRequest);

        requestXML = requestXML.replaceAll("__", "_");

        //2.发送报文并接受结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        HttpEntity requesEntity=new StringEntity(requestXML,"UTF-8");
        httpPost.setEntity(requesEntity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        String responsXMl = EntityUtils.toString(entity,"UTF-8");
        System.out.println(responsXMl);
        //3.解析报文
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(responsXMl.getBytes()));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        //从返回的信息中去除codeUrl
        String codeUrl="";
        for (Element element : elements) {
            if (element.getName().equals("code_url")){
                codeUrl=element.getStringValue();
            }
        }
        System.out.println(codeUrl);

        //将codeUrl转成二维码返回给前端
        QRCodeUtil.createQRCode(response,codeUrl);


    }
    @RequestMapping("/wxresult")
    public String  wxresult(HttpServletRequest request) throws IOException, DocumentException {
        System.out.println("收到支付结果。。。。");
        ServletInputStream inputStream=request.getInputStream();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        //从返回信息中取出codeUrl
        Map map=new HashMap();
        for (Element element : elements) {
            map.put(element.getName(),element.getStringValue());
            System.out.println(element.getName()+"---"+element.getStringValue());
        }
        //验签
       String sign=(String) map.remove("sign");
        Set<String> set = map.keySet();
        List<String> keyList = new ArrayList(set);
        Collections.sort(keyList);


        String signStr="";
        for (String  key : keyList) {
            signStr+=key+"="+map.get(key)+"&";
        }
        signStr+="key="+Constants.API_KEY;
        String sign2 = DigestUtils.md5Hex(signStr).toUpperCase();

        if(!sign2.equals(sign)){
            System.out.println("验签失败!");
            return  "FAIL";
        }
        System.out.println("验签成功");

        //修改订单状态1.根据订单编号查询订单表,判断支付金额和订单金额是否一致

        //2.修改订单状态为已支付

        //3.扣减锁定库存
        return  "SUCCESS";
    }

    @RequestMapping ("/alipay")
    @ResponseBody
    public  String alipay(HttpServletRequest request) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no =request.getParameter("id");
        //付款金额，必填
        String total_amount = "5000";
        //订单名称，必填
        String subject = "悠悠有品";
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        return result;
    }

    @RequestMapping("/alipayReturn")
    public  String  alipayReturn(HttpServletRequest request) throws AlipayApiException {
        System.out.println("收到同步回调");
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //交易状态
            String trade_status = request.getParameter("trade_status");

            return "redirect:http://localhost/paysuccess.html";

        }else {
            return "success";
        }
    }
    @RequestMapping("/alipayNotify")
    @ResponseBody
    public  String  alipayNotify(HttpServletRequest request) throws AlipayApiException {
        System.out.println("收到异步回调");
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //交易状态
            String trade_status = request.getParameter("trade_status");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

           return "success";

        }else {//验证失败
            return "fail";
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

    }


}
