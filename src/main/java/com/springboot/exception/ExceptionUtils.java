package com.springboot.exception;

import com.springboot.myEnum.MyEnum;
import com.springboot.response.ResponseMessage;

public class ExceptionUtils {
	
	public static ResponseMessage<?>creatError(MyEnum myEnum){
		
		return new ResponseMessage<>(myEnum.getCode(),myEnum.getMessage());
	}

}
