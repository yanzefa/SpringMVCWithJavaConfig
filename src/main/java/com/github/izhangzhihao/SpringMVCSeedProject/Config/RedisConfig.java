package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableRedisHttpSession
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
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(getIntPropertyFormEnv("redis.maxTotal"));
        jedisPoolConfig.setMaxIdle(getIntPropertyFormEnv("redis.maxIdle"));
        jedisPoolConfig.setMaxWaitMillis(getIntPropertyFormEnv("redis.maxWaitMillis"));
        jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("redis.testOnBorrow")));
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setHostName(getPropertyFormEnv("redis.host"));
        connection.setPort(getIntPropertyFormEnv("redis.port"));
        connection.setTimeout(getIntPropertyFormEnv("redis.timeout"));
        connection.setDatabase(getIntPropertyFormEnv("redis.database"));
        connection.setPoolConfig(getJedisPoolConfig());
        return connection;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
