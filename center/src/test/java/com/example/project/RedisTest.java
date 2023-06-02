package com.example.project;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import com.example.project.model.entity.User;
import io.lettuce.core.GeoArgs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations<String, Object, Object> HashOperations = redisTemplate.opsForHash();

//for循环增加id ， 内容不变， 用户数据内容
        // add by string

//        addByString(user,valueOperations);


        //add by hush


//        // 增
//        valueOperations.set("yupiString", "dog");
//        valueOperations.set("yupiInt", 1);
//        valueOperations.set("yupiDouble", 2.0);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("yupi");
//        valueOperations.set("yupiUser", user);
//        // 查
//        Object yupi = valueOperations.get("yupiString");
//        Assertions.assertTrue("dog".equals((String) yupi));
//        yupi = valueOperations.get("yupiInt");
//        Assertions.assertTrue(1 == (Integer) yupi);
//        yupi = valueOperations.get("yupiDouble");
//        Assertions.assertTrue(2.0 == (Double) yupi);
//        System.out.println(valueOperations.get("yupiUser"));
//        valueOperations.set("yupiString", "dog");
//        redisTemplate.delete("yupiString");
    }

}
