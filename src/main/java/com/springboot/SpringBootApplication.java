package com.springboot;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication注解包含，@EnableAutoConfiguration，自动配置，启动时会加载
//spring-boot-autoconfigure-2.0.4.RELEASE.jar下meta-inf下spring.factories配置文件
//@SpringBootConfiguration，spring配置类，相当于ioc容器  ClassPathXmlApplicationContext
//@ComponentScan 扫包器
//扫描mapp接口的位置注解
@MapperScan("com.springboot.mapp")
@ServletComponentScan//监听器注解
@EnableSwagger2
@Slf4j

@org.springframework.boot.autoconfigure.SpringBootApplication
//@EnableScheduling
public class SpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
//	Logger log=LoggerFactory.getLogger("MySpringBootApplication");
		String md5=DigestUtils.md5Hex(DigestUtils.md5Hex("123456".getBytes()));
		log.info("md5加密：。。。。。。"+md5);
		
	}

	// springboot2.0 写法与1.5有所不同  http跳转到https
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }
 
    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8080);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(8443);
      
        return connector;
    }

}
