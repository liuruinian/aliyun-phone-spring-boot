package io.github.liuruinian.phone.threadpool;

import io.github.liuruinian.phone.properties.AliPhoneProperties;
import io.github.liuruinian.phone.properties.RejectPolicy;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public abstract class AbstractAsyncThreadPoolExecutor implements AsyncThreadPoolExecutor {

    protected final AliPhoneProperties prop;

    private final ThreadPoolExecutor executor;

    public AbstractAsyncThreadPoolExecutor(AliPhoneProperties properties) {
        this.prop = properties;
        AliPhoneProperties.ThreadPool pool = prop.getThreadPool();
        executor = new ThreadPoolExecutor(
                pool.getCorePoolSize(),
                pool.getMaximumPoolSize(),
                pool.getKeepAliveTime(),
                pool.getUnit(),
                new LinkedBlockingQueue<>(pool.getQueueCapacity()),
                new DefaultThreadFactory(),
                buildRejectedExecutionHandler(pool.getRejectPolicy()));
    }

    /**
     * @param type RejectPolicy
     * @return RejectedExecutionHandler
     */
    protected static RejectedExecutionHandler buildRejectedExecutionHandler(RejectPolicy type) {
        if (type == null) {
            return new ThreadPoolExecutor.AbortPolicy();
        }
        switch (type) {
            case Caller:
                return new ThreadPoolExecutor.CallerRunsPolicy();
            case Discard:
                return new ThreadPoolExecutor.DiscardPolicy();
            case DiscardOldest:
                return new ThreadPoolExecutor.DiscardOldestPolicy();
            default:
                return new ThreadPoolExecutor.AbortPolicy();
        }
    }

    /**
     * @param command command
     */
    @Override
    public final void execute(Runnable command) {
        if (command != null) {
            submit0(command);
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        RunnableFuture<T> ftask = newTaskFor(task);
        ftask.run();
        return ftask;
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable);
    }

    /**
     * @param command command
     */
    protected void submit0(Runnable command) {
        if (command == null) {
            return;
        }
        executor.execute(command);
    }

    protected static class DefaultThreadFactory implements ThreadFactory {

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "phone-protection-async-thread-";
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }
}
