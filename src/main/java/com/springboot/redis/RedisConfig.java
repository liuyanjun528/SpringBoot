package com.springboot.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//import redis.clients.jedis.JedisPoolConfig;
/**
 * spring中使用redis可以用这种java配置类的方式配置相关bean
 * 对于springboot来说，自动配置已经帮我们注入jedisConnectionFactory，以及redisTemplate
 * 所以不需要在做了，只需要在配置文件做相关连接池以及redis的配置即可
 * @author 刘彦军
 *
 */
@Configuration
public class RedisConfig {
/*	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(10);
		// 最小空闲数
		jedisPoolConfig.setMinIdle(5);
		// 最大连接数
		jedisPoolConfig.setMaxTotal(20);
		return jedisPoolConfig;
	}

	// 参数传入spring容器中的连接池
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		// 关联连接池
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6379);
		return jedisConnectionFactory;
	}

	// 注入RedisTemplate对象
	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		// 设置序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}*/
}
