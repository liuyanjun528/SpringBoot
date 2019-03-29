package com.springboot.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//监听器
//　在 Servlet API 中有一个 ServletContextListener 接口，
//它能够监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期。
//ServletContext 对象是一个为整个 web 应用提供共享的内存，任何请求都可以访问里面的内容
@WebListener
public class MyListener implements ServletContextListener{
	/**
	  * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，
	  * 并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
	  */
	@Override
	public void contextDestroyed(ServletContextEvent var1) {
		//销毁前可存入数据库，持久保存数据
		System.out.println("销毁====num的次数"+var1.getServletContext().getAttribute("num"));
	}
	/**
	  * 当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
	  */
	@Override
	public void contextInitialized(ServletContextEvent var1) {
		//统计总人数，初始化容器时，可从数据库取出数据，存入ServletContext域中，拦截器中在获取到
		System.out.println("初始化===");
	}
}
