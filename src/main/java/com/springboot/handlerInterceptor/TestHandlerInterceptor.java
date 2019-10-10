package com.springboot.handlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

//拦截器 SpringMVC的处理器拦截器，类似于Servlet开发中的过滤器Filter，用于对请求进行拦截和处理。
/**
 * 定义一个Interceptor 非常简单方式也有几种，我这里简单列举两种 1、类 要实现Spring 的HandlerInterceptor 接口 2、类
 * 继承实现了HandlerInterceptor 接口的类，例如 已经提供的实现了HandlerInterceptor
 * 接口的抽象类HandlerInterceptorAdapter
 * 
 * @author 刘彦军 ！！！除了定义此类，还需要将自定义的拦截器，注册到WebMvcConfigurer中，拦截器才起作用
 *         之前我们在xml中配置拦截路径等，springboot我们需要继承WebMvcConfigurerAdapter类
 *         不过springBoot2.0以上 WebMvcConfigurerAdapter 方法过时， 有两种替代方案：
 *         1、继承WebMvcConfigurationSupport 2、实现WebMvcConfigurer
 *         但是继承WebMvcConfigurationSupport会让Spring-boot对mvc的自动配置失效。根据项目情况选择。
 *         现在大多数项目是前后端分离，并没有对静态资源有自动配置的需求所以继承WebMvcConfigurationSupport也未尝不可。
 */
@Slf4j
public class TestHandlerInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(TestHandlerInterceptor.class);

	// 最终拦截
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("最终拦截--在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	// 后置拦截+统计总访问数
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("后置拦截--请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后");
		// controller方法调用之前,获取servlet上下文对象，从中取num
		ServletContext servletContext = request.getServletContext();
		Object num = servletContext.getAttribute("num");
		if (num == null) {
			servletContext.setAttribute("num", 1);
		} else {
			int num2 = (Integer) num;
			num2++;
			servletContext.setAttribute("num", num2);
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	//
	// 前置拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("前置拦截在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理");
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie : cookies) {
				System.out.println("cookie的key是：" + cookie.getName() + "cookie的value是：" + cookie.getValue());
				if ("token".equals(cookie.getName())) {
					Claims checkJWT = JwtUtils.checkJWT(cookie.getValue());
					System.out.println("校验token之后----" + checkJWT);
					if (checkJWT != null) {
						return HandlerInterceptor.super.preHandle(request, response, handler);
					} 
				}
			}
		}
		System.out.println(request.getRequestURI());
		response.sendRedirect("/login");
		return false;
	}

}
