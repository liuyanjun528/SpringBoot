package springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.SpringBootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringBootApplication.class)
public class TestRedis {

	@Autowired
	private RedisTemplate<String , String> redisTemplate;
	@Test
	public void testRedis() {
		redisTemplate.opsForValue().set("name", "liuyanjun");
		
	}
}
