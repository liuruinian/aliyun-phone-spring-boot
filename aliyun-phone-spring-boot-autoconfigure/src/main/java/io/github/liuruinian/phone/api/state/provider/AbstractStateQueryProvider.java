package io.github.liuruinian.phone.api.state.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubscriptionDetailRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubscriptionDetailResponse;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;
import io.github.liuruinian.phone.exception.StateQueryException;

public abstract class AbstractStateQueryProvider implements StateQueryProvider {

    private final IAcsClient acsClient;

    public AbstractStateQueryProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public QuerySubscriptionDetailResponse querySubscriptionDetail(SubscriptionDetailRequest request) throws StateQueryException {
        try {
            QuerySubscriptionDetailRequest detailRequest = buildQuerySubscriptionDetailRequest(request);

            QuerySubscriptionDetailResponse response = acsClient.getAcsResponse(detailRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new StateQueryException(e);
        }

        return null;
    }

    protected abstract QuerySubscriptionDetailRequest buildQuerySubscriptionDetailRequest(SubscriptionDetailRequest request);
}
