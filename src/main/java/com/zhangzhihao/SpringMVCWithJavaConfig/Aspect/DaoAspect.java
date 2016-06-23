package com.zhangzhihao.SpringMVCWithJavaConfig.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {
	/*@Before("execution(public int com.zhihao.AOP.ArithmeticCalculatorImpl.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();

		System.out.println("+++++++++++++++++The method " + methodName + " begins with " + Arrays.asList(args));
	}*/
}
