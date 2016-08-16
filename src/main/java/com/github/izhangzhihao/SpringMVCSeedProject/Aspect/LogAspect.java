package com.github.izhangzhihao.SpringMVCSeedProject.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class LogAspect {
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
        log.error("方法" + signature, "参数" + argString, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }
}
