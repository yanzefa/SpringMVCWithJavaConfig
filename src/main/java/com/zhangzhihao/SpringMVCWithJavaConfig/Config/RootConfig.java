package com.zhangzhihao.SpringMVCWithJavaConfig.Config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableCaching
@EnableAspectJAutoProxy
@EnableRedisHttpSession
//@ImportResource("classpath:Spring.xml")
@ComponentScan(basePackages = "com.zhangzhihao.SpringMVCWithJavaConfig",
        excludeFilters =//不扫描以下包
                {@ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        value = {EnableWebMvc.class, ControllerAdvice.class, Controller.class})})
public class RootConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setHostName("127.0.0.1");
        connection.setPort(6379);
        return connection;
    }

    /*@Bean
    public DelegatingFilterProxy getDelegatingFilterProxy() {
        return new DelegatingFilterProxy();
    }*/
}
