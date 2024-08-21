package com.ruoyi.dirver.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.lang.Validator;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.dirver.domain.Driver;
import com.ruoyi.dirver.domain.TbUser;
import com.ruoyi.dirver.service.ITbUserService;
import com.ruoyi.dirver.utils.OSSFileUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 司机登录Controller
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/user")
public class TbUserController extends BaseController
{
    @Autowired
    private ITbUserService tbUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    OSSFileUtil ossFileUtil;

    /**
     * 查询司机登录列表
     */
    @RequiresPermissions("dirver:user:list")
    @GetMapping("/list")
    public TableDataInfo list(TbUser tbUser)
    {
        startPage();
        List<TbUser> list = tbUserService.selectTbUserList(tbUser);
        return getDataTable(list);
    }

    /**
     * 导出司机登录列表
     */
    @RequiresPermissions("dirver:user:export")
    @Log(title = "司机登录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbUser tbUser)
    {
        List<TbUser> list = tbUserService.selectTbUserList(tbUser);
        ExcelUtil<TbUser> util = new ExcelUtil<TbUser>(TbUser.class);
        util.exportExcel(response, list, "司机登录数据");
    }

    /**
     * 获取司机登录详细信息
     */
    @RequiresPermissions("dirver:user:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbUserService.selectTbUserById(id));
    }

    /**
     * 新增司机登录
     */
    @RequiresPermissions("dirver:user:add")
    @Log(title = "司机登录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbUser tbUser)
    {
        return toAjax(tbUserService.insertTbUser(tbUser));
    }

    /**
     * 修改司机登录
     */
    @RequiresPermissions("dirver:user:edit")
    @Log(title = "司机登录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbUser tbUser)
    {
        return toAjax(tbUserService.updateTbUser(tbUser));
    }

    /**
     * 删除司机登录
     */
    @RequiresPermissions("dirver:user:remove")
    @Log(title = "司机登录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbUserService.deleteTbUserByIds(ids));
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/UserLogin")
    public AjaxResult UserLogin(@RequestBody TbUser user) {
        TbUser tbUser = tbUserService.findUser(user);
        if (ObjectUtils.isEmpty(tbUser)) {
            return error("账号或密码错误");
        }

        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, "ruoyi")
                .claim("id", tbUser.getId())
                .claim("username", tbUser.getUsername())
                .compact();

        stringRedisTemplate.opsForValue().set("TOKEN" + tbUser.getId(), token, 30, TimeUnit.MINUTES);

        return success("登录成功" + token);
    }

    @PostMapping("/sendCode/{phone}")
    public AjaxResult sendCode(@PathVariable(name = "phone")String phone){
        if(StringUtils.isEmpty(phone)){
            return AjaxResult.error("手机号不能为空");
        }

        Random random = new Random();
        int num=1000+random.nextInt(9000);
        String code=""+num;

        String codeKey="code"+phone;
        stringRedisTemplate.opsForValue().set(codeKey,code,5,TimeUnit.MINUTES);

        return AjaxResult.success("验证码发送成功"+code);
    }

    @PostMapping("/regUser/{phone}/{code}")
    public AjaxResult register(@PathVariable(name = "phone")String phone,
                               @PathVariable(name = "code")String code){
        String rediscode = stringRedisTemplate.opsForValue().get("code" + phone);
        if(!Validator.isMobile(phone)){
            return AjaxResult.error("手机号格式错误");
        }

        if(rediscode.isEmpty()){
            return AjaxResult.error("手机号不存在");
        }

        if(!rediscode.equals(code)){
            return AjaxResult.error("验证码错误");
        }

        TbUser tbUser = tbUserService.UserPhone(phone);
        String token = Jwts.builder().setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "bwie")
                .claim("id", tbUser.getId())
                .claim("phone", tbUser.getPhone())
                .compact();

        stringRedisTemplate.opsForValue().set("TOKEN"+tbUser.getId(),token,15,TimeUnit.MINUTES);

        return AjaxResult.success("登录成功"+token);
    }

    @PostMapping("/saveDriver")
    public AjaxResult saveDriver(@RequestBody Driver driver){
        Integer i = tbUserService.saveDriver(driver);
        return i>0?success():error();
    }

    @PostMapping("upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        String s = ossFileUtil.uploadFile(file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("url",s);
        return success(map);
    }



}
