package com.springboot.enty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p> 
 *  user
 * </p> 
 * 
 * @author 95303 2018-09-06 21:40:19 907-
 */
//指定自定义配置文件的位置
@PropertySource(value= {"classpath:user.properties"})
@Component
//读取配置文件，以什么前缀读取
@ConfigurationProperties(prefix="user1")
public class User implements Serializable {
   

	/**
     * name 描述:
     */
    private String name;

    /**
     * sex 描述:
     */
    private String sex;

    /**
     * age 描述:
     */
    private Integer age;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null || name.trim().isEmpty() ) ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = (sex == null || sex.trim().isEmpty() ) ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
   	public String toString() {
   		return "User [name=" + name + ", sex=" + sex + ", age=" + age + "]";
   	}
    
    @Bean
    public List<Object> list() {
    	
    	return new ArrayList<>();
    }
}