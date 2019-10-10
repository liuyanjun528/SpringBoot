package com.springboot.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.enty.Stu;
import com.springboot.enty.User;

@Service
public class RedisTestService {
	@Cacheable(value="'users'",key="'user:1'")
	public User getUser() {
		User user = new User();
		user.setId(1);
		user.setName("小明");
		user.setAge(19);
		return  user;
	}
	@Cacheable(value="'stus'",key="'stu:1'")
	public Stu getStu() {
		Stu stu = new Stu();
		stu.setId(12);
		stu.setStuname("zhang三");
		stu.setStusex("男");
		return stu;
	}
}
