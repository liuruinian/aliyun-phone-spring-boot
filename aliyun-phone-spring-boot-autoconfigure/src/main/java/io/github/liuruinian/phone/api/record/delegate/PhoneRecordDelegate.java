package io.github.liuruinian.phone.api.record.delegate;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.GetSecretAsrDetailRequest;
import com.aliyuncs.dyplsapi.model.v20170525.GetTotalPublicUrlRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlRequest;
import io.github.liuruinian.phone.api.record.provider.AbstractPhoneRecordProvider;
import io.github.liuruinian.phone.domain.record.RecordDownloadUrlRequest;
import io.github.liuruinian.phone.domain.record.RingPublicUrlRequest;
import io.github.liuruinian.phone.domain.record.SecretAsrDetailRequest;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;
import java.util.Objects;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public class PhoneRecordDelegate extends AbstractPhoneRecordProvider {

    public PhoneRecordDelegate(IAcsClient acsClient) {
        super(acsClient);
    }

    @Override
    protected QueryRecordFileDownloadUrlRequest buildQueryRecordFileDownloadUrlRequest(RecordDownloadUrlRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        QueryRecordFileDownloadUrlRequest qfr = new QueryRecordFileDownloadUrlRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .to(qfr::setPoolKey);
        // ProductType
        propertyMapper.from(request::getProductType)
                .to(qfr::setProductType);
        // CallId
        propertyMapper.from(request::getCallId)
                .whenNot(callId -> !StringUtils.hasLength(callId))
                .to(qfr::setCallId);
        // CallTime
        propertyMapper.from(request::getCallTime)
                .whenNot(time -> !StringUtils.hasLength(time))
                .to(qfr::setCallTime);

        return qfr;
    }

    @Override
    protected GetTotalPublicUrlRequest buildGetTotalPublicUrlRequest(RingPublicUrlRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        GetTotalPublicUrlRequest gpr = new GetTotalPublicUrlRequest();

        // CheckSubs
        propertyMapper.from(request::getCheckSubs)
                .whenNot(Objects::isNull)
                .to(gpr::setCheckSubs);
        // PartnerKey
        propertyMapper.from(request::getPartnerKey)
                .whenNot(p -> !StringUtils.hasLength(p))
                .to(gpr::setPartnerKey);
        // CallId
        propertyMapper.from(request::getCallId)
                .whenNot(callId -> !StringUtils.hasLength(callId))
                .to(gpr::setCallId);
        // CallTime
        propertyMapper.from(request::getCallTime)
                .whenNot(time -> !StringUtils.hasLength(time))
                .to(gpr::setCallTime);

        return gpr;
    }

    @Override
    protected GetSecretAsrDetailRequest buildGetSecretAsrDetailRequest(SecretAsrDetailRequest request) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        GetSecretAsrDetailRequest gar = new GetSecretAsrDetailRequest();

        // PoolKey
        propertyMapper.from(request::getPoolKey)
                .whenNot(p -> !StringUtils.hasLength(p))
                .to(gar::setPoolKey);
        // CallId
        propertyMapper.from(request::getCallId)
                .whenNot(callId -> !StringUtils.hasLength(callId))
                .to(gar::setCallId);
        // CallTime
        propertyMapper.from(request::getCallTime)
                .whenNot(callTime -> !StringUtils.hasLength(callTime))
                .to(gar::setCallTime);

        return gar;
    }
}
