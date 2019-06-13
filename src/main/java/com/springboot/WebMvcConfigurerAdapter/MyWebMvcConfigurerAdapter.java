package com.springboot.WebMvcConfigurerAdapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.springboot.handlerInterceptor.TestHandlerInterceptor;
/**
 * 其实以前都是继承WebMvcConfigurerAdapter类 不过springBoot2.0以上 WebMvcConfigurerAdapter 方法过时
 * 从源码上可以看出，WebMvcConfigurerAdapter类对WebMvcConfigurer接口进行了实现，不过都是空实现。
 * ，java版本1.8 接口可以定义defult方法，那么WebMvcConfigurerAdapter这个适配类也就没有意义了，所以被抛弃
 * 有两种替代方案：
1、继承WebMvcConfigurationSupport
2、实现WebMvcConfigurer
		注意！！！
		但是继承WebMvcConfigurationSupport会让Spring-boot对mvc的自动配置失效。
		比如说默认静态资源路径的访问会失效，
		根据项目情况选择。现在大多数项目是前后端分离，
		并没有对静态资源有自动配置的需求所以继承WebMvcConfigurationSupport也未尝不可。
 * @author 刘彦军
 *
 */
//springboot也可以通过WebMvcConfigurer接口，来添加一些自己需要的一些功能，
//也可以继承WebMvcConfigurerAdapter类，重写其中的方法，WebMvcConfigurerAdapter也是实现自WebMvcConfigurer接口
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
