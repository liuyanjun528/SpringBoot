package com.springboot.handlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//拦截器
public class TestHandlerInterceptor implements HandlerInterceptor{
	//最终拦截
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("最终拦截--在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作");
		//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	//后置拦截
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("后置拦截--请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后");
		//请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	//前置拦截统计总访问数
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		//controller方法调用之前
		ServletContext servletContext=request.getServletContext();
		Object num=	servletContext.getAttribute("num");
		if(num==null) {
			servletContext.setAttribute("num", 1);
		}else {
			int	 num2=(Integer)num;
			num2++;
			servletContext.setAttribute("num", num2);
		}
		System.out.println("前置拦截controller方法调用之前");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
