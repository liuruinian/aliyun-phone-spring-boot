package io.github.liuruinian.phone.api.record.provider;

import com.aliyuncs.dyplsapi.model.v20170525.GetSecretAsrDetailResponse;
import com.aliyuncs.dyplsapi.model.v20170525.GetTotalPublicUrlResponse;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlResponse;
import io.github.liuruinian.phone.domain.record.RecordDownloadUrlRequest;
import io.github.liuruinian.phone.domain.record.RingPublicUrlRequest;
import io.github.liuruinian.phone.domain.record.SecretAsrDetailRequest;
import io.github.liuruinian.phone.exception.PhoneRecordException;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public interface PhoneRecordProvider {

    /**
     * query record file download url
     *
     * @param request RecordDownloadUrlRequest - https://help.aliyun.com/document_detail/400497.html#api-detail-35
     * @return QueryRecordFileDownloadUrlResponse - https://help.aliyun.com/document_detail/400497.html#api-detail-40
     * @see RecordDownloadUrlRequest
     * @see QueryRecordFileDownloadUrlResponse
     */
    QueryRecordFileDownloadUrlResponse queryRecordFileDownloadUrl(RecordDownloadUrlRequest request) throws PhoneRecordException;

    /**
     * query ring public url
     *
     * @param request RingPublicUrlRequest - https://help.aliyun.com/document_detail/400498.html#api-detail-35
     * @return GetTotalPublicUrlResponse - https://help.aliyun.com/document_detail/400498.html#api-detail-40
     * @see RingPublicUrlRequest
     * @see GetTotalPublicUrlResponse
     */
    GetTotalPublicUrlResponse queryRingPublicUrl(RingPublicUrlRequest request) throws PhoneRecordException;

    /**
     * query secret asr detail
     *
     * @param request SecretAsrDetailRequest - https://help.aliyun.com/document_detail/400499.html#api-detail-35
     * @return GetSecretAsrDetailResponse - https://help.aliyun.com/document_detail/400499.html#api-detail-40
     * @see SecretAsrDetailRequest
     * @see GetSecretAsrDetailResponse
     */
    GetSecretAsrDetailResponse querySecretAsrDetail(SecretAsrDetailRequest request) throws PhoneRecordException;
}
