package com.springboot.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.myEnum.MyEnum;
@Component
public class ResponseMessage<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private List<E> list;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public void sucess(MyEnum my){
	this.code=my.getCode();
	this.message=my.getMessage();
	}
	public ResponseMessage(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ResponseMessage() {
		
	}
	
	
}
