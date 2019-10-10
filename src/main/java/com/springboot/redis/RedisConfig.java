package com.springboot.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.enty.Stu;

//import redis.clients.jedis.JedisPoolConfig;
/**
 * spring中使用redis可以用这种java配置类的方式配置相关bean
 * 对于springboot来说，自动配置已经帮我们注入jedisConnectionFactory，以及redisTemplate
 * 所以不需要在做了，只需要在配置文件做相关连接池以及redis的配置即可
 * 
 * @author 刘彦军
 *
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	// @Bean
	// public JedisPoolConfig jedisPoolConfig() {
	// JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	// // 最大空闲数
	// jedisPoolConfig.setMaxIdle(10);
	// // 最小空闲数
	// jedisPoolConfig.setMinIdle(5);
	// // 最大连接数
	// jedisPoolConfig.setMaxTotal(20);
	// return jedisPoolConfig;
	// }
	//
	// // 参数传入spring容器中的连接池
	// @Bean
	// public JedisConnectionFactory jedisConnectionFactory() {
	// JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
	// // 关联连接池
	// //jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
	// jedisConnectionFactory.setHostName("192.168.123.105");
	// jedisConnectionFactory.setPort(6379);
	// jedisConnectionFactory.setPassword("123456");
	// return jedisConnectionFactory;
	// }
	////
	//// // 注入RedisTemplate对象
	// @Bean
	// public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory
	// jedisConnectionFactory) {
	// RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	// // 设置序列化
	// redisTemplate.setConnectionFactory(jedisConnectionFactory);
	// redisTemplate.setKeySerializer(new StringRedisSerializer());
	// redisTemplate.setValueSerializer(new StringRedisSerializer());
	// return redisTemplate;
	// }

	@Bean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		// key序列化方式
		template.setKeySerializer(redisSerializer);
		// value序列化
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// value hashmap序列化
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		RedisCacheConfiguration redisCacheConfiguration = config
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));

		RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration)
				.build();
		return cacheManager;
	}


}
