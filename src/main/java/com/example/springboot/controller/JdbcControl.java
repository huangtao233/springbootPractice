package com.example.springboot.controller;

import com.example.springboot.bean.Userinfo;
import com.example.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class JdbcControl {

    @Autowired
    private UserService userServiceImpl;


    /**
     * 查询用户列表
     *
     * @return
     */
    @ApiOperation(value="查看用户列表", notes="查看用户列表")
    @GetMapping("/testJdbc/users")
    public List<Userinfo> listUsers() {


        return userServiceImpl.getUsers();

    }

    @ApiOperation(value="查看具体用户根据ID", notes="查看具体用户根据ID")
    @GetMapping("/testJdbc/users/{userId}")
    public Userinfo getUser(@PathVariable("userId") Integer userId) {
        return userServiceImpl.getUserById(userId);

    }


}
