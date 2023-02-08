package io.github.liuruinian.phone.api.axg.provider;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxgResponse;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import io.github.liuruinian.phone.exception.BindAxgException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface AxgBindProvider {

    /**
     * bing axg
     *
     * @param request AxgBindRequest - https://help.aliyun.com/document_detail/400485.html#api-detail-35
     * @return BindAxgResponse - https://help.aliyun.com/document_detail/400485.html#api-detail-40
     * @see AxgBindRequest
     * @see BindAxgResponse
     */
    BindAxgResponse bingAxg(AxgBindRequest request) throws BindAxgException;
}
