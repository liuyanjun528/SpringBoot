package com.springboot.proxyFactory;


import java.lang.reflect.Modifier;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect//切面
/**
 * springAOP，在程序运行过程中，如果运行到切点匹配的（表达式时）,jvm会动态的生成代理类，如果拦截到的类有实现接口，
 * 那么使用jdk动态代理，否则使用cglbi，继承的方式进行生成代理类，类为finli不能被代理会报错，方法为静态，或者finli类型，aop的扩展功能不会被执行
 */
public class CarAdd {
	//切点，第一个*表示修饰符，切点抽取出来，用一个方法表示，各种通知只要调用这个切点注解的方法即可，避免代码的重复性
	@Pointcut("execution(* com.springboot.controller.*.*(..))")//包下的所有类中的所有方法，..表示任意参数，都会生成代理类
	public void qieDian() {}
	
	/**
	 * Signature getSignature();	获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
	Object[] getArgs();	获取传入目标方法的参数对象
	Object getTarget();	获取被代理的对象
	Object getThis();	获取代理对象
	 * @param joinPoint
	 */
	@Before("qieDian()")
	public  void before(JoinPoint joinPoint) {
		System.out.println(joinPoint);//joinPoint封装了被拦截的方法信息
		System.out.println(joinPoint.getSignature());
		System.out.println("获取目标方法的方法名"+joinPoint.getSignature().getName());//
		System.out.println("目标方法所属类的简单类名:" +joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        System.out.println("获取被代理的对象"+joinPoint.getTarget());
        System.out.println("获取代理对象"+joinPoint.getThis());
        //spring 自带获取request的方式
        
        ServletRequestAttributes sqa=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=  sqa.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();   //这个方法取客户端ip"不够好"
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
      System.out.println("请求url=" + requestURI + ",客户端ip=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名=" + declaringTypeName + ",方法名=" + methodName + ",入参=" + args);
      System.out.println("猫吃饭之前");
	}
	//后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
	@After("qieDian()")
	public void after() {
		System.out.println("猫吃饭之后");
	}
}
