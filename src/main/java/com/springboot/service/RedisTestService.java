package com.springboot.service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import com.springboot.enty.Stu;

@Service
public class RedisTestService {
	/**
	value 代表数据存储在redis库中哪个文件夹，不写value会报错，
	除非在类上注解@CacheConfig(cacheNames= {"UserControllercache"})
	此注解会将这个类所缓存的都存储在指定的这个名字下面
	key  redis的key （不过最终生成的key由value的值::加key的值）
	*/
	//如果缓存中有数据，这个方法体直接不执行，如果没有才会执行
	@Cacheable(value="'stus'",key="'stu:1'")
	public Stu getStu() {
		System.out.println("================");
		Stu stu = new Stu();
		stu.setId(12);
		stu.setStuname("zhang三");
		stu.setStusex("男");
		return stu;
	}
}
