package io.github.liuruinian.phone.api.axb.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbRequest;
import io.github.liuruinian.phone.api.axb.provider.AbstractAxbBindProvider;
import io.github.liuruinian.phone.domain.axb.AxbBindRequest;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class AxbBindDelegate extends AbstractAxbBindProvider {

    public AxbBindDelegate(IAcsClient acsClient) {
        super(acsClient);
    }

    @Override
    protected BindAxbRequest buildBindAxbRequest(AxbBindRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        BindAxbRequest axbRequest = new BindAxbRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(p -> !StringUtils.hasLength(p))
                .to(axbRequest::setPoolKey);
        // PhoneNoA
        propertyMapper.from(request::getPhoneNoA)
                .whenNot(phoneNoA -> !StringUtils.hasLength(phoneNoA))
                .to(axbRequest::setPhoneNoA);
        // PhoneNoB
        propertyMapper.from(request::getPhoneNoB)
                .whenNot(phoneNoB -> !StringUtils.hasLength(phoneNoB))
                .to(axbRequest::setPhoneNoB);
        // PhoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .to(axbRequest::setPhoneNoX);
        // Expiration
        propertyMapper.from(request::getExpiration)
                .whenNot(expiration -> !StringUtils.hasLength(expiration))
                .to(axbRequest::setExpiration);
        // ExpectCity
        propertyMapper.from(request::getExpectCity)
                .to(axbRequest::setExpectCity);
        // IsRecordingEnabled
        propertyMapper.from(request::getIsRecordingEnabled)
                .to(axbRequest::setIsRecordingEnabled);
        // OutId
        propertyMapper.from(request::getOutId)
                .to(axbRequest::setOutId);
        // OutOrderId
        propertyMapper.from(request::getOutOrderId)
                .to(axbRequest::setOutOrderId);
        // CallRestrict
        propertyMapper.from(request::getCallRestrict)
                .to(axbRequest::setCallRestrict);
        // CallDisplayType
        propertyMapper.from(request::getCallDisplayType)
                .to(axbRequest::setCallDisplayType);
        // RingConfig
        propertyMapper.from(request::getRingConfig)
                .to(axbRequest::setRingConfig);
        // ASRStatus
        propertyMapper.from(request::getAsrStatus)
                .to(axbRequest::setASRStatus);
        // ASRModelId
        propertyMapper.from(request::getAsrModelId)
                .to(axbRequest::setASRModelId);
        // CallTimeout
        propertyMapper.from(request::getCallTimeout)
                .to(axbRequest::setCallTimeout);

        return axbRequest;
    }
}
