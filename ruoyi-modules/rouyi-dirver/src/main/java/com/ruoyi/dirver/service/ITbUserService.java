package com.ruoyi.dirver.service;

import java.util.List;

import com.ruoyi.dirver.domain.Driver;
import com.ruoyi.dirver.domain.TbUser;

/**
 * 司机登录Service接口
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public interface ITbUserService 
{
    /**
     * 查询司机登录
     * 
     * @param id 司机登录主键
     * @return 司机登录
     */
    public TbUser selectTbUserById(Long id);

    /**
     * 查询司机登录列表
     * 
     * @param tbUser 司机登录
     * @return 司机登录集合
     */
    public List<TbUser> selectTbUserList(TbUser tbUser);

    /**
     * 新增司机登录
     * 
     * @param tbUser 司机登录
     * @return 结果
     */
    public int insertTbUser(TbUser tbUser);

    /**
     * 修改司机登录
     * 
     * @param tbUser 司机登录
     * @return 结果
     */
    public int updateTbUser(TbUser tbUser);

    /**
     * 批量删除司机登录
     * 
     * @param ids 需要删除的司机登录主键集合
     * @return 结果
     */
    public int deleteTbUserByIds(Long[] ids);

    /**
     * 删除司机登录信息
     * 
     * @param id 司机登录主键
     * @return 结果
     */
    public int deleteTbUserById(Long id);

    TbUser findUser(TbUser user);

    TbUser userLogin(String username, String password);


    TbUser login(String phone);

    TbUser UserPhone(String phone);

    Integer saveDriver(Driver driver);
}
