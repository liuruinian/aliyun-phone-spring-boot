package io.github.liuruinian.phone.api.subscribe.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.UpdateSubscriptionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.UpdateSubscriptionResponse;
import io.github.liuruinian.phone.domain.subscribe.SubscriptionUpdateRequest;
import io.github.liuruinian.phone.exception.SubscriptionOperationException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public abstract class AbstractSubscriptionProvider implements SubscriptionProvider {

    private final IAcsClient acsClient;

    public AbstractSubscriptionProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public UpdateSubscriptionResponse updateSubscription(SubscriptionUpdateRequest request) throws SubscriptionOperationException {
        try {
            UpdateSubscriptionRequest usr = buildUpdateSubscriptionRequest(request);

            UpdateSubscriptionResponse response = acsClient.getAcsResponse(usr);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new SubscriptionOperationException(e);
        }

        return null;
    }

    protected abstract UpdateSubscriptionRequest buildUpdateSubscriptionRequest(SubscriptionUpdateRequest request);
}
