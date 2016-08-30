package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JPAConfig {

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
    @PropertySource("classpath:development/db.properties")
    static class Development {
    }

    @Configuration
    @Profile("production")
    @PropertySource("classpath:production/db.properties")
    static class Production {
    }

    @Configuration
    @Profile("test")
    @PropertySource("classpath:test/db.properties")
    static class Test {
    }

    /**
     * 配置数据源
     */
    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(getPropertyFormEnv("jdbc.userName"));
        dataSource.setPassword(getPropertyFormEnv("jdbc.password"));
        dataSource.setJdbcUrl(getPropertyFormEnv("jdbc.connectionURL"));
        dataSource.setDriverClass(getPropertyFormEnv("jdbc.driverClass"));
        dataSource.setInitialPoolSize(getIntPropertyFormEnv("jdbc.initialPoolSize"));
        dataSource.setMaxPoolSize(getIntPropertyFormEnv("jdbc.maxPoolSize"));
        return dataSource;
    }

    /**
     * 配置 Hibernate 的 SessionFactory 实例: 通过 Spring 提供的 LocalSessionFactoryBean 进行配置
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        //配置数据源属性
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);

        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL,getPropertyFormEnv("hibernate.show_sql"));
        jpaProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL,getPropertyFormEnv("hibernate.format_sql"));
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO,getPropertyFormEnv("hibernate.hbm2ddl.auto"));
        jpaProperties.put(org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE,true);
        jpaProperties.put(org.hibernate.cfg.Environment.CACHE_REGION_FACTORY,"org.hibernate.cache.SingletonEhCacheRegionFactory");
        jpaProperties.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE,true);
        jpaProperties.put(org.hibernate.cfg.Environment.ISOLATION,2);
        jpaProperties.put(org.hibernate.cfg.Environment.USE_IDENTIFIER_ROLLBACK,true);
        jpaProperties.put(org.hibernate.cfg.Environment.C3P0_MAX_SIZE,20);
        jpaProperties.put(org.hibernate.cfg.Environment.C3P0_MIN_SIZE,1);
        jpaProperties.put(org.hibernate.cfg.Environment.C3P0_ACQUIRE_INCREMENT,2);
        jpaProperties.put(org.hibernate.cfg.Environment.C3P0_IDLE_TEST_PERIOD,2000);
        jpaProperties.put(org.hibernate.cfg.Environment.C3P0_TIMEOUT,2000);
        jpaProperties.put(org.hibernate.cfg.Environment.C3P0_MAX_STATEMENTS,10);


        /*jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.setProperty("hibernate.show_sql", getPropertyFormEnv("hibernate.show_sql"));
        jpaProperties.setProperty("hibernate.format_sql", getPropertyFormEnv("hibernate.format_sql"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", getPropertyFormEnv("hibernate.hbm2ddl.auto")); //update validate
        jpaProperties.setProperty("cache.use_second_level_cache", "true");
        jpaProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.SingletonEhCacheRegionFactory");
        jpaProperties.setProperty("cache.use_query_cache", "true");
        jpaProperties.setProperty("connection.isolation", "2");
        jpaProperties.setProperty("use_identifier_rollback", "true");
        jpaProperties.setProperty("hibernate.c3p0.max_size", "20");
        jpaProperties.setProperty("hibernate.c3p0.min_size", "1");
        jpaProperties.setProperty("c3p0.acquire_increment", "2");
        jpaProperties.setProperty("c3p0.idle_test_period", "2000");
        jpaProperties.setProperty("c3p0.timeout", "2000");
        jpaProperties.setProperty("c3p0.max_statements", "10");*/


        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);


        //通过扫描下面这个包的方式注册到Hibernate
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.github.izhangzhihao.SpringMVCSeedProject.Model");
        return localContainerEntityManagerFactoryBean;
    }

    /**
     * 配置 Spring 事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}
