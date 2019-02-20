package com.wjc.config;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自定义RedisTemplate模板
 *
 * @author wangjc
 * @see RedisAutoConfiguration
 */
@Configuration
public class RedisConfig {

  /**
   * 用于支持@Cacheable、@CacheEvict、@CachePut等注解
   */
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {
    RedisCacheManager cacheManager = RedisCacheManager.create(factory);
    return cacheManager;
  }

  /**
   * 自定义缓存模板
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(factory);
    //使用StringRedisSerializer来序列号key
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new JdkSerializationRedisSerializer());
    return template;
  }

}
