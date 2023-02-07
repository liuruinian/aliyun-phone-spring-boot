package io.github.liuruinian.phone.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface AsyncThreadPoolExecutor {

    /**
     * @param task task
     */
    void execute(Runnable task);

    /**
     * @param task task
     * @return T
     */
    <T> Future<T> submit(Callable<T> task);
}
