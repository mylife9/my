package com.ruoyi.cms.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000139657110";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCU/sKUZ60mTZLghqmRgZBvTJuU/lutZu+kUUG3088TQJ0f0Z/9RXxZ9MHfNnkK2rQtLcUZU/Q5kCU4p1QakQDvr20cvLCX+O29Xx0KhKn1QiarStIWEnIMRrQmhEd089x/QqibgWc+BxG4ZIVzMq4g4FSV8LFHBMTSaeLOoABinrk2azubtcixVnp/ihVuyDvQ2exN3bCyt8K5L6bxHIisyngVae9Q5hOAoip5Ar2rHC7yUex4w+LII+Gvtub5QagtU4JGxcZdePW7WLxio6GoAMz1cUrZo/A46yIOE9w1pBXMevXpop9hfSaz8DDhZUpBcE8JPEysWZDyOVBLYYObAgMBAAECggEAST3SuNEeNK39fZG02WovbgbilCNVPbBULRokjBD6wiQJGRMa6o21yyKeCvPkHInV+D9/nCvA6msJYQggN87//tLleDLpPEuJ0oVm6Ty9r2qNR7bH/0Gh/pbjB+wOlFcsFQLXj1nVPpCLDXGciDxpjI+6BNsEyjx15FlYvOm6Rr+pY7/XxCZwbQxAcZJxoKqJg8Hh45Dsou3pp43ZO5xHYGSDCYbFATzC1VgPGKhP+SHE/nQpKoz3KyezuHBklJ2ZBEBwb3NQjH0nQsooiDDREamxrdGxoKlHahOWtnMUARjMRT6k4bAwXkpCykcUVf1v3mT1hFY+uAoK2gb40+PxwQKBgQD7rr8s087p56GKM6s4+VlcR5NpRPi8H5hVBtFt07ZIz+UejFUal35vqFv6bI2wz6EWEEa22E1Cj6UCZpVfOiZCQkjxWWAedqtC31hDMfMBclGY/gmMO3fioEM9BD4AXiGpC5BRymsEi06DwAdpLOju+ENtAEs+H5fsg2fd37cIfQKBgQCXjRDabidsA8X+3xPq3VaiphwQsOPPOluHt6X9B1wueIzU8TI2XnwDkNxvGvTCVz1SON9ZPJu8054YLjUyhY7clx26L4tqv3issYZ0VxN38xqMNOpCkoP4l1xNeB3ddiTu1c7oRG3xDQXhBoVNe0ID+PlcBGXwfpL1U4gghL0P9wKBgQDBJQvFZQ6nZoELxDckVAcF0FAKO0n/ZOfMaJ0UAaMkIZcdaGhvLc9z9OoRNnlONSjT+82oyDoSWJYzT3JWUBPISYIc5Y/kEQHBIhFkD9MDSy3fkSTWn1STcJXpH3ZNZe/DAQLSIdsa86VfamDFkBd6PL/RyKX1mN1IPlfZZ7PZKQKBgHNdpmOxEP9vy+xeIspaXdYI/zpRfcnFkeG5knkQfkK97+82ANRUjrEkODm32nSKdpq9woAq3B8dNK8Rldt0wjD4NGAES2wif5lutnyfwKlB/YaXzXCKGj5F8rTiaFGq2yiYyB4V4V9rW3tWPGmkF7fwctpyWqt9nx9zzB99DHM3AoGBAPH+8DjCNg9VZwJJ0+CgncXsTv4oVjEjxlPlTM14SroL1Plw4uOedQiDmuMb8UHKNTNMuRYpvhKFmTi/vQcri/GDm1VtqharUuLRc/1BFClG0pPWmUCRrqIM3PHIeUe/vASNxS/Qik1gAoU0gpX3CGDiwwJvHE3VLh4lRZ/QxvhQ";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhYqVeCjfHBlBd1CEm99FRSOvtNbiO0pYs2s/HWTQwKWNX+eO/ldLTdBlXM6XcpAeW3m+untQhV14hvEhSfyCAIfuQ9TSt0WRruyPJ0nzu6vmmL0+ZA+PoccsI2CdbfOiV8p9aMjkNLYYny8gP+vMxg5V4kO6e3i723t6hNWqQSXKyz23XN/XfOfD7/v0MhzksMfU4AZ/yoT304sAI/UC/GOqmOPUzC/XenLytTz0G4gc3pREg2opl33Cfs0BGaUrE6fAij+vFV/8GrGa+3xaj486/X7bLS9Vpr0r42GqGKa7dkEaRn5/vkFG25L8jDCsJsZvY0H4iwTAFyHVW5QAVwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://pqwxpay.free.idcfengye.com/pay/alipayNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:9301/pay/alipayReturn";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

