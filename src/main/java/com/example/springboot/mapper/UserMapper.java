package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.bean.Userinfo;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<Userinfo> {


}
