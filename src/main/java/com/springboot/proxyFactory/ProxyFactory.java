package com.springboot.proxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.mapp.Ainmal;
import com.springboot.mapp.Cat;
//代理工厂
//@Component
public class ProxyFactory {
	// 目标对象
	@Autowired
	private Cat cat;//Cat实现了Ainmal接口

	public ProxyFactory(Cat cat) {
		this.cat=cat;
	}
	//创建代理对象
	@SuppressWarnings("unchecked")
	public Ainmal getProxyObject(CarAdd carAdd) {//传入需要扩展功能的类，在调用目标方法之前或之后进行扩展
	//
	return (Ainmal)Proxy.newProxyInstance(
			//目标对象的类加载器
			cat.getClass().getClassLoader(),
			//目标对象实现的接口数组，程序会在运行时生成代理类，代理类会实现给定的接口
			cat.getClass().getInterfaces(),
			//代理对象需要调用的处理器，代理对象调用方法，都由处理器的invoke方法处理，调用目标对象中的方法
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//carAdd.before();
						Object rest=method.invoke(cat, args);
						//carAdd.after();
						return rest;
					}
				});
					
	}
}
