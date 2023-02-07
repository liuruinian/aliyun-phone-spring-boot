package io.github.liuruinian.phone.api.state.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubscriptionDetailRequest;
import io.github.liuruinian.phone.api.state.provider.AbstractStateQueryProvider;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class StateQueryDelegate extends AbstractStateQueryProvider {

    public StateQueryDelegate(IAcsClient acsClient) {
        super(acsClient);
    }

    @Override
    protected QuerySubscriptionDetailRequest buildQuerySubscriptionDetailRequest(SubscriptionDetailRequest request) {

        return null;
    }
}
