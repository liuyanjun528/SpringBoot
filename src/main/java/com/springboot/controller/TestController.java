package com.springboot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	//@GetMapping("swagger-ui.html")
	public String Test(HttpServletResponse resp) {
		resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		return "index";
	}
}