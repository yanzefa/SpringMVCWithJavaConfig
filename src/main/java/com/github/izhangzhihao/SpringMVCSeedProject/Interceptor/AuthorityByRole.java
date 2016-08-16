package com.github.izhangzhihao.SpringMVCSeedProject.Interceptor;


import com.github.izhangzhihao.SpringMVCSeedProject.Annotation.AuthByRole;
import com.github.izhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorityByRole extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthByRole auth = ((HandlerMethod) handler).getMethodAnnotation(AuthByRole.class);

            if(auth==null){
                return true;
            }
            AuthorityType[] authorityTypes = auth.AuthorityType();
            for (AuthorityType authorityType:authorityTypes){
                System.out.println(authorityType);
            }
            //没有声明需要权限,或者声明不验证权限
            if(authorityTypes.length == 0 || !auth.validate())
                return true;
            else{
//                if(request.getSession().getAttribute("User")!=null){
//                    return true;
//                }else//如果验证失败
//                {
//                    //返回到登录界面
//                    response.sendRedirect("Account/Login");
//                    return false;
//                }
                response.sendRedirect("Account/Login");
                return false;
            }
        }
        else
            return true;
    }
}
