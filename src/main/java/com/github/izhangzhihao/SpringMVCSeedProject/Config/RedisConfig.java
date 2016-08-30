package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
//@EnableRedisHttpSession 开启此注释将不通过shiro存储session至redis
public class RedisConfig {

    @Autowired
    private Environment environment;

    private String getPropertyFormEnv(String propertyName) {
        return environment.getProperty(propertyName);
    }

    private int getIntPropertyFormEnv(String propertyName) {
        return Integer.parseInt(environment.getProperty(propertyName));
    }

    @Configuration
    @Profile("development")
    @PropertySource("classpath:development/redis.properties")
    static class Development {
    }

    @Configuration
    @Profile("production")
    @PropertySource("classpath:production/redis.properties")
    static class Production {
    }

    @Configuration
    @Profile("test")
    @PropertySource("classpath:test/redis.properties")
    static class Test {
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(getIntPropertyFormEnv("redis.maxTotal"));
        jedisPoolConfig.setMaxIdle(getIntPropertyFormEnv("redis.maxIdle"));
        jedisPoolConfig.setMaxWaitMillis(getIntPropertyFormEnv("redis.maxWaitMillis"));
        jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("redis.testOnBorrow")));
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory connectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setHostName(getPropertyFormEnv("redis.host"));
        connection.setPort(getIntPropertyFormEnv("redis.port"));
        connection.setTimeout(getIntPropertyFormEnv("redis.timeout"));
        connection.setDatabase(getIntPropertyFormEnv("redis.database"));
        connection.setPoolConfig(jedisPoolConfig);
        return connection;
    }

    /*@Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        return redisTemplate;
    }*/

    @Bean
    @Primary
    public StringRedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
}
