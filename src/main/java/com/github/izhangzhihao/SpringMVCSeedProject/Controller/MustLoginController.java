package com.github.izhangzhihao.SpringMVCSeedProject.Controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MustLoginController {
    /**
     * 必须登录才可以进入
     */
    //@AuthByRole()
    //@Auth
    @RequiresRoles("administrator") //需要administrator Role
    //@RequiresPermissions("user:create") 需要user:create权限
    //@RequiresGuest 要求当前用户是一个访客
    //@RequiresAuthentication  需要登录
    @RequestMapping("/MustLogin")
    public String MustLogin(){
        return "/MustLogin/MustLogin";
    }
}
