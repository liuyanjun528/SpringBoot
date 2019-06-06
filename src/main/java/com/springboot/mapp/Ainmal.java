package com.springboot.mapp;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.enty.Stu;



@Component
public interface Ainmal extends BaseMapper<Stu> {
	
	public void eat();
	
	
		
}
