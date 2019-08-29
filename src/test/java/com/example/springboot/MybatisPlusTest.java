package com.example.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.bean.UserAddressVo;
import com.example.springboot.bean.Userinfo;
import com.example.springboot.mapper.JoinMapper;
import com.example.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test() {

    }

    /**
     * mp执行新增操作
     * mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
     */
    @Test
    public void insert() {

        for (int i = 0; i < 20; i++) {
            Userinfo userinfo = new Userinfo();

            userinfo.setUserName(JdbcTest.getRandName());
            userinfo.setTelephone(JdbcTest.getRandTele());
            userMapper.insert(userinfo);
            System.out.println(userinfo.getUserId());
        }


    }


    @Test
    public void update() {
        Userinfo userinfo = userMapper.selectById(1);
        userinfo.setTelephone("212");
        //根据条件查询还是根据QueryWrapper
        userMapper.update(userinfo, new QueryWrapper<Userinfo>().eq("GENDER", 1));

    }

    @Test
    public void delete() {

        userMapper.deleteById(2);
        //根據條件刪除還是用QueryWrapper
        userMapper.delete(new QueryWrapper<Userinfo>().eq("GENDER", 0));
    }


    @Autowired
    private JoinMapper joinMapper;

    /**
     * 条件查询，分頁,多表查询
     * page作为参数放入之后，mp直接就对入参的page做了赋值，返回值不需要再是Ipage
     */
    @Test
    public void selectByCondition() {

//        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<Userinfo>();
//        queryWrapper.eq("GENDER", 1);
//        System.out.println(userMapper.selectList(queryWrapper).size());

        //current是第几页，size是每页取几条。第一页是0或者1都可以
        IPage<UserAddressVo> page = new Page<UserAddressVo>(1, 5);
//
//        page = userMapper.selectPage(page, null);

        System.out.println("page=" + page.getPages());
        // page=joinMapper.selectUserAddressList(page);
        joinMapper.selectUserAddressList(page);

        System.out.println("page=" + page.getPages());

    }

    /**
     * MP代码自动生成器
     */
    @Test
    public void autoGenerateCode() {


    }

}
