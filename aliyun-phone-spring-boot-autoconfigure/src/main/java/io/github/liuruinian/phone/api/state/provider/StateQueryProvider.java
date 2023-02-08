package io.github.liuruinian.phone.api.state.provider;

import com.aliyuncs.dyplsapi.model.v20170525.QuerySecretNoDetailResponse;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubsIdResponse;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubscriptionDetailResponse;
import io.github.liuruinian.phone.domain.state.SecretNoDetailRequest;
import io.github.liuruinian.phone.domain.state.SubsIdRequest;
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

    /**
     * query subsId
     *
     * @param request SubsIdRequest - https://help.aliyun.com/document_detail/400494.html#api-detail-35
     * @return QuerySubsIdResponse - https://help.aliyun.com/document_detail/400494.html#api-detail-40
     * @see SubsIdRequest
     * @see QuerySubsIdResponse
     */
    QuerySubsIdResponse querySubsId(SubsIdRequest request) throws StateQueryException;

    /**
     * query secretNo detail
     *
     * @param request SecretNoDetailRequest - https://help.aliyun.com/document_detail/400495.html#api-detail-35
     * @return QuerySecretNoDetailResponse - https://help.aliyun.com/document_detail/400495.html#api-detail-40
     * @see SecretNoDetailRequest
     * @see QuerySecretNoDetailResponse
     */
    QuerySecretNoDetailResponse querySecretNoDetail(SecretNoDetailRequest request) throws StateQueryException;
}
