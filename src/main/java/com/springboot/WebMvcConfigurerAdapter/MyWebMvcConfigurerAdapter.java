package com.springboot.WebMvcConfigurerAdapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.springboot.handlerInterceptor.TestHandlerInterceptor;

//springboot也可以通过WebMvcConfigurer接口，来添加一些自己需要的一些功能，
//也可以继承WebMvcConfigurerAdapter类，WebMvcConfigurerAdapter也是实现自WebMvcConfigurer接口
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

	// 继承WebMvcConfigurerAdapter类，注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 传入自定义的拦截器
		registry.addInterceptor(new TestHandlerInterceptor())
				// 拦截所有的请求
				.addPathPatterns("/**")
				// 不拦截的url
				.excludePathPatterns("/login");
	}

	// 注册不通过contorller跳转至templates下的页面
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 注册一个contorller路径，在设置视图名称，访问contorller在进行跳转视图
		registry.addViewController("/form").setViewName("form");
	}

}
