package com.thirdparty.ThirdPartyWithMysqlStorage.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfig {
    @Bean("customThreadPool")
    public ExecutorService customThreadPool() {
        return new CustomThreadPool(5,20, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
        
    }

    @Bean("singleThreaded")
    public ExecutorService singleThreadedExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean("cachedThreadPool")
    public ExecutorService cachedThreadPool() {
        return Executors.newCachedThreadPool();
    }
}
