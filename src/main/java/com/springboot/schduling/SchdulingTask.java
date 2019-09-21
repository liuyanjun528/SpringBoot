package com.springboot.schduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class SchdulingTask{
	private   static final SimpleDateFormat dataFormat=new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate=3000)
	public  void schdulingTask() {
		String threadName = Thread.currentThread().getName();
		System.out.println("当前线程名字是"+threadName+"每隔三秒执行一次定时任务"+dataFormat.format(new Date()));
	}
  
}
