package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboot.bean.UserAddressVo;
import com.example.springboot.bean.Userinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JoinMapper extends BaseMapper<Userinfo> {


    @Select("SELECT USER_NAME,ADDR_CONTENT FROM userinfo,address")
    List<UserAddressVo> selectUserAddressList(IPage<UserAddressVo> page);
}
