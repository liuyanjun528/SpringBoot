package com.springboot.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.jexl2.internal.AbstractExecutor.Get;
import org.apache.http.HttpResponse;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.QRCodeService;

@RestController
public class QRCodeController {
	
	@Autowired
	private QRCodeService  qRCodeService;
	
	@GetMapping("/getQRCode")
	public  String  getQRCode(HttpServletResponse resp) throws Exception {
		qRCodeService.getQRCode(resp);
		return "生成二维码完成";
	}

}
