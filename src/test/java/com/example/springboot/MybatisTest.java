package com.example.springboot;

import com.example.springboot.bean.Userinfo;
import com.example.springboot.mapper.UserinfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    UserinfoMapper userinfoMapper;


    /**
     * mybatis获取用户列表
     */
    @Test
    public void getUsers() {

        List<Userinfo> userinfos = userinfoMapper.selectByExample(null);

        for (Userinfo user : userinfos) {
            System.out.println(user.getUserName());
        }
    }

    /**
     * mybatis根据ID查询用户
     */
    @Test
    public void queryUserById() {


        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(0);

        System.out.println(userinfo.getUserName());
    }

    /**
     * mybatis新增用户(返回自增主键需要在mapper.xml文件中配置
     * useGeneratedKeys="true" keyProperty="userId")
     */
    @Test
    public void addUser() {

        Userinfo userinfo = new Userinfo();
        Random random = new Random();
        StringBuffer randName = new StringBuffer();

        for (int i = 0; i < 10; i++) {
            char randomChar = (char) (97 + Math.abs(random.nextInt() % 26));
            randName.append(String.valueOf(randomChar));
        }

        StringBuffer randTelephone = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int randomChar = random.nextInt(10);
            randTelephone.append(randomChar);
        }


        userinfo.setUserName(randName.toString());
        userinfo.setTelephone(randTelephone.toString());

        userinfoMapper.insert(userinfo);
        System.out.println(userinfo.getUserId());
    }

    /**
     * mybatis删除用户
     */
    @Test
    public void deleteUserById() {

        userinfoMapper.deleteByPrimaryKey(13);

    }

    /**
     * mybatis修改用户
     */
    @Test
    public void updateUser() {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(11);

        if(userinfo!=null){
            //随机产生一个名字
            Random random = new Random();
            StringBuffer randName = new StringBuffer();

            for (int i = 0; i < 10; i++) {
                char randomChar = (char) (97 + Math.abs(random.nextInt() % 26));
                randName.append(String.valueOf(randomChar));
            }

            userinfo.setUserName(randName.toString());
            userinfoMapper.updateByPrimaryKeySelective(userinfo);

        }
        else{

            System.out.println("未查到数据");
        }
        }


}

