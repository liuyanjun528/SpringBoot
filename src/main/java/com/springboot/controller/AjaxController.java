package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meditrusthealth.fast.common.core.utils.FastCodeUtils;

@RestController
public class AjaxController {
@GetMapping("/ajax")
	public String ajax(String name) {
	String encrypt = FastCodeUtils.encrypt("liuyanjun");
	System.out.println(encrypt);
	String decrypt = FastCodeUtils.decrypt(encrypt);
	System.out.println(decrypt);
		return "ajax调用成功，传过来参数为"+name;
	}
}
