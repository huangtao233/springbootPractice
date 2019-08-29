package com.example.springboot.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {


    private String username;
    private int id;
    private int age;
    private String job;

    public static String getloginLockKey(String username) {
        return "failLoginLockTime:" + username;
    }

    public static String getFailCountKey(String username) {
        return "failLoginCount:" + username;
    }
}
