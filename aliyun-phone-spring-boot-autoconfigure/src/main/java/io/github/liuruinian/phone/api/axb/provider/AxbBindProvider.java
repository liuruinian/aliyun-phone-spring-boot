package io.github.liuruinian.phone.api.axb.provider;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import io.github.liuruinian.phone.domain.axb.AxbBindRequest;
import io.github.liuruinian.phone.exception.BindAxbException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface AxbBindProvider {

    /**
     * bind axb
     *
     * @param request AxbBindRequest - https://help.aliyun.com/document_detail/400482.html#api-detail-35
     * @return BindAxbResponse - https://help.aliyun.com/document_detail/400482.html#api-detail-40
     * @see AxbBindRequest
     * @see BindAxbResponse
     */
    BindAxbResponse bindAxb(AxbBindRequest request) throws BindAxbException;
}
