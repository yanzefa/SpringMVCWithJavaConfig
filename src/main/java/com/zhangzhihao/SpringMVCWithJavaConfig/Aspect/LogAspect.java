package com.zhangzhihao.SpringMVCWithJavaConfig.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.zhangzhihao.SpringMVCSeedProject.*.*.*(..)))")
    public void LogToDB(JoinPoint joinPoint, Throwable ex) {
        //出错行
        int lineNumber = ex.getStackTrace()[0].getLineNumber();
        //方法签名
        Signature signature = joinPoint.getSignature();
        //参数
        Object[] args = joinPoint.getArgs();
        //拼接参数
        StringBuilder argString = new StringBuilder();
        if (args.length > 0)
            for (Object arg : args)
                argString.append(arg);
        logger.error("方法" + signature, "参数" + argString, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }
}
