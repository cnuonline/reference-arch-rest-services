package com.arch.api.restful.boot.async;

import org.springframework.scheduling.annotation.Async;

/**
 * Created by SRINIVASULU on 31/10/16.
 */

public class CustomAsyncBean {

    @Async("customThreadPoolTaskExecutor")
    public void timeConsumingMethod() throws InterruptedException {
        System.out.println("Execute method with configured executor - "
                + Thread.currentThread().getName());
        Thread.sleep(1000*2);
        System.out.println("I'm should be second!");

    }
}
