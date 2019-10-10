package com.springboot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.json.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.enty.User;
import com.springboot.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 刘彦军
 */

@Controller
public class LoginController {

	@Autowired
	private User user;

	// 使用thymeleaf模板引擎，需要pom中引入thymeleaf-streat jar包，跳转到templates下的html页面，
	// templates目录下的资源属于受保护的，必须通过controller进行
	// 跳转，springbooot底层默认配置了跳转前缀为prefix=“templates”，后缀为suffix=“html”
	// 使用rest风格进行动态跳转
	@ApiOperation("页面跳转")
	@GetMapping("/url/{pageName}")
	public String login(Model model, @PathVariable("pageName") String pageName) {
		model.addAttribute("user", this.user);

		return pageName;
	}

	@ApiOperation("登陆")
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,MultipartFile file) {
		//request.getSession();如果cookie中有sessionId那么会获取你原来的session对象，
		//如果通过sessionid获取不到session（比如服务的sesion失效），那么会重新创建新的session对象
		//如果没有sessionid那么会创建新的session对象，并会创建cookie对象，将sessionid存储在cookie中，下次访问浏览器在携带
		//session默认在服务器存储时间为30分钟，只要30分钟之内发起一次请求，那么时间会重置为30分钟
		//HttpSession session = request.getSession();
		//设置有效时间1个小时
		//session.setMaxInactiveInterval(60*60);
		//设置session立马失效，比如退出登录场景
		//session.invalidate();
		User user2 = new User();
		user2.setHeadImg("img");
		user2.setName("liuyanjun");
		user2.setId(26);
		String geneJsonWebToken = JwtUtils.geneJsonWebToken(user2);
		System.out.println("geneJsonWebToken----"+geneJsonWebToken);
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie : cookies) {
				System.out.println("cookie的key是：" + cookie.getName() + "cookie的value是：" + cookie.getValue());
				if("token".equals(cookie.getName())) {
					Claims checkJWT = JwtUtils.checkJWT(cookie.getValue());	
					System.out.println("校验token之后----"+checkJWT);
				}
			}
		} else {
			javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("token", geneJsonWebToken);
			// 设置有效时间，会保存在浏览器磁盘，默认保存在浏览器内存，浏览器关闭cookie消失
			cookie.setMaxAge(3600 * 24);
			// 可以设置路径，只有访问此路径，才会携带cookie到浏览器
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		//FileUtils.copyInputStreamToFile(source, destination);
		//com.meditrusthealth.fast.common.core.utils.FileUtils.copyTo(source, desc);
		// new ClassPathXmlApplicationContext("");
		//file.transferTo(arg0);
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("modelAndView", "登陆的账号："+name+"     密码："+pwd);
		return modelAndView;

	}
@GetMapping("test")
@ResponseBody
	public String test(String name,@RequestParam(defaultValue="1")int age) {
		return "name"+name+"age"+age+"你好啊";
	}

@GetMapping("download")
@ResponseBody
	public void download(HttpServletResponse response,HttpServletRequest request) throws IOException {
	String fileName = "zookeeper.tar.gz";// 文件名
	String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
	//String realPath = request.getServletContext().getRealPath("");
	System.out.println(path);
	//System.out.println(realPath);
    if (fileName != null) {
        //设置文件路径
        File file = new File(path+"static",fileName);
        if (file.exists()) {
        	System.out.println("======================");
        	ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
            outputStream.write(readFileToByteArray);
            outputStream.flush();
            outputStream.close();
        }
    }

	}
}
