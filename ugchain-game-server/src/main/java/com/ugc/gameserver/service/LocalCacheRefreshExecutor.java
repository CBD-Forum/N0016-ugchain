package com.ugc.gameserver.service;

import java.util.concurrent.*;

/**
 * Created by yuanshichao on 2016/12/14.
 */
public class LocalCacheRefreshExecutor {

    private static final int CORE_POOLSIZE = 0;
    private static final int MAX_POOLSIZE = 200;

    private static final int ALIVE_TIME = 60;

    private static final ExecutorService EXECUTOR
            = new ThreadPoolExecutor(CORE_POOLSIZE, MAX_POOLSIZE, ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<>());

    public static void execute(Runnable command) {
        EXECUTOR.execute(command);
    }
}
