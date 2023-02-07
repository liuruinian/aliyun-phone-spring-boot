package io.github.liuruinian.phone.axn.provider;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.phone.axn.domain.AxnBindExtensionRequest;
import io.github.liuruinian.phone.axn.domain.AxnBindRequest;
import io.github.liuruinian.phone.exception.BindAxnException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface AxnBindProvider {

    /**
     * bind axn
     *
     * @param request AxnBindRequest - https://help.aliyun.com/document_detail/400483.html#api-detail-35
     * @return BindAxnResponse - https://help.aliyun.com/document_detail/400483.html#api-detail-40
     * @see AxnBindRequest
     * @see BindAxnResponse
     */
    BindAxnResponse bindAxn(AxnBindRequest request) throws BindAxnException;

    /**
     * bind axn extension
     *
     * @param request AxnBindExtensionRequest - https://help.aliyun.com/document_detail/400484.html#api-detail-35
     * @return BindAxnExtensionResponse - https://help.aliyun.com/document_detail/400484.html#api-detail-40
     * @see AxnBindExtensionRequest
     * @see BindAxnExtensionResponse
     */
    BindAxnExtensionResponse bindAxnExtension(AxnBindExtensionRequest request) throws BindAxnException;
}
