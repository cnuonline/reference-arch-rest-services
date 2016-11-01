package com.arch.api.restful.boot.async;

import com.arch.api.restful.boot.async.CustomAsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * Created by SRINIVASULU on 30/10/16.
 */
@Configuration
@EnableAsync
@ImportResource({ "classpath*:/rest_config.xml" })
public class CustomAsyncConfig implements AsyncConfigurer {

   @Autowired
    ThreadPoolTaskExecutor customThreadPoolTaskExecutor;

    @Bean
    public CustomAsyncBean customAsyncBean(){
        return new CustomAsyncBean();
    }

    @Override
    public Executor getAsyncExecutor() {
       /* ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("CustomeExecutor-");
        executor.initialize();*/
        return customThreadPoolTaskExecutor;
    }

    @Override
    @Bean
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncUncaughtExceptionHandler();
    }
}
