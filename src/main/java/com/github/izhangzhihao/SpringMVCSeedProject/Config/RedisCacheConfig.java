package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 将SpringMVC缓存至Redis中就取消注释该文件，注释掉
 *
 * @see EhCacheConfig
 */
@SuppressWarnings("JavadocReference")
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(300);// 设置缓存过期时间单位秒，默认不过期
        return cacheManager;
    }

    /**
     * @return 自定义策略生成的key
     * 自定义的缓存key的生成策略</br>
     * 若想使用这个key</br>
     * 只需要讲注解上keyGenerator的值设置为customKeyGenerator即可</br>
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append("-");
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
