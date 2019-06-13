package com.springboot.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.enty.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 刘彦军
 */

@Controller
public class LoginController {
	
	@Autowired
	private	User user;
	
	//使用thymeleaf模板引擎，需要pom中引入thymeleaf-streat jar包，跳转到templates下的html页面，
	//templates目录下的资源属于受保护的，必须通过controller进行
	//跳转，springbooot底层默认配置了跳转前缀为prefix=“templates”，后缀为suffix=“html”
	//使用rest风格进行动态跳转
	@ApiOperation("页面跳转")
	@GetMapping("/url/{pageName}")
	public String login(Model model ,@PathVariable("pageName") String pageName) {
		model.addAttribute("user", this.user);
	
		return pageName;
	}
	@ApiOperation("登陆")
	@GetMapping("/login")
	public ModelAndView login() {
		// new ClassPathXmlApplicationContext("");
		ModelAndView modelAndView=	new  ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("modelAndView","使用modelAndView添加数据模型到前");
		return modelAndView ;
		
	}
	
}
