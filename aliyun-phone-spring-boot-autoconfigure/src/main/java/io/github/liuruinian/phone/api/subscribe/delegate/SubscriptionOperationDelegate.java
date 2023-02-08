package io.github.liuruinian.phone.api.subscribe.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.UpdateSubscriptionRequest;
import io.github.liuruinian.phone.api.subscribe.provider.AbstractSubscriptionProvider;
import io.github.liuruinian.phone.domain.subscribe.SubscriptionUpdateRequest;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class SubscriptionOperationDelegate extends AbstractSubscriptionProvider {

    public SubscriptionOperationDelegate(IAcsClient acsClient) {
        super(acsClient);
    }

    @Override
    protected UpdateSubscriptionRequest buildUpdateSubscriptionRequest(SubscriptionUpdateRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        UpdateSubscriptionRequest usr = new UpdateSubscriptionRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .to(usr::setPoolKey);
        // ProductType
        propertyMapper.from(request::getProductType)
                .to(usr::setProductType);
        // SubsId
        propertyMapper.from(request::getSubsId)
                .whenNot(s -> !StringUtils.hasLength(s))
                .to(usr::setSubsId);
        // PhoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .whenNot(phoneNoX -> !StringUtils.hasLength(phoneNoX))
                .to(usr::setPhoneNoX);
        // PhoneNoA
        propertyMapper.from(request::getPhoneNoA)
                .to(usr::setPhoneNoA);
        // PhoneNoB
        propertyMapper.from(request::getPhoneNoB)
                .to(usr::setPhoneNoB);
        // GroupId
        propertyMapper.from(request::getGroupId)
                .to(usr::setGroupId);
        // CallRestrict
        propertyMapper.from(request::getCallRestrict)
                .to(usr::setCallRestrict);
        // Expiration
        propertyMapper.from(request::getExpiration)
                .to(usr::setExpiration);
        // CallDisplayType
        propertyMapper.from(request::getCallDisplayType)
                .to(usr::setCallDisplayType);
        // OutId
        propertyMapper.from(request::getOutId)
                .to(usr::setOutId);
        // IsRecordingEnabled
        propertyMapper.from(request::getIsRecordingEnabled)
                .to(usr::setIsRecordingEnabled);
        // OperateType
        propertyMapper.from(request::getOperateType)
                .whenNot(ot -> !StringUtils.hasLength(ot))
                .to(usr::setOperateType);
        // RingConfig
        propertyMapper.from(request::getRingConfig)
                .to(usr::setRingConfig);
        // ASRStatus
        propertyMapper.from(request::getAsrStatus)
                .to(usr::setASRStatus);
        // ASRModelId
        propertyMapper.from(request::getAsrModelId)
                .to(usr::setASRModelId);

        return usr;
    }
}
