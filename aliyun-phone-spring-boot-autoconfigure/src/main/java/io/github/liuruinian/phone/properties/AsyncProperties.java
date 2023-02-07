package io.github.liuruinian.phone.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@Data
@ConfigurationProperties(prefix = "phone.protection.async")
public class AsyncProperties {

    /**
     * 是否启用异步支持
     */
    private boolean enable = true;

    /**
     * 核心线程数量
     * 默认: 可用处理器数量 (Runtime.getRuntime().availableProcessors())
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 最大线程数量
     * 默认: 可用处理器数量 * 2 (Runtime.getRuntime().availableProcessors() * 2)
     */
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 线程最大空闲时间
     * 默认: 60
     */
    private long keepAliveTime = 60L;

    /**
     * 线程最大空闲时间单位
     * 默认: TimeUnit.SECONDS
     */
    private TimeUnit unit = TimeUnit.SECONDS;

    /**
     * 队列容量
     * 默认: Integer.MAX_VALUE
     */
    private int queueCapacity = Integer.MAX_VALUE;

    /**
     * 拒绝策略
     *
     * 可选值: Abort、Caller、Discard、DiscardOldest
     * 默认: Abort
     */
    private RejectPolicy rejectPolicy = RejectPolicy.Abort;
}
