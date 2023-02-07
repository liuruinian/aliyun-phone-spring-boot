package io.github.liuruinian.phone.api.axn.processor;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.phone.exception.BindAxnException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface BindAxnPostProcessor {

    /**
     * @param request BindAxnRequest
     * @see BindAxnRequest
     */
    void postProcessBeforeBind(BindAxnRequest request) throws BindAxnException;

    /**
     * @param response BindAxnResponse
     * @see BindAxnResponse
     */
    void postProcessAfterBind(BindAxnResponse response) throws BindAxnException;
}
