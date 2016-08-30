package com.github.izhangzhihao.SpringMVCSeedProject.Controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.LogUtils.LogToDB;

@ControllerAdvice
public class HandlerExceptionController {

    /**
     * 无权限访问跳转
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        return new ModelAndView("../../403");
    }

    /**
     * 全局Controller异常处理
     * @param ex 异常
     * @return 跳转出错页面
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handlerExceptionMethod(Exception ex) {

        //将错误信息记录到数据库
        LogToDB(ex);

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
