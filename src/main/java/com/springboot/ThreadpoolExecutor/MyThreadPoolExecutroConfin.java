package com.springboot.ThreadpoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class MyThreadPoolExecutroConfin {
	@Bean(value="MyThreadPoolExecutroConfin")
	public ExecutorService getExecutorService() {
		ExecutorService  ExecutorService =	Executors.newFixedThreadPool(3);
		return ExecutorService;
	}
}
