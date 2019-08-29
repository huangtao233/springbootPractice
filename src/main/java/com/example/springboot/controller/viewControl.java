package com.example.springboot.controller;

import com.example.springboot.bean.User;
import com.example.springboot.bean.Userinfo;
import com.example.springboot.bean.UserinfoExample;
import com.example.springboot.mapper.UserinfoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class viewControl {

    @Autowired
    UserinfoMapper userinfoMapper;

    @RequestMapping("/test")
    @ApiOperation(value="跳转test.html", notes="跳转test.html")
    ModelAndView forwardTest() {


        Userinfo userinfo  = new Userinfo();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userinfo);
        modelAndView.addObject("var", 3);

        modelAndView.setViewName("thymeleafTest/test");

        return modelAndView;
    }

    @RequestMapping("/test2")
    @ApiOperation(value="跳转test2.html", notes="跳转test2")
    String forwardTest(HttpServletRequest request) {


        List list = Arrays.asList(new User(), new User(), new User());
        request.setAttribute("list", list);


        return "thymeleafTest/test2";
    }


    @RequestMapping("/")
    @ApiOperation(value="首页", notes="首页")
    String forwardIndex() {


        return "thymeleafTest/index";
    }


    @RequestMapping("/jdbc")
    @ApiOperation(value="跳转jdbc页面", notes="跳转jdbc页面")
    String forwardJdbc() {


        return "jdbcPages/jdbc";
    }
}
