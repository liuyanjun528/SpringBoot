package com.springboot.controller;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

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
	//获得虚拟机年轻代和老年代的垃圾回收器
	List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
	for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
		System.out.println(garbageCollectorMXBean.getName());
	}
		return "ajax调用成功，传过来参数为"+name;
	}
}
