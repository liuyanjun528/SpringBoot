package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meditrusthealth.fast.common.core.servlet.ServletUtils;
//import com.springboot.Order;
import com.springboot.enty.Stu;
import com.springboot.enty.User;
import com.springboot.mapp.Ainmal;
import com.springboot.mapp.StuMapper;
import com.springboot.mapp.UserMapper;
import com.springboot.myEnum.MyEnum;
import com.springboot.response.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("/Api")
@RestController
@EnableCaching//开启redis缓存
@CacheConfig(cacheNames= {"UserControllercache"})//此注解会将这个类所缓存的都存储在指定的这个名字下面
//（redis会生成一个文件名），如果不用这个注解，那么方法上使用@Cacheable注解是必须给一个value属性，指定数据最后缓存到哪个redis库中
public class UserController{
	@Autowired
	RedisTemplate<String, String> jedis;
	//@Autowired
	//Order order;
	@Autowired
	private StuMapper stuMapper;
	@Autowired
	private  UserMapper usermapper;
	@Autowired
	private Ainmal ainmal;
//	 @Autowired
//	 private Cat cat;
	@Autowired
	private	User user;
	@Autowired
	private   ResponseMessage<User> responsemessage;
	//交由线程池进行异步处理，获取线程池bean，name
	//@Async("MyThreadPoolExecutroConfin")
	@RequestMapping("/user/{id}")
	@ApiOperation("用户接口")
	//@Cacheable(key="'controller'")//redis缓存，使用方法名作为key，返回值作为value，存入redis
	//配置了findByName函数的返回值将被加入缓存。同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问。
	public  ResponseMessage<User> controller( User user,HttpServletRequest request, @PathVariable Integer id) {
	//List<User> list=usermapper.page(user, new Paging(1));
	//AOP使用jdk代理模式.强制类型转换需要用接口去接
//	Ainmal ainmal=(Ainmal)new ProxyFactory(cat).getProxyObject(caradd);
//	ainmal.eat();
	Stu stu=stuMapper.selectByPrimaryKey(1);

	System.out.println(ainmal.selectById(2));//基于mybatisPlus,baseMapper的查询
	System.out.println(stu);
	System.out.println("id"+id);
	//log.info(this.user.toString());
	//responsemessage.setList(list);
	//System.out.println(2/0);
	System.out.println(request.getHeader("User-Agent"));// 客户端浏览器类型
	System.out.println(ServletUtils.getRemoteIp(request));// 
	//System.out.println(order.order("李四")+order.getName());
	responsemessage.sucess(MyEnum.SUCESS);
	System.out.println(request.getServletContext().getAttribute("num"));
		return responsemessage;
	}
	
}
