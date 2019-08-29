package com.example.springboot;

import com.example.springboot.bean.User;
import com.example.springboot.bean.Userinfo;
import com.example.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {


    @Autowired
    UserService userService;


    /**
     * jdbc查询用户列表
     */
    @Test
    public void queryUsers() {

        List<Userinfo> userinfos = userService.getUsers();

        for (Userinfo user : userinfos) {
            System.out.println(user.getUserName());
        }
    }

    /**
     * jdbc根据ID查询用户
     */
    @Test
    public void queryUserById() {
        Userinfo userinfo = userService.getUserById(0);
        System.out.println(userinfo.getUserName());
    }


    public static String getRandName() {
        Random random = new Random();
        StringBuffer randName = new StringBuffer();

        for (int i = 0; i < 10; i++) {
            char randomChar = (char) (97 + Math.abs(random.nextInt() % 26));
            randName.append(String.valueOf(randomChar));
        }
        return randName.toString();
    }

    public static String getRandTele() {
        Random random = new Random();

        StringBuffer randTelephone = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int randomChar = random.nextInt(10);
            randTelephone.append(randomChar);
        }
        return randTelephone.toString();
    }

    /**
     * jdbc新增用户
     */
    @Test
    public void addUser() {
        Userinfo userinfo = new Userinfo();


        userinfo.setUserName(getRandName());
        userinfo.setTelephone(getRandTele());
        userService.addUser(userinfo);
    }

    /**
     * jdbc删除用户
     */
    @Test
    public void deleteUserById() {


        userService.deleteUser(5);


    }

    /**
     * jdbc修改用户
     */
    @Test
    public void updateUser() {
        Userinfo user = userService.getUserById(5);
        user.setUserName("updatedName");
        userService.updateUser(user);
    }


}
