package com.netty.chd02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 7/3/14.
 */
public class TimeServerHandlerExecutePool {
    private ExecutorService executer;
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
        executer =  new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public void execute(Runnable task){
        executer.execute(task);
    }
}
