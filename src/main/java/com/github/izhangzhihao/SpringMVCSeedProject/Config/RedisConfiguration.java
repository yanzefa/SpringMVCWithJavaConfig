package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableRedisHttpSession
@PropertySource("classpath:redis.properties") //导入资源文件
public class RedisConfiguration {

    @Value("${redis.host}")
    String host;
    @Value("${redis.port}")
    int port;
    @Value("${redis.timeout}")
    int timeout;
    @Value("${redis.database}")
    int database;
    @Value("${redis.maxTotal}")
    int maxTotal;
    @Value("${redis.maxIdle}")
    int maxIdle;
    @Value("${redis.maxWaitMillis}")
    int maxWaitMillis;
    @Value("${redis.testOnBorrow}")
    boolean testOnBorrow;

    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setHostName(host);
        connection.setPort(port);
        connection.setTimeout(timeout);
        connection.setDatabase(database);
        connection.setPoolConfig(getJedisPoolConfig());
        return connection;
    }
}
