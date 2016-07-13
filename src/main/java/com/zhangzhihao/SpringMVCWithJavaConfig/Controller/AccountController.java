package com.zhangzhihao.SpringMVCWithJavaConfig.Controller;

import com.zhangzhihao.SpringMVCWithJavaConfig.Model.User;
import com.zhangzhihao.SpringMVCWithJavaConfig.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static com.zhangzhihao.SpringMVCWithJavaConfig.Utils.LogUtils.LogToDB;


@SuppressWarnings("JavaDoc")
@Controller
@RequestMapping("/Account")
public class AccountController {
	@Autowired
	UserService userService;


	/**
	 * 转向登录界面
	 *
	 * @return 登录界面
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String LoginPage() {
		return "Account/Login";
	}

	/**
	 * 接收用户登录传参，判断是否登陆成功
	 *
	 * @param UserName
	 * @param Password
	 * @param session
	 * @return
	 */
	@Cacheable("springCache")
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String Login(@RequestParam("UserName") String UserName, @RequestParam("Password") String Password, HttpSession session) {
		String userName = UserName.trim();
		User LoginUser = null;
		if (userName != null && userName != "") {
			try {
				LoginUser = userService.getById(userName);
			} catch (Exception e) {
				LogToDB(e);
			}
		}
		assert LoginUser != null;
		if (LoginUser.getPassWord().equals(Password.trim())) {
			session.setAttribute("User", LoginUser);
			return "redirect:/MustLogin";//为防止重复提交数据，使用重定向视图

		} else {
			return "/Account/Login";
		}
	}
}
