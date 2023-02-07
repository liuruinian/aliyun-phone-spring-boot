package io.github.liuruinian.phone.axn.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import io.github.liuruinian.phone.axn.domain.AxnBindRequest;
import io.github.liuruinian.phone.axn.provider.AbstractAxnBindProvider;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

public class AxnBindDelegate extends AbstractAxnBindProvider {

    public AxnBindDelegate(IAcsClient acsClient) {
        super(acsClient);
    }

    @Override
    protected BindAxnRequest buildBindAxnRequest(AxnBindRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        BindAxnRequest axnRequest = new BindAxnRequest();

        // poolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot((poolKey) -> !StringUtils.hasLength(poolKey))
                .to(axnRequest::setPoolKey);
        // phoneNoA
        propertyMapper.from(request::getPhoneNoA)
                .whenNot(phoneNoA -> !StringUtils.hasLength(phoneNoA))
                .to(axnRequest::setPhoneNoA);
        // phoneNoB
        propertyMapper.from(request::getPhoneNoB)
                .to(axnRequest::setPhoneNoB);
        // phoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .to(axnRequest::setPhoneNoX);
        // Expiration
        propertyMapper.from(request::getExpiration)
                .whenNot(expiration -> !StringUtils.hasLength(expiration))
                .to(axnRequest::setExpiration);
        // ExpectCity
        propertyMapper.from(request::getExpectCity)
                .to(axnRequest::setExpectCity);

        // IsRecordingEnabled
        propertyMapper.from(request::getIsRecordingEnabled)
                .to(axnRequest::setIsRecordingEnabled);
        // NoType
        propertyMapper.from(request::getNoType)
                .to(axnRequest::setNoType);
        // OutId
        propertyMapper.from(request::getOutId)
                .to(axnRequest::setOutId);
        // OutOrderId
        propertyMapper.from(request::getOutOrderId)
                .to(axnRequest::setOutOrderId);
        // CallDisplayType
        propertyMapper.from(request::getCallDisplayType)
                .to(axnRequest::setCallDisplayType);
        // RingConfig
        propertyMapper.from(request::getRingConfig)
                .to(axnRequest::setRingConfig);
        // ASRStatus
        propertyMapper.from(request::getAsrStatus)
                .to(axnRequest::setASRStatus);
        // ASRModelId
        propertyMapper.from(request::getAsrModelId)
                .to(axnRequest::setASRModelId);
        // CallTimeout
        propertyMapper.from(request::getCallTimeout)
                .to(axnRequest::setCallTimeout);
        // CallRestrict
        propertyMapper.from(request::getCallRestrict)
                .to(axnRequest::setCallRestrict);

        return axnRequest;
    }
}
