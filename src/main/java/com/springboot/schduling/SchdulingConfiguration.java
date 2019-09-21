package com.springboot.schduling;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
@SpringBootConfiguration
public class SchdulingConfiguration implements SchedulingConfigurer,AsyncConfigurer {
	  //将线程池注册到异步任务中
		@Override
		public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
			TaskScheduler   asyncExecutor = getAsyncExecutor();
			taskRegistrar.setTaskScheduler(asyncExecutor);
			
		}
		//创建定时任务线程池
		@Override
		@Bean()
		public ThreadPoolTaskScheduler  getAsyncExecutor() {
			ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
			scheduler.setPoolSize(3);
			//线程名称，设置好了之后可以方便我们定位处理任务所在的线程池
			scheduler.setThreadNamePrefix("Lyj-task-");
			//该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
			scheduler.setAwaitTerminationSeconds(60);
			//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
			scheduler.setWaitForTasksToCompleteOnShutdown(true);
			return scheduler;
		}
		
		/*
		* 异步任务 异常处理
		*/
		@Override
		public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler()
		{
		return new SimpleAsyncUncaughtExceptionHandler();
		}
		
}
