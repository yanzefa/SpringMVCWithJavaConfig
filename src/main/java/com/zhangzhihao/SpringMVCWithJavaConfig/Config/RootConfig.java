package com.zhangzhihao.SpringMVCWithJavaConfig.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "com.zhangzhihao.SpringMVCWithJavaConfig",
		excludeFilters =
				{@ComponentScan.Filter(
						type = FilterType.ANNOTATION,
						value = {EnableWebMvc.class, ControllerAdvice.class, Controller.class})})
public class RootConfig {

	@Value("${jdbc.userName}") String user;
	@Value("${jdbc.password}") String password;
	@Value("${jdbc.connectionURL}") String url;
	@Value("${jdbc.driverClass}") String driverClass;

	@Bean
	public ComboPooledDataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setUser(user);
//		dataSource.setPassword(password);
//		dataSource.setJdbcUrl(url);
//		dataSource.setDriverClass(driverClass);
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://10.16.155.241:8081/SpringMVCSeedProject?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
		dataSource.setPassword("root");
		dataSource.setUser("root");
		dataSource.setMaxPoolSize(10);
		dataSource.setInitialPoolSize(5);
		return dataSource;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public LocalSessionFactoryBean getSessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(getDataSource());
//		URL resource1 = RootConfig.class.getResource("Hibernate.cfg.xml");
		Resource resource = new ClassPathResource("Hibernate.cfg.xml");
		localSessionFactoryBean.setConfigLocation(resource);
		localSessionFactoryBean.setPackagesToScan("com.zhangzhihao.SpringMVCWithJavaConfig.Model");
		return localSessionFactoryBean;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTransactionManager getTransactionManager() throws PropertyVetoException {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory( getSessionFactory().getObject());
		return hibernateTransactionManager;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTemplate getHibernateTemplate() throws PropertyVetoException {
		return new HibernateTemplate(getSessionFactory().getObject());
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTransactionManager txManager() throws PropertyVetoException {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(getSessionFactory().getObject());
		return txManager;
	}
}
