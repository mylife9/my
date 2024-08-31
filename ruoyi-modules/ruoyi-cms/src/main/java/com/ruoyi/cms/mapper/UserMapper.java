package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/24 8:04
 * @注释
 */
@Mapper
public interface UserMapper {
    @Select("select  * from tb_user where username=#{account} or phone=#{account} or email=#{account}")
    TbUser findUser(TbUser user);
}
