//package com.github.izhangzhihao.SpringMVCSeedProject.Config;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//
///**
// * 将SpringMVC缓存至EhCache中就取消注释该文件，注释掉
// * @see RedisCacheConfig
// */
//@EnableCaching
//@Configuration
//public class EhCacheConfig {
//
//    @Bean
//    public EhCacheManagerFactoryBean getEhcache() {
//        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//        ehCacheManagerFactoryBean.setShared(true);
//        ehCacheManagerFactoryBean.setAcceptExisting(true);
//        Resource resource = new ClassPathResource("ehcache.xml");
//        ehCacheManagerFactoryBean.setConfigLocation(resource);
//        return ehCacheManagerFactoryBean;
//    }
//
//    @Bean
//    public EhCacheCacheManager getEhCacheCacheManager() {
//        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
//        ehCacheCacheManager.setCacheManager(getEhcache().getObject());
//        return ehCacheCacheManager;
//    }
//
//}
