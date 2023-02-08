package io.github.liuruinian.phone.api.axg.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxgRequest;
import io.github.liuruinian.phone.api.axg.provider.AbstractAxgBindProvider;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class AxgBindDelegate extends AbstractAxgBindProvider {

    public AxgBindDelegate(IAcsClient acsClient) {
        super(acsClient);
    }

    @Override
    protected BindAxgRequest buildBindAxgRequest(AxgBindRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        BindAxgRequest axgRequest = new BindAxgRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(p -> !StringUtils.hasLength(p))
                .to(axgRequest::setPoolKey);
        // PhoneNoA
        propertyMapper.from(request::getPhoneNoA)
                .whenNot(phoneNoA -> !StringUtils.hasLength(phoneNoA))
                .to(axgRequest::setPhoneNoA);
        // GroupId
        propertyMapper.from(request::getGroupId)
                .whenNot(g -> !StringUtils.hasLength(g))
                .to(axgRequest::setGroupId);
        // PhoneNoB
        propertyMapper.from(request::getPhoneNoB)
                .to(axgRequest::setPhoneNoB);
        // PhoneNoX
        propertyMapper.from(request::getPhoneNoX)
                .to(axgRequest::setPhoneNoX);
        // Expiration
        propertyMapper.from(request::getExpiration)
                .whenNot(e -> !StringUtils.hasLength(e))
                .to(axgRequest::setExpiration);
        // ExpectCity
        propertyMapper.from(request::getExpectCity)
                .to(axgRequest::setExpectCity);
        // IsRecordingEnabled
        propertyMapper.from(request::getIsRecordingEnabled)
                .to(axgRequest::setIsRecordingEnabled);
        // OutId
        propertyMapper.from(request::getOutId)
                .to(axgRequest::setOutId);
        // OutOrderId
        propertyMapper.from(request::getOutOrderId)
                .to(axgRequest::setOutOrderId);
        // CallDisplayType
        propertyMapper.from(request::getCallDisplayType)
                .to(axgRequest::setCallDisplayType);
        // RingConfig
        propertyMapper.from(request::getRingConfig)
                .to(axgRequest::setRingConfig);
        // ASRStatus
        propertyMapper.from(request::getAsrStatus)
                .to(axgRequest::setASRStatus);
        // ASRModelId
        propertyMapper.from(request::getAsrModelId)
                .to(axgRequest::setASRModelId);
        // CallRestrict
        propertyMapper.from(request::getCallRestrict)
                .to(axgRequest::setCallRestrict);

        return axgRequest;
    }
}
