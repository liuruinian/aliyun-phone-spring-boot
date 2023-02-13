package io.github.liuruinian.phone.api.axn.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindExtensionRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindRequest;
import io.github.liuruinian.phone.api.axn.provider.AbstractAxnBindProvider;
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

    @Override
    protected BindAxnExtensionRequest buildBindAxnExtensionRequest(AxnBindExtensionRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        BindAxnExtensionRequest axnExtensionRequest = new BindAxnExtensionRequest();

        // poolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(poolKey -> !StringUtils.hasLength(poolKey))
                .to(axnExtensionRequest::setPoolKey);
        // phoneNoA
        propertyMapper.from(request::getPhoneNoA)
                .whenNot(phoneNoA -> !StringUtils.hasLength(phoneNoA))
                .to(axnExtensionRequest::setPhoneNoA);
        // Extension
        propertyMapper.from(request::getExtension)
                .to(axnExtensionRequest::setExtension);
        // phoneNoB
        propertyMapper.from(request::getPhoneNoB)
                .to(axnExtensionRequest::setPhoneNoB);
        // phoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .to(axnExtensionRequest::setPhoneNoX);
        // Expiration
        propertyMapper.from(request::getExpiration)
                .whenNot(expiration -> !StringUtils.hasLength(expiration))
                .to(axnExtensionRequest::setExpiration);
        // ExpectCity
        propertyMapper.from(request::getExpectCity)
                .to(axnExtensionRequest::setExpectCity);
        // IsRecordingEnabled
        propertyMapper.from(request::getIsRecordingEnabled)
                .to(axnExtensionRequest::setIsRecordingEnabled);
        // OutId
        propertyMapper.from(request::getOutId)
                .to(axnExtensionRequest::setOutId);
        // OutOrderId
        propertyMapper.from(request::getOutOrderId)
                .to(axnExtensionRequest::setOutOrderId);
        // CallDisplayType
        propertyMapper.from(request::getCallDisplayType)
                .to(axnExtensionRequest::setCallDisplayType);
        // RingConfig
        propertyMapper.from(request::getRingConfig)
                .to(axnExtensionRequest::setRingConfig);
        // ASRStatus
        propertyMapper.from(request::getAsrStatus)
                .to(axnExtensionRequest::setASRStatus);
        // ASRModelId
        propertyMapper.from(request::getAsrModelId)
                .to(axnExtensionRequest::setASRModelId);
        // CallRestrict
        propertyMapper.from(request::getCallRestrict)
                .to(axnExtensionRequest::setCallRestrict);

        return axnExtensionRequest;
    }
}
