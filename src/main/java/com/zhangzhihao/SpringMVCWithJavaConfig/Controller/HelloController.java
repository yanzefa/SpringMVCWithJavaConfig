package com.zhangzhihao.SpringMVCWithJavaConfig.Controller;

import com.zhangzhihao.SpringMVCWithJavaConfig.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@Autowired
	private UserService service;

	@RequestMapping("/Hello")
	public String Hello(){
		System.out.println(service.toString());
		return "hello";
	}
}
