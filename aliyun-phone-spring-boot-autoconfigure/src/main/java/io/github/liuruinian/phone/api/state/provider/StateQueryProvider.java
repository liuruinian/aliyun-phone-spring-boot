package io.github.liuruinian.phone.api.state.provider;

import com.aliyuncs.dyplsapi.model.v20170525.QuerySubscriptionDetailResponse;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;
import io.github.liuruinian.phone.exception.StateQueryException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface StateQueryProvider {

    /**
     * query subscription detail
     *
     * @param request SubscriptionDetailRequest - https://help.aliyun.com/document_detail/400493.html#api-detail-35
     * @return QuerySubscriptionDetailResponse - https://help.aliyun.com/document_detail/400493.html#api-detail-40
     * @see SubscriptionDetailRequest
     * @see QuerySubscriptionDetailResponse
     */
    QuerySubscriptionDetailResponse querySubscriptionDetail(SubscriptionDetailRequest request) throws StateQueryException;
}
