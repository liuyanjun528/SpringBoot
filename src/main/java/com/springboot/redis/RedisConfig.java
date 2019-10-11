package com.springboot.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * spring中使用redis可以用这种java配置类的方式配置相关bean（相关配置百度）
 * 对于springboot来说，自动配置已经帮我们注入jedisConnectionFactory，以及redisTemplate，StringRedisTemplate
 * 所以不需要在做了，只需要在配置文件做相关连接池以及redis的连接配置即可 不过自动配置的RedisTemplate<Object,
 * Object>默认使用jdk的序列化机制，我们需要配置json的序列化 Jackson2JsonRedisSerializer
 * 
 * @author 刘彦军
 *
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	/**
	 * 定义自己的redisTemplate对象，如果容器中有redisTemplate对象，
	 * springboot将不在注入它默认的redisTemplate对象 也可以不用指定name属性，那么方法名一定要为redisTemplate
	 * （应为bean默认的名称为方法名,这个机制也可以排除springboot注入默认redisTemplate）
	 */
	@Bean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		//（取出数据序列化配置）
		// 配置在使用template对象从缓存读取json字符串的时候，无法将json字符串转换为java Bean的问题，不配置则抛出异常
		// （java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to
		// com.springboot.enty.Stu）
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//（存入数据序列化配置）
		// key序列化方式
		template.setKeySerializer(redisSerializer);
		// value存入序列化
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
		//（取出数据序列化配置）
		// 配置在使用 @Cacheable(value="'stus'",key="'stu:1'")注解，当第二次从缓存中读取
		// 数据时，无法将json字符串转换为java Bean的问题，不配置则抛出异常
		// （java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to
		// com.springboot.enty.Stu）
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//（存入数据序列化配置）
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
