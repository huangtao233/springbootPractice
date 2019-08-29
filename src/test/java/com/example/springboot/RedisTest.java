package com.example.springboot;

import com.example.springboot.bean.Recevier;
import com.example.springboot.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Test
    public void testJedis() {

        Jedis jedis = RedisUtils.getRedis();
        System.out.println("jedis连接redis:" + jedis.ping());
        jedis.close();
    }

    /**
     * 测试String字符串
     */
    @Test
    public void testString() {
        String host = "192.168.91.128";
        int port = 6379;
        String password = "huangtao";
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);

        jedis.set("user:1:name", "huangtao");


        System.out.println("从redis中获取key的值=" + jedis.get("user:1:name"));
        jedis.close();
    }


    /**
     * redis是缓存数据库压力的,先从redis去值，取不到再去数据库找。
     * 然后再把值存到redis中；
     */
    @Test
    public void test3() {

        String key = "testKey";

        Jedis jedis = RedisUtils.getRedis();

        if (jedis.exists(key)) {
            System.out.println("从redis取出的值：");
            System.out.println(jedis.get(key));
        } else {
            //从数据库中取值...

            String value = "testValue";

            jedis.set(key, value);
            System.out.println("从数据库取出的值：");
            System.out.println(jedis.get(key));

        }

        RedisUtils.closeRedis(jedis);
    }


    /**
     * 测试HASH类型数据
     */
    @Test
    public void testHash() {
        Jedis jedis = RedisUtils.getRedis();

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "xiaowang");
        hashMap.put("age", "22");
        hashMap.put("job", "teacher");

        jedis.hmset("userTest", hashMap);

        RedisUtils.closeRedis(jedis);

    }


    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    /**
     * springboot整合redis
     */
    @Test
    public void testSpringAndRedis() {

//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//
//
//        String key = "springRedis";
//        if (redisTemplate.hasKey(key)) {
//            //从redis取数据
//            System.out.println("从redis中取数据：" + valueOperations.get(key));
//        } else {
//            //从数据库中取数据
//            String value = "springRedis的值";
//            valueOperations.set(key, value);
//            System.out.println("从数据库取数据：" + value);
//        }

    }

    /**
     * springboot整合redis操作HASH  存对象
     */
    @Test
    public void testSpringRedis() {


//        User user = new User();
//        user.setUsername("huangtao22");
//        user.setAge(22);
//        user.setId(3);
//        user.setJob("teacher");
//
//        redisTemplate.opsForHash().put("userHASH", user.getId(), user);
//
//
//        User user2 = (User) redisTemplate.opsForHash().get("userHASH", user.getId());
//        System.out.println(user2.getUsername());
    }


    /**
     * springboot整合redis操作HASH
     */
    @Test
    public void testSpringRedisHash() {


        redisTemplate.opsForHash().put("userHASH", "name", "huangtao");


    }


    @Autowired
    UserServiceImpl userImpl;


    /**
     * redisTemplate操作list
     */
    @Test
    public void testList() {

        String cartId = "1001";
        System.out.println("初始化队列----");
        userImpl.initQueue(cartId);
        System.out.println("待执行队列----");
        List<String> listWait = userImpl.initQueueWait(cartId);
        for (String task : listWait) {
            System.out.println(task);
        }
        System.out.println("小哥开始操作物流信息----");
        userImpl.QueueTouch(cartId);
        userImpl.QueueTouch(cartId);
        userImpl.QueueTouch(cartId);
        System.out.println("已完成队列----");
        List<String> listSucc = userImpl.QueueListSucc(cartId);
        for (String task : listSucc) {
            System.out.println(task);
        }

    }


    @Autowired
    CountDownLatch latch;
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);

    @Test
    public void testRedisMQ()  throws Exception{
        LOGGER.info("Send Mesaage...");
        redisTemplate.convertAndSend("chat", "this is a message from redis");
        latch.await();//阻塞当前线程，等待其他线程调用 latch.countDown()N次，满足new CountDownLatch(N)中的N次数时
        System.exit(0);

    }

}
