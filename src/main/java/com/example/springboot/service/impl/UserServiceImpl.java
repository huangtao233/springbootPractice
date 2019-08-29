package com.example.springboot.service.impl;

import com.example.springboot.bean.User;
import com.example.springboot.bean.Userinfo;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void addUser(Userinfo user) {


        StringBuffer sb = new StringBuffer("INSERT INTO userinfo (USER_NAME,TELEPHONE ) VALUES (");
        sb.append("'" + user.getUserName() + "' , '");
        sb.append(user.getTelephone() + "' )");


        jdbcTemplate.execute(sb.toString());
    }

    @Override
    public void deleteUser(Integer userId) {
        jdbcTemplate.execute("delete from userinfo where USER_ID = " + userId);
    }

    @Override
    public void updateUser(Userinfo user) {

        jdbcTemplate.execute("UPDATE userinfo SET USER_NAME= '" +
                user.getUserName() + " ',TELEPHONE=' " + user.getTelephone() + "' where USER_ID='" + user.getUserId() + "'");
    }

    @Override
    public List<Userinfo> getUsers() {


        String sql = "select * from userinfo ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Userinfo.class));
    }

    @Override
    public Userinfo getUserById(Integer userId) {
        String sql = "select  *  from userinfo where user_id=" + userId;

        return (Userinfo) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(Userinfo.class));
    }

    /**
     * 判断用户是否被限制登陆
     *
     * @param username
     * @return
     */
    @Override
    public Map loginUserLock(String username) {
        Map map = new HashMap<String, Object>();
        String key = User.getloginLockKey(username);
        if (redisTemplate.hasKey(key)) {
            long leftTime = redisTemplate.getExpire(key, TimeUnit.MINUTES);
            map.put("flag", true);
            map.put("leftTime", leftTime);//返回剩余锁定时间，单位分钟
        } else {
            map.put("flag", false);
        }


        return map;
    }


    /**
     * 添加登陆错误次数，如果2mins内超过5次，则禁止登陆1小时
     *
     * @param username
     * @return
     */
    @Override
    public String loginValidate(String username) {

        //获取登陆失败次数的key
        String key = User.getFailCountKey(username);
        int maxErrorTimes = 5;//运行的登陆错误次数
        if (!redisTemplate.hasKey(key)) {
            //第一次登陆失败
            redisTemplate.opsForValue().set(key, 1);
            redisTemplate.expire(key, 2, TimeUnit.MINUTES);//key的生命周期为2分钟
            return "登陆失败，您2分钟内还能继续输入：" + (maxErrorTimes - 1) + "次";

        } else {
            long failCount = Long.parseLong(redisTemplate.opsForValue().get(key) + "");
            if (failCount < (maxErrorTimes - 1)) {

                //没超过5次
                redisTemplate.opsForValue().increment(key);
                long seconds = redisTemplate.getExpire(key, TimeUnit.SECONDS);
                return "登陆失败，您在" + seconds + "秒内还能继续输入：" + (maxErrorTimes - failCount - 1) + "次";

            } else {

                //超过最大次数
                String lockKey = User.getloginLockKey(username);
                redisTemplate.opsForValue().set(lockKey, 1);
                redisTemplate.expire(lockKey, 1, TimeUnit.HOURS);

                //情况登陆
                return "你已经登陆失败" + maxErrorTimes + "次，被限制登陆1小时";
            }

        }


    }

    @Resource(name = "redisTemplate")
    ListOperations<String, String> listOperations;

    public void initQueue(String cartId) {
        String key = "prop:" + cartId;

        redisTemplate.delete(key);
        listOperations.leftPushAll(key, "1商家出货", "2小哥发件",
                "3北京某海淀校区--》首都机场", "4首都机场--》南京机场", "5南京机场--》建邺区", "6建邺区--》收货人");

    }


    public List<String> initQueueWait(String cartId) {
        String key = "prop:" + cartId;

        return listOperations.range(key, 0, -1);

    }

    public void QueueTouch(String cartId) {
        String key = "prop:" + cartId + ":succ";


        listOperations.rightPopAndLeftPush("prop:" + cartId, key);


    }

    public List<String> QueueListSucc(String cartId) {
        String key = "prop:" + cartId + ":succ";


        return listOperations.range(key, 0, -1);


    }
}
