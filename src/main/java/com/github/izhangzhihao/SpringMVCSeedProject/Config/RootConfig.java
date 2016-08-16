package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 相当于Spring.xml
 */
@Configuration
@EnableCaching
@EnableAspectJAutoProxy
//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableAutoConfiguration
//@ImportResource("classpath:Spring.xml")
@ComponentScan(basePackages = "com.github.izhangzhihao.SpringMVCSeedProject",
        excludeFilters =//不扫描以下包
                {@ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        value = {EnableWebMvc.class, ControllerAdvice.class, Controller.class})})
public class RootConfig {


    public static void main(String[] args) {
        SpringApplication.run(RootConfig.class, args);
    }

    /*@Bean
    public DelegatingFilterProxy getDelegatingFilterProxy() {
        return new DelegatingFilterProxy();
    }*/
}
