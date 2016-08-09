package com.zhangzhihao.SpringMVCWithJavaConfig.Config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class EhCacheConfiguration {

    @Bean
    public EhCacheManagerFactoryBean getEhcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setShared(true);
        ehCacheManagerFactoryBean.setAcceptExisting(true);
        Resource resource = new ClassPathResource("ehcache.xml");
        ehCacheManagerFactoryBean.setConfigLocation(resource);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager getEhCacheCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(getEhcache().getObject());
        return ehCacheCacheManager;
    }

}
