package com.zhangzhihao.SpringMVCWithJavaConfig.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({Exception.class})
    public ModelAndView HandlerMethod(Exception ex) {


        //将错误信息记录到数据库
        //LogToDB(ex); //不再重复记录异常

        ModelAndView modelAndView = new ModelAndView("../../500");
        modelAndView.addObject("MSG", ex.toString());
        modelAndView.addObject("Line", ex.getStackTrace()[0].getLineNumber());
        modelAndView.addObject("Method", ex.getStackTrace()[0].getMethodName());
        Writer writer = new StringWriter();
        //客户端输出一下，打开F12可以看到
        ex.printStackTrace(new PrintWriter(writer));
        modelAndView.addObject("detailed", writer.toString());
        return modelAndView;
    }
}
