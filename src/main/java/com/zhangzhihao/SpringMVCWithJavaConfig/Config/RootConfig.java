package com.zhangzhihao.SpringMVCWithJavaConfig.Config;

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
//@ImportResource("classpath:Spring.xml")
@ComponentScan(basePackages = "com.zhangzhihao.SpringMVCWithJavaConfig",
        excludeFilters =//不扫描以下包
                {@ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        value = {EnableWebMvc.class, ControllerAdvice.class, Controller.class})})
public class RootConfig {



    /*@Bean
    public DelegatingFilterProxy getDelegatingFilterProxy() {
        return new DelegatingFilterProxy();
    }*/
}
