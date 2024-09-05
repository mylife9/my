package com.ruoyi.cms.controller;

import com.alipay.api.AlipayApiException;
import com.ruoyi.cms.config.Constants;
import com.ruoyi.cms.domain.OrderInfo;
import com.ruoyi.cms.service.IOrderInfoService;
import com.ruoyi.cms.utils.*;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单信息Controller
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@RestController
@RequestMapping("/info")
public class OrderInfoController extends BaseController
{
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * 查询订单信息列表
     */
    @RequiresPermissions("cms:info:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderInfo orderInfo)
    {
        startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        return getDataTable(list);
    }
    //根据手机号查询订单
    @GetMapping("/getbyinfo/{tel}")
    public AjaxResult getbyinfo(@PathVariable("tel") String tel)
    {
        OrderInfo orderInfo = orderInfoService.getselectOrderInfo(tel);
        System.out.println(orderInfo);
        return success(orderInfo);
    }
    //根据手机号修改订单状态为司机到达乘客起点
    @PostMapping("/updatstatus/{tel}")
    public AjaxResult updatstatus(@PathVariable("tel") String tel)
    {
        orderInfoService.updateOrderInfoStatus(tel);
        return success();
    }
    //根据手机号修改订单状态为乘客上车，司机开始行程
    @PostMapping("/updatstatusone/{tel}")
    public AjaxResult updatstatusone(@PathVariable("tel") String tel)
    {
        orderInfoService.updatstatusone(tel);
        return success();
    }

    //根据ID修改订单目的地
    @PostMapping("/updateInfodestination/{id}/{destination}")
    public AjaxResult updateInfodestination(@PathVariable("id") String id,@PathVariable("destination") String destination)
    {
        orderInfoService.updateInfo(id,destination);
        return success();
    }
    @PostMapping("/showDriverById3/{tel}")
    public AjaxResult showDriverById3(@PathVariable("tel") String tel)
    {
        orderInfoService.updateInfo3(tel);
        return success();
    }

    //微信支付
    @RequestMapping("/WxPay")
    public void   WxPay(HttpServletResponse response,String tel) throws Exception {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("mch_id", Constants.MCH_ID);
//        params.put("appid", "wx52bed64ca4a3ef89");
//        params.put("notify_url", Constants.NOTIFY_URL);
//        params.put("fee_type", Constants.FEE_TYPE);
//        params.put("device_info", Constants.DEVICE_INFO);
//        params.put("trade_type", Constants.TRADE_TYPE);
//        params.put("sign_type", Constants.SIGN_TYPE);
//        params.put("nonce_str", CommUtil.getNonce_str());
//        params.put("out_trade_no","489481565161894");
//        params.put("spbill_create_ip", "127.0.0.1");
//        params.put("body", "订单信息");
//        params.put("product_id", "489481565161894");
//        params.put("total_fee", "1");
//        //MD5(appid=xxxxx&body=bwie微信支付&........&密钥)---->sign
//        params.put("sign", CommUtil.generateSignature(params, Constants.API_KEY, "HMACSHA256"));
//        String requestXML = XMLUtil.mapToXml(params);
//        //将XML报文发送给微信支付服务器得到返回结果(XML)
//        String resultXML = HttpUtil.postData(Constants.UFDODER_URL, requestXML);
//        Map<String, String> result = XMLUtil.doXMLParse(resultXML);
//        result.put("sign",params.get("sign"));
//        //生成时间戳
//        long timestamp = System.currentTimeMillis() / 1000;
//        result.put("timestamp", String.valueOf(timestamp));
//        result.put("notifyurl", params.get("notifyurl"));
        OrderInfo  = orderInfoService.selectorderInfo(tel);
        //判断是否已经支付
        if (.getOrderStatus()==6){
            //保证幂等性
            String s1 = stringRedisTemplate.opsForValue().get("zf-" + tel);
            if (StringUtils.isEmpty(s1)){
                stringRedisTemplate.opsForValue().set("zf"+tel, "北伐");
                WxPayUtils wxPayUtils = new WxPayUtils();
                String s = wxPayUtils.wxPay(orderInfo1.getId());
                //修改订单状态为：发起收款
                orderInfoService.updateOrderStatusa(tel);
                QRCodeUtil.createQRCode(response,s);


            }
        }

    }

    @GetMapping("GetWxPay")
    public AjaxResult  GetWxPay( String tel) throws Exception {
        OrderInfo orderInfo1 = orderInfoService.selectorderInfoeight(tel);
        Boolean aBoolean = new WxPayUtils().refreshWxPay(orderInfo1.getId());
        //判断订单状态
        if (aBoolean){
            //就修改订单的状态为已支付
            orderInfoService.updateOrderStatus(tel);
            return  success();
        }
        return  error();
    }
    //支付宝接口
    @RequestMapping("/zfbzf")
    public  String  zfbzf(String tel) throws AlipayApiException {
        OrderInfo orderInfo1 = orderInfoService.selectorderInfo(tel);
        ZhifubaoUtill zhifubaoUtill = new ZhifubaoUtill();
        String s = zhifubaoUtill.pay(orderInfo1.getId(), "200", "马牛逼订单");
        return  s;
    }
    /**
     * 导出订单信息列表
     */
    @RequiresPermissions("cms:info:export")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderInfo orderInfo)
    {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        util.exportExcel(response, list, "订单信息数据");
    }

    /**
     * 获取订单信息详细信息
     */
    @RequiresPermissions("cms:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderInfoService.selectOrderInfoById(id));
    }

    /**
     * 新增订单信息
     */
    @RequiresPermissions("cms:info:add")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.insertOrderInfo(orderInfo));
    }

    /**
     * 修改订单信息
     */
    @RequiresPermissions("cms:info:edit")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.updateOrderInfo(orderInfo));
    }

    /**
     * 删除订单信息
     */
    @RequiresPermissions("cms:info:remove")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderInfoService.deleteOrderInfoByIds(ids));
    }
}
