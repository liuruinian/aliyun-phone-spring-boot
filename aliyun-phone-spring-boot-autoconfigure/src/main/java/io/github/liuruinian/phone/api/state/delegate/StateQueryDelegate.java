package io.github.liuruinian.phone.api.state.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySecretNoDetailRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubsIdRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QuerySubscriptionDetailRequest;
import io.github.liuruinian.phone.api.state.provider.AbstractStateQueryProvider;
import io.github.liuruinian.phone.domain.state.SecretNoDetailRequest;
import io.github.liuruinian.phone.domain.state.SubsIdRequest;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

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
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        QuerySubscriptionDetailRequest subscriptionDetailRequest = new QuerySubscriptionDetailRequest();

        // subsId
        propertyMapper.from(request::getSubsId)
                .whenNot(subsId -> !StringUtils.hasLength(subsId))
                .to(subscriptionDetailRequest::setSubsId);
        // phoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .whenNot(phoneNoA -> !StringUtils.hasLength(phoneNoA))
                .to(subscriptionDetailRequest::setPhoneNoX);

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .to(subscriptionDetailRequest::setPoolKey);

        // ProductType
        propertyMapper.from(request::getProductType)
                .to(subscriptionDetailRequest::setProductType);

        return subscriptionDetailRequest;
    }

    @Override
    protected QuerySubsIdRequest buildQuerySubsIdRequest(SubsIdRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        QuerySubsIdRequest subsIdRequest = new QuerySubsIdRequest();

        // poolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(poolKey -> !StringUtils.hasLength(poolKey))
                .to(subsIdRequest::setPoolKey);
        // phoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .whenNot(phoneNoX -> !StringUtils.hasLength(phoneNoX))
                .to(subsIdRequest::setPhoneNoX);

        return subsIdRequest;
    }

    @Override
    protected QuerySecretNoDetailRequest buildQuerySecretNoDetailRequest(SecretNoDetailRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        QuerySecretNoDetailRequest noDetailRequest = new QuerySecretNoDetailRequest();

        // poolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(poolKey -> !StringUtils.hasLength(poolKey))
                .to(noDetailRequest::setPoolKey);
        // secretNo
        propertyMapper.from(request::getSecretNo)
                .whenNot(secret -> !StringUtils.hasLength(secret))
                .to(noDetailRequest::setSecretNo);

        return noDetailRequest;
    }
}
