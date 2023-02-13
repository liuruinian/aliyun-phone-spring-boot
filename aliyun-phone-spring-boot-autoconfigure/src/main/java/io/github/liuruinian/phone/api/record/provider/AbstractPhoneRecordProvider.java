package io.github.liuruinian.phone.api.record.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import io.github.liuruinian.phone.domain.record.RecordDownloadUrlRequest;
import io.github.liuruinian.phone.domain.record.RingPublicUrlRequest;
import io.github.liuruinian.phone.domain.record.SecretAsrDetailRequest;
import io.github.liuruinian.phone.exception.PhoneRecordException;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public abstract class AbstractPhoneRecordProvider implements PhoneRecordProvider {

    private final IAcsClient acsClient;

    public AbstractPhoneRecordProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public QueryRecordFileDownloadUrlResponse queryRecordFileDownloadUrl(RecordDownloadUrlRequest request) throws PhoneRecordException {
        try {
            QueryRecordFileDownloadUrlRequest qfr = buildQueryRecordFileDownloadUrlRequest(request);

            QueryRecordFileDownloadUrlResponse response = acsClient.getAcsResponse(qfr);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new PhoneRecordException(e);
        }

        return null;
    }

    protected abstract QueryRecordFileDownloadUrlRequest buildQueryRecordFileDownloadUrlRequest(RecordDownloadUrlRequest request);

    @Override
    public GetTotalPublicUrlResponse queryRingPublicUrl(RingPublicUrlRequest request) throws PhoneRecordException {
        try {
            GetTotalPublicUrlRequest gpr = buildGetTotalPublicUrlRequest(request);

            GetTotalPublicUrlResponse response = acsClient.getAcsResponse(gpr);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new PhoneRecordException(e);
        }

        return null;
    }

    protected abstract GetTotalPublicUrlRequest buildGetTotalPublicUrlRequest(RingPublicUrlRequest request);

    @Override
    public GetSecretAsrDetailResponse querySecretAsrDetail(SecretAsrDetailRequest request) throws PhoneRecordException {
        try {
            GetSecretAsrDetailRequest gar = buildGetSecretAsrDetailRequest(request);

            GetSecretAsrDetailResponse response = acsClient.getAcsResponse(gar);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new PhoneRecordException(e);
        }

        return null;
    }

    protected abstract GetSecretAsrDetailRequest buildGetSecretAsrDetailRequest(SecretAsrDetailRequest request);
}
