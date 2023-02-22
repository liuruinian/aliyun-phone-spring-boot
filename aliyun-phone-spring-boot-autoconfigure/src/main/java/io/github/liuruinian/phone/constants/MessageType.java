package io.github.liuruinian.phone.constants;

/**
 * message type
 * @author liuruinian
 * @since 2023-02-13
 *
 * https://help.aliyun.com/document_detail/299954.html
 */
public interface MessageType {

    String SECRET_START_REPORT = "SecretStartReport";

    String SECRET_END_REPORT = "SecretReport";

    String SECRET_RECORDING_COMPLETION = "SecretRecording";

    String SECRET_EXCEPTION_PHONE_REPORT = "SecretExceptionPhoneReport";

    String SECRET_ASR_REPORT = "SecretAsrReport";

    String SMART_LOGISTICS_REPORT = "SmartLogisticsReport";

    String SECRET_RING_REPORT = "SecretRingReport";

    String SECRET_PICKUP_REPORT = "SecretPickUpReport";

    String NUMBER_MANAGEMENT_REPORT = "NumberManagementReport";
}
