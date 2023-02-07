package io.github.liuruinian.phone.threadpool;

import io.github.liuruinian.phone.properties.AliPhoneProperties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class DefaultAsyncThreadPoolExecutor extends AbstractAsyncThreadPoolExecutor {

    private final ThreadPoolExecutor executor;

    public DefaultAsyncThreadPoolExecutor(AliPhoneProperties properties) {
        super(properties);
        AliPhoneProperties.ThreadPool pool = properties.getThreadPool();
        executor = new ThreadPoolExecutor(
                pool.getCorePoolSize(),
                pool.getMaximumPoolSize(),
                pool.getKeepAliveTime(),
                pool.getUnit(),
                new LinkedBlockingQueue<>(pool.getQueueCapacity()),
                new DefaultThreadFactory(),
                buildRejectedExecutionHandler(pool.getRejectPolicy()));
    }

    @Override
    protected void submit0(Runnable command) {
        if (command == null) {
            return;
        }
        executor.execute(command);
    }
}
