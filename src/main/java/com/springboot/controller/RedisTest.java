package com.springboot.controller;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.springboot.enty.Stu;
import com.springboot.enty.User;
import com.springboot.service.RedisTestService;

@EnableCaching
@RestController
public class RedisTest {
	// 注入缓存管理器
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	RedisTestService redisTestService;
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@GetMapping("/redisTest")
	@Cacheable(value = "'db1'", key = "'redistest'")
	public String redisTest() {
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		// redisTemplate.setValueSerializer(new StringRedisSerializer());
		if (!redisTemplate.hasKey("name")) {
			ValueOperations<Object, Object> redisString = redisTemplate.opsForValue();

			redisString.set("name", "liuyanjun");
			redisString.set("cln", "meinv", 200, TimeUnit.SECONDS);
			redisTemplate.expire("name", 200, TimeUnit.SECONDS);

		}
		// User user = new User();
		// //user.setId(1);
		// user.setName("小明");
		// //user.setAge(19);
		// redisTemplate.opsForHash().put("user", user.getName(), user);
		// Boolean hasKey = redisTemplate.opsForHash().hasKey("user", user.getName());
		// if(hasKey) {
		// //User u =(User) redisTemplate.opsForHash().get("user", user.getName());
		// //return "redis测试"+u;
		// }

		return "redistesthhhhhh你好五五五";
	}

	@GetMapping("/getStu")
	public Stu getStu() {
		 
		
		return redisTestService.getStu();
	}
	
	@GetMapping("/test2")
	public Stu test2() {
		//通过缓存管理器也可以操作缓存getCache("'stus'");参数为@Cacheable(value="'stus'",key="'stu:1'")中的value
		//可以为这个缓存空间中添加缓存数据
		Cache cache = cacheManager.getCache("'stus'");
		cache.put("111", "222");
		Collection<String> cacheNames = cacheManager.getCacheNames();
		System.out.println("获得缓存中所有的命名空间："+cacheNames.size());
		for (String cacheName : cacheNames) {
			System.out.println("缓存中的命名空间："+cacheName);
		}
		String key = "'stus'::stu:1";
		Stu redisStu = (Stu) redisTemplate.opsForValue().get(key);
		System.out.println(redisStu);
		return redisStu;
	}
}
