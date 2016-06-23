package com.zhangzhihao.SpringMVCWithJavaConfig.Config;

import com.zhangzhihao.SpringMVCWithJavaConfig.Interceptor.AuthInterceptor;
import com.zhangzhihao.SpringMVCWithJavaConfig.Interceptor.AuthorityByRole;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.zhangzhihao.SpringMVCWithJavaConfig",
		includeFilters =
				{@ComponentScan.Filter(
						type = FilterType.ANNOTATION,
						value = {ControllerAdvice.class, Controller.class})})
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}

	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 *
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new AuthInterceptor());
		registry.addInterceptor(new AuthorityByRole());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
