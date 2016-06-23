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
//@ImportResource("classpath:Spring.xml")
@PropertySource("classpath:db.properties") //导入资源文件
@ComponentScan(basePackages = "com.zhangzhihao.SpringMVCWithJavaConfig",
		excludeFilters =//不扫描以下包
				{@ComponentScan.Filter(
						type = FilterType.ANNOTATION,
						value = {EnableWebMvc.class, ControllerAdvice.class, Controller.class})})
public class RootConfig {

	@Value("${jdbc.userName}") String user;
	@Value("${jdbc.password}") String password;
	@Value("${jdbc.connectionURL}") String url;
	@Value("${jdbc.driverClass}") String driverClass;
	@Value("${jdbc.initPoolSize}") Integer initPoolSize;
	@Value("${jdbc.maxPoolSize}") Integer maxPoolSize;

	/**
	 * 配置数据源
	 * @return 数据源
	 * @throws PropertyVetoException
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
	 * @return
	 * @throws PropertyVetoException
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public LocalSessionFactoryBean getSessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		//配置数据源属性
		localSessionFactoryBean.setDataSource(getDataSource());
		//配置文件位置
		Resource resource = new ClassPathResource("Hibernate.cfg.xml");
		localSessionFactoryBean.setConfigLocation(resource);
		//通过扫描下面这个包的方式注册到Hibernate
		localSessionFactoryBean.setPackagesToScan("com.zhangzhihao.SpringMVCWithJavaConfig.Model");
		return localSessionFactoryBean;
	}

	/**
	 * 配置 Spring 的声明式事务 配置事务管理器
	 * @return
	 * @throws PropertyVetoException
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTransactionManager getTransactionManager() throws PropertyVetoException {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory( getSessionFactory().getObject());
		return hibernateTransactionManager;
	}

	/**
	 * 配置HibernateTemplate
	 * @return
	 * @throws PropertyVetoException
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTemplate getHibernateTemplate() throws PropertyVetoException {
		return new HibernateTemplate(getSessionFactory().getObject());
	}

	/*@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTransactionManager txManager() throws PropertyVetoException {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(getSessionFactory().getObject());
		return txManager;
	}*/
}
