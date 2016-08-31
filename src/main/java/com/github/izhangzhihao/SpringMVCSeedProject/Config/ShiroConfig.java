package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import com.github.izhangzhihao.SpringMVCSeedProject.Repository.ShiroRealm;
import com.github.izhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Listener.ShiroSessionListener;
import com.github.izhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Repository.CachingShiroSessionDao;
import com.github.izhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Repository.ShiroSessionRepository;
import com.github.izhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Service.ShiroSessionService;
import com.github.izhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Session.ShiroSessionFactory;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.junit.Assert;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.HashMap;

import static org.apache.shiro.codec.Base64.decode;


@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager webSecurityManager(Realm realm,
                                                        CookieRememberMeManager rememberMeManager,
                                                        DefaultWebSessionManager sessionManager,
                                                        EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public Realm getRealm() {
        return new ShiroRealm();
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2592000);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie);
        rememberMeManager.setCipherKey(decode("5AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    @Bean
    public ShiroSessionFactory sessionFactory() {
        return new ShiroSessionFactory();
    }

//    @Autowired
//    RedisTemplate redisTemplate;

    @Bean
    public ShiroSessionRepository shiroSessionRepository(RedisTemplate redisTemplate) {
        ShiroSessionRepository shiroSessionRepository = new ShiroSessionRepository();
        Assert.assertNotNull(redisTemplate);
        shiroSessionRepository.setRedisTemplate(redisTemplate);
        return shiroSessionRepository;
    }

    @Bean
    public CachingShiroSessionDao cachingShiroSessionDao(ShiroSessionRepository shiroSessionRepository) {
        CachingShiroSessionDao cachingShiroSessionDao = new CachingShiroSessionDao();
        cachingShiroSessionDao.setSessionRepository(shiroSessionRepository);
        return cachingShiroSessionDao;
    }

    @Bean
    public ShiroSessionService shiroSessionService(RedisTemplate redisTemplate,
                                                   CachingShiroSessionDao cachingShiroSessionDao) {
        ShiroSessionService shiroSessionService = new ShiroSessionService();
        shiroSessionService.setRedisTemplate(redisTemplate);
        shiroSessionService.setSessionDao(cachingShiroSessionDao);
        return shiroSessionService;
    }

    @Bean
    public ShiroSessionListener shiroSessionListener(CachingShiroSessionDao cachingShiroSessionDao,
                                                     ShiroSessionService shiroSessionService) {
        ShiroSessionListener shiroSessionListener = new ShiroSessionListener();
        shiroSessionListener.setSessionDao(cachingShiroSessionDao);
        shiroSessionListener.setShiroSessionService(shiroSessionService);
        return shiroSessionListener;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(CachingShiroSessionDao cachingShiroSessionDao,
                                                   ShiroSessionListener shiroSessionListener,
                                                   ShiroSessionFactory sessionFactory) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(false);
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setSessionValidationInterval(1800000);
        sessionManager.setSessionFactory(sessionFactory);
        sessionManager.setSessionDAO(cachingShiroSessionDao);
        sessionManager.setSessionIdCookie(new SimpleCookie("SHRIOSESSIONID"));
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionListeners(Collections.singletonList(shiroSessionListener));
        return sessionManager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        CacheManager cacheManager = CacheManager.create("classpath:ehcache-shiro.xml");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
        /*EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile();
        return ehCacheManager;*/
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager webSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(webSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager webSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);
        shiroFilterFactoryBean.setLoginUrl("/Account/Login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/Account/Login");

        HashMap<String, String> map = new HashMap<>();
        map.put("/Account/**", "anon");
        map.put("/login", "anon");
        map.put("/assets/**", "anon");
        map.put("/app/**", "anon");
        map.put("/**", "authc");//需要登陆
        //map.put("/**", "user");//通过记住我登陆

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
}