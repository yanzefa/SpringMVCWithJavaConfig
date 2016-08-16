package com.github.izhangzhihao.SpringMVCSeedProject.Interceptor;


import com.github.izhangzhihao.SpringMVCSeedProject.Annotation.Auth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);

            //没有声明需要权限,或者声明不验证权限
            if(auth == null || !auth.validate())
                return true;
            else{
                Boolean IsLogin=false;
                if(request.getSession().getAttribute("User")!=null){
                    IsLogin=true;
                }
                if(IsLogin)//如果验证成功返回true
                    return true;
                else//如果验证失败
                {
                    //返回到登录界面
                    response.sendRedirect("Account/Login");
                    return false;
                }
            }
        }
        else
            return true;
    }
}
