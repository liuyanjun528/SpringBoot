package com.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.myEnum.MyEnum;
import com.springboot.response.ResponseMessage;

@RestControllerAdvice
public class MyExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

	//@RestControllerAdvice组合@ExceptionHandler的方法会作用于requestMapper()注解的方法，拦截控制层的异常
	@ExceptionHandler(Exception.class)
	public ResponseMessage<?> exception(Exception e) {
		log.info("------------拦截异常:{}");
		log.error(MyEnum.ERROR.getMessage(), e);
		return ExceptionUtils.creatError(MyEnum.ERROR);
	}

}
