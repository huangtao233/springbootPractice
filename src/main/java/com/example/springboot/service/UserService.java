package com.example.springboot.service;

import com.example.springboot.bean.Userinfo;

import java.util.List;
import java.util.Map;

public interface UserService {


    Map loginUserLock(String username);


    String loginValidate(String username);


    List<Userinfo>  getUsers();
    Userinfo getUserById(Integer userId);


    void addUser(Userinfo user);
    void deleteUser(Integer userId);
    void updateUser(Userinfo user);

}
