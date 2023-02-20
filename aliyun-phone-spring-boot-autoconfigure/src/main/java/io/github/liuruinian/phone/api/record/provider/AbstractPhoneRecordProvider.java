package io.github.liuruinian.phone.api.record.provider;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import io.github.liuruinian.phone.domain.record.RecordDownloadUrlRequest;
import io.github.liuruinian.phone.domain.record.RingPublicUrlRequest;
import io.github.liuruinian.phone.domain.record.SecretAsrDetailRequest;
import io.github.liuruinian.phone.exception.PhoneRecordException;
import io.github.liuruinian.phone.properties.AliPhoneProperties;
import io.github.liuruinian.phone.store.record.SecretRecordUrl;
import io.github.liuruinian.phone.store.record.SecretRecordUrlStore;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public abstract class AbstractPhoneRecordProvider implements PhoneRecordProvider, ApplicationContextAware {

    private final IAcsClient acsClient;

    private final AliPhoneProperties prop;

    private SecretRecordUrlStore secretRecordUrlStore;

    private OSSClient ossClient;

    public AbstractPhoneRecordProvider(IAcsClient acsClient, AliPhoneProperties properties) {
        this.acsClient = acsClient;
        this.prop = properties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ossClient = applicationContext.getBean(OSSClient.class);
        this.secretRecordUrlStore = applicationContext.getBean(SecretRecordUrlStore.class);
    }

    @Override
    public QueryRecordFileDownloadUrlResponse queryRecordFileDownloadUrl(RecordDownloadUrlRequest request) throws PhoneRecordException {
        try {
            QueryRecordFileDownloadUrlRequest qfr = buildQueryRecordFileDownloadUrlRequest(request);

            QueryRecordFileDownloadUrlResponse response = acsClient.getAcsResponse(qfr);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                String downloadUrl = response.getDownloadUrl();

                String callId = request.getCallId();
                if (secretRecordUrlStore != null) {
                    List<SecretRecordUrl> recordUrls = secretRecordUrlStore.querySecretRecordUrl(callId);
                    if (CollectionUtils.isEmpty(recordUrls)) {
                        URL url = ResourceUtils.getURL(downloadUrl);
                        String file = url.getFile();
                        String name = file.substring(1, file.indexOf("?"));
                        // upload ali oss
                        InputStream inputStream = url.openStream();
                        ossClient.putObject(prop.getOss().getBucketName(), name, inputStream);
                        String ossUrl = "https://" + prop.getOss().getDomain() + "/" + name;
                        // secret_record_url
                        SecretRecordUrl secretRecordUrl = SecretRecordUrl.builder()
                                .callId(request.getCallId())
                                .callTime(request.getCallTime())
                                .downloadUrl(ossUrl).build();
                        secretRecordUrlStore.addSecretRecordUrls(Collections.singleton(secretRecordUrl));

                        response.setDownloadUrl(ossUrl);
                    } else {
                        response.setDownloadUrl(recordUrls.get(0).getDownloadUrl());
                    }
                }
            }

            return response;
        } catch (Exception e) {
            throw new PhoneRecordException(e);
        }
    }

    protected abstract QueryRecordFileDownloadUrlRequest buildQueryRecordFileDownloadUrlRequest(RecordDownloadUrlRequest request);

    @Override
    public GetTotalPublicUrlResponse queryRingPublicUrl(RingPublicUrlRequest request) throws PhoneRecordException {
        try {
            GetTotalPublicUrlRequest gpr = buildGetTotalPublicUrlRequest(request);

            GetTotalPublicUrlResponse response = acsClient.getAcsResponse(gpr);
            if (response.getCode() != null && "OK".equals(response.getCode())) {

            }

            return response;
        } catch (Exception e) {
            throw new PhoneRecordException(e);
        }
    }

    protected abstract GetTotalPublicUrlRequest buildGetTotalPublicUrlRequest(RingPublicUrlRequest request);

    @Override
    public GetSecretAsrDetailResponse querySecretAsrDetail(SecretAsrDetailRequest request) throws PhoneRecordException {
        try {
            GetSecretAsrDetailRequest gar = buildGetSecretAsrDetailRequest(request);

            GetSecretAsrDetailResponse response = acsClient.getAcsResponse(gar);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
            }

            return response;
        } catch (Exception e) {
            throw new PhoneRecordException(e);
        }
    }

    protected abstract GetSecretAsrDetailRequest buildGetSecretAsrDetailRequest(SecretAsrDetailRequest request);
}
