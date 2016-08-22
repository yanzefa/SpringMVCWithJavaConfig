package com.github.izhangzhihao.SpringMVCSeedProject.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties") //导入资源文件
public class JPAConfig {
    @Value("${jdbc.userName}")
    String user;
    @Value("${jdbc.password}")
    String password;
    @Value("${jdbc.connectionURL}")
    String url;
    @Value("${jdbc.driverClass}")
    String driverClass;
    @Value("${jdbc.initialPoolSize}")
    Integer initPoolSize;
    @Value("${jdbc.maxPoolSize}")
    Integer maxPoolSize;

    /**
     * 配置数据源
     */
    @Bean
    public ComboPooledDataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClass(driverClass);
        dataSource.setInitialPoolSize(initPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        return dataSource;
    }

    /**
     * 配置 Hibernate 的 SessionFactory 实例: 通过 Spring 提供的 LocalSessionFactoryBean 进行配置
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        //配置数据源属性
        localContainerEntityManagerFactoryBean.setDataSource(getDataSource());

        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        jpaProperties.setProperty("hibernate.format_sql", "true");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "validate"); //update validate
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
        jpaProperties.setProperty("c3p0.max_statements", "10");


        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);


        //通过扫描下面这个包的方式注册到Hibernate
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.github.izhangzhihao.SpringMVCSeedProject.Model");
        return localContainerEntityManagerFactoryBean;
    }

    /**
     * 配置 Spring 事务管理器
     */
    @Bean
    public PlatformTransactionManager getTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        try {
            jpaTransactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return jpaTransactionManager;
    }
}
