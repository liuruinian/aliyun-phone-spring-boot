package io.github.liuruinian.phone.api.axg.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxgRequest;
import com.aliyuncs.dyplsapi.model.v20170525.CreateAxgGroupRequest;
import com.aliyuncs.dyplsapi.model.v20170525.OperateAxgGroupRequest;
import io.github.liuruinian.phone.api.axg.provider.AbstractAxgBindProvider;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import io.github.liuruinian.phone.domain.axg.AxgCreateGroupRequest;
import io.github.liuruinian.phone.domain.axg.UpdateAxgGroupRequest;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.util.Objects;

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

    @Override
    protected CreateAxgGroupRequest buildAxgCreateGroupRequest(AxgCreateGroupRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        CreateAxgGroupRequest gr = new CreateAxgGroupRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(p -> !StringUtils.hasLength(p))
                .to(gr::setPoolKey);
        // Name
        propertyMapper.from(request::getName)
                .to(gr::setName);
        // Remark
        propertyMapper.from(request::getRemark)
                .to(gr::setRemark);
        // Numbers
        propertyMapper.from(request::getNumbers)
                .to(gr::setNumbers);

        return gr;
    }

    @Override
    protected OperateAxgGroupRequest buildOperateAxgGroupRequest(UpdateAxgGroupRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        OperateAxgGroupRequest ogr = new OperateAxgGroupRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(p -> !StringUtils.hasLength(p))
                .to(ogr::setPoolKey);
        // GroupId
        propertyMapper.from(request::getGroupId)
                .whenNot(Objects::isNull)
                .to(ogr::setGroupId);
        // OperateType
        propertyMapper.from(request::getOperateType)
                .whenNot(o -> !StringUtils.hasLength(o))
                .to(ogr::setOperateType);
        // Numbers
        propertyMapper.from(request::getNumbers)
                .whenNot(n -> !StringUtils.hasLength(n))
                .to(ogr::setNumbers);

        return ogr;
    }
}
