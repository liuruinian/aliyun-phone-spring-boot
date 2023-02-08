package io.github.liuruinian.phone.api.subscribe.provider;

import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UpdateSubscriptionResponse;
import io.github.liuruinian.phone.domain.subscribe.SubscriptionUnbindRequest;
import io.github.liuruinian.phone.domain.subscribe.SubscriptionUpdateRequest;
import io.github.liuruinian.phone.exception.SubscriptionOperationException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public interface SubscriptionProvider {

    /**
     * update subscription
     *
     * @param request SubscriptionUpdateRequest - https://help.aliyun.com/document_detail/400488.html#api-detail-35
     * @return UpdateSubscriptionResponse - https://help.aliyun.com/document_detail/400488.html#api-detail-40
     * @see SubscriptionUpdateRequest
     * @see UpdateSubscriptionResponse
     */
    UpdateSubscriptionResponse updateSubscription(SubscriptionUpdateRequest request) throws SubscriptionOperationException;

    /**
     * unbind subscription
     *
     * @param request SubscriptionUnbindRequest - https://help.aliyun.com/document_detail/400490.html#api-detail-35
     * @return UnbindSubscriptionResponse - https://help.aliyun.com/document_detail/400490.html#api-detail-40
     * @see SubscriptionUnbindRequest
     * @see UnbindSubscriptionResponse
     */
    UnbindSubscriptionResponse unbindSubscription(SubscriptionUnbindRequest request) throws SubscriptionOperationException;
}
