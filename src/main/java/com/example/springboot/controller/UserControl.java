package com.example.springboot.controller;

import com.example.springboot.bean.User;
import com.example.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserControl {


    @Autowired
    UserService userImpl;

    @RequestMapping(value="/login",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    @ResponseBody
    @ApiOperation(value="用户登录", notes="用户登录")
    public String loginUser(String username) {


        Map<String, Object> map = userImpl.loginUserLock(username);
        if ((boolean) map.get("flag")) {//被禁止登陆，返回剩余时间。单位分钟

            return "您 " +username+"已经输错5次密码 ，已被限制登陆，" +
                    "还剩下"+ map.get("leftTime")+" 分钟";
        } else {

            //没有被禁止登陆，开始登陆
            //模拟登陆...

            User user = null;//user为空模拟登陆失败

            return userImpl.loginValidate(username);


        }


    }
}
