package com.ruoyi.dirver.service.impl;

import java.util.List;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.dirver.domain.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dirver.mapper.TbUserMapper;
import com.ruoyi.dirver.domain.TbUser;
import com.ruoyi.dirver.service.ITbUserService;

/**
 * 司机登录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class TbUserServiceImpl implements ITbUserService 
{
    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 查询司机登录
     * 
     * @param id 司机登录主键
     * @return 司机登录
     */
    @Override
    public TbUser selectTbUserById(Long id)
    {
        return tbUserMapper.selectTbUserById(id);
    }

    /**
     * 查询司机登录列表
     * 
     * @param tbUser 司机登录
     * @return 司机登录
     */
    @Override
    public List<TbUser> selectTbUserList(TbUser tbUser)
    {
        return tbUserMapper.selectTbUserList(tbUser);
    }

    /**
     * 新增司机登录
     * 
     * @param tbUser 司机登录
     * @return 结果
     */
    @Override
    public int insertTbUser(TbUser tbUser)
    {
        return tbUserMapper.insertTbUser(tbUser);
    }

    /**
     * 修改司机登录
     * 
     * @param tbUser 司机登录
     * @return 结果
     */
    @Override
    public int updateTbUser(TbUser tbUser)
    {
        return tbUserMapper.updateTbUser(tbUser);
    }

    /**
     * 批量删除司机登录
     * 
     * @param ids 需要删除的司机登录主键
     * @return 结果
     */
    @Override
    public int deleteTbUserByIds(Long[] ids)
    {
        return tbUserMapper.deleteTbUserByIds(ids);
    }

    /**
     * 删除司机登录信息
     * 
     * @param id 司机登录主键
     * @return 结果
     */
    @Override
    public int deleteTbUserById(Long id)
    {
        return tbUserMapper.deleteTbUserById(id);
    }

    @Override
    public TbUser findUser(TbUser user) {
        return tbUserMapper.findUser(user);
    }

    @Override
    public TbUser userLogin(String username, String password) {
        return tbUserMapper.userLogin(username,password);
    }

    @Override
    public TbUser login(String phone) {
        return tbUserMapper.login(phone);
    }

    @Override
    public TbUser UserPhone(String phone) {
        return tbUserMapper.UserPhone(phone);
    }

    @Override
    public Integer saveDriver(Driver driver) {
        return tbUserMapper.saveDriver(driver);
    }


}
