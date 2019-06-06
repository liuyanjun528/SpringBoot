package com.springboot.myEnum;




public enum MyEnum {
	
	SUCESS("200","消息处理成功"),
	ERROR("500","系统繁忙");
	
	private String code;
	private String message;
	private MyEnum(String code,String message) {
		this.code=code;
		this.message=message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
