package io.github.liuruinian.phone.threadpool;

import io.github.liuruinian.phone.properties.AliPhoneProperties;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class DefaultAsyncThreadPoolExecutor extends AbstractAsyncThreadPoolExecutor {

    public DefaultAsyncThreadPoolExecutor(AliPhoneProperties properties) {
        super(properties);
    }
}
