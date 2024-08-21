package com.ruoyi.dirver.mapper;

import java.util.List;

import com.ruoyi.dirver.domain.Driver;
import com.ruoyi.dirver.domain.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 司机登录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Mapper
public interface TbUserMapper 
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
     * 删除司机登录
     * 
     * @param id 司机登录主键
     * @return 结果
     */
    public int deleteTbUserById(Long id);

    /**
     * 批量删除司机登录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbUserByIds(Long[] ids);

    @Select("select * from tb_user where username=#{username} and password=#{password}")
    TbUser findUser(TbUser user);

    @Select("select * from tb_user where username=#{username} and password=#{password}")
    TbUser userLogin(String username, String password);

    @Select("select * from tb_user where phone=#{phone}")
    TbUser login(String phone);

    @Select("select * from tb_user where phone=#{phone}")
    TbUser UserPhone(String phone);

    @Insert("insert into driver_user set driver_name=#{driverName},driver_phone=#{driverPhone},driver_number=#{driverNumber},driver_gender=#{driverGender},driver_birthday=#{driverBirthday},driver_contact_address=#{driverAddress},license_id=#{licenseId},driver_photo=#{driverPhoto}")
    Integer saveDriver(Driver driver);
}
