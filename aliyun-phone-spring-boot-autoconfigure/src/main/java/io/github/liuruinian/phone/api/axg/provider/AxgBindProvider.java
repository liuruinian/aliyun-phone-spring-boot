package io.github.liuruinian.phone.api.axg.provider;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxgResponse;
import com.aliyuncs.dyplsapi.model.v20170525.CreateAxgGroupResponse;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import io.github.liuruinian.phone.domain.axg.AxgCreateGroupRequest;
import io.github.liuruinian.phone.exception.AxgGroupException;
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

    /**
     * create axg group
     *
     * @param request AxgCreateGroupRequest - https://help.aliyun.com/document_detail/400486.html#api-detail-35
     * @return CreateAxgGroupResponse - https://help.aliyun.com/document_detail/400486.html#api-detail-40
     * @see AxgCreateGroupRequest
     * @see CreateAxgGroupResponse
     */
    CreateAxgGroupResponse createAxgGroup(AxgCreateGroupRequest request) throws AxgGroupException;
}
