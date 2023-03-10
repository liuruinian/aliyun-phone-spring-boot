package io.github.liuruinian.phone.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@ConfigurationProperties(prefix = "phone.protection.ali")
public class AliPhoneProperties {

    /**
     * Privacy Number Product name (The privacy product name is fixed and does not need to be changed)
     */
    public static final String PRODUCT = "Dyplsapi";

    /**
     * Privacy Number Product domain name (Interface address fixed, do not change)
     */
    public static final String DOMAIN = "dyplsapi.aliyuncs.com";

    @NestedConfigurationProperty
    private Acs acs;

    @NestedConfigurationProperty
    private Mns mns;

    @NestedConfigurationProperty
    private Oss oss;

    public Acs getAcs() {
        return acs;
    }

    public void setAcs(Acs acs) {
        this.acs = acs;
    }

    public Mns getMns() {
        return mns;
    }

    public void setMns(Mns mns) {
        this.mns = mns;
    }

    public Oss getOss() {
        return oss;
    }

    public void setOss(Oss oss) {
        this.oss = oss;
    }

    /**
     * IAcsClientProfile
     *
     * @author liuruinian
     * @since 2023-02-07
     */
    public static class Acs {

        /**
         * Endpoint
         */
        private String endpoint = "cn-hangzhou";

        /**
         * accessKeyId
         */
        private String accessKeyId;

        /**
         * accessKeySecret
         */
        private String accessKeySecret;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }
    }

    /**
     * mns message queue
     *
     * @author liuruinian
     * @since 2023-02-13
     */
    public static class Mns {
        /**
         * message type, separate with ','
         * <p>
         * default: SecretStartReport,SecretReport,SecretExceptionPhoneReport,SecretRecording,
         * SecretAsrReport,SmartLogisticsReport,SecretRingReport,SecretPickUpReport,NumberManagementReport
         */
        private String messageType = "SecretStartReport,SecretReport,SecretExceptionPhoneReport," +
                "SecretRecording,SecretAsrReport,SmartLogisticsReport,SecretRingReport,SecretPickUpReport,NumberManagementReport";

        /**
         * Whether to enable the secret start report for call initiation.
         */
        private Boolean enableSecretStartReport = false;

        /**
         * Name of the secret start report queue when the call is initiated.
         */
        private String secretStartReportQueueName;

        /**
         * Whether to enable the secret end report after call end.
         */
        private Boolean enableSecretEndReport = false;

        /**
         * Name of the secret end report queue after the call ends.
         */
        private String secretEndReportQueueName;

        /**
         * Whether to enable recording completion event messages.
         */
        private Boolean enableRecordingCompletionEvent = false;

        /**
         * Recording completion event message queue name.
         */
        private String secretRecordingCompletionQueueName;

        /**
         * Whether to enable the secret exception phone report.
         */
        private Boolean enableSecretExceptionPhoneReport = false;

        /**
         * secret exception phone report queue name.
         */
        private String secretExceptionPhoneReportQueueName;

        /**
         * Whether to enable the secret asr report.
         */
        private Boolean enableSecretAsrReport = false;

        /**
         * secret asr report queue name.
         */
        private String secretAsrReportQueueName;

        /**
         * Whether to enable the smart logistic report.
         */
        private Boolean enableSmartLogisticsReport = false;

        /**
         * smart logistic report queue name.
         */
        private String smartLogisticsReportQueueName;

        /**
         * Whether to enable the secret ring report.
         */
        private Boolean enableSecretRingReport = false;

        /**
         * secret ring report queue name.
         */
        private String secretRingReportQueueName;

        /**
         * Whether to enable the secret pick up report.
         */
        private Boolean enableSecretPickUpReport = false;

        /**
         * secret pick up report queue name.
         */
        private String secretPickUpReportQueueName;

        /**
         * Whether to enable the number management report.
         */
        private Boolean enableNumberManagementReport = false;

        /**
         * number management report queue name.
         */
        private String numberManagementReportQueueName;

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public Boolean getEnableSecretStartReport() {
            return enableSecretStartReport;
        }

        public void setEnableSecretStartReport(Boolean enableSecretStartReport) {
            this.enableSecretStartReport = enableSecretStartReport;
        }

        public String getSecretStartReportQueueName() {
            return secretStartReportQueueName;
        }

        public void setSecretStartReportQueueName(String secretStartReportQueueName) {
            this.secretStartReportQueueName = secretStartReportQueueName;
        }

        public Boolean getEnableSecretEndReport() {
            return enableSecretEndReport;
        }

        public void setEnableSecretEndReport(Boolean enableSecretEndReport) {
            this.enableSecretEndReport = enableSecretEndReport;
        }

        public String getSecretEndReportQueueName() {
            return secretEndReportQueueName;
        }

        public void setSecretEndReportQueueName(String secretEndReportQueueName) {
            this.secretEndReportQueueName = secretEndReportQueueName;
        }

        public Boolean getEnableRecordingCompletionEvent() {
            return enableRecordingCompletionEvent;
        }

        public void setEnableRecordingCompletionEvent(Boolean enableRecordingCompletionEvent) {
            this.enableRecordingCompletionEvent = enableRecordingCompletionEvent;
        }

        public String getSecretRecordingCompletionQueueName() {
            return secretRecordingCompletionQueueName;
        }

        public void setSecretRecordingCompletionQueueName(String secretRecordingCompletionQueueName) {
            this.secretRecordingCompletionQueueName = secretRecordingCompletionQueueName;
        }

        public Boolean getEnableSecretExceptionPhoneReport() {
            return enableSecretExceptionPhoneReport;
        }

        public void setEnableSecretExceptionPhoneReport(Boolean enableSecretExceptionPhoneReport) {
            this.enableSecretExceptionPhoneReport = enableSecretExceptionPhoneReport;
        }

        public String getSecretExceptionPhoneReportQueueName() {
            return secretExceptionPhoneReportQueueName;
        }

        public void setSecretExceptionPhoneReportQueueName(String secretExceptionPhoneReportQueueName) {
            this.secretExceptionPhoneReportQueueName = secretExceptionPhoneReportQueueName;
        }

        public Boolean getEnableSecretAsrReport() {
            return enableSecretAsrReport;
        }

        public void setEnableSecretAsrReport(Boolean enableSecretAsrReport) {
            this.enableSecretAsrReport = enableSecretAsrReport;
        }

        public String getSecretAsrReportQueueName() {
            return secretAsrReportQueueName;
        }

        public void setSecretAsrReportQueueName(String secretAsrReportQueueName) {
            this.secretAsrReportQueueName = secretAsrReportQueueName;
        }

        public Boolean getEnableSmartLogisticsReport() {
            return enableSmartLogisticsReport;
        }

        public void setEnableSmartLogisticsReport(Boolean enableSmartLogisticsReport) {
            this.enableSmartLogisticsReport = enableSmartLogisticsReport;
        }

        public String getSmartLogisticsReportQueueName() {
            return smartLogisticsReportQueueName;
        }

        public void setSmartLogisticsReportQueueName(String smartLogisticsReportQueueName) {
            this.smartLogisticsReportQueueName = smartLogisticsReportQueueName;
        }

        public Boolean getEnableSecretRingReport() {
            return enableSecretRingReport;
        }

        public void setEnableSecretRingReport(Boolean enableSecretRingReport) {
            this.enableSecretRingReport = enableSecretRingReport;
        }

        public String getSecretRingReportQueueName() {
            return secretRingReportQueueName;
        }

        public void setSecretRingReportQueueName(String secretRingReportQueueName) {
            this.secretRingReportQueueName = secretRingReportQueueName;
        }

        public Boolean getEnableSecretPickUpReport() {
            return enableSecretPickUpReport;
        }

        public void setEnableSecretPickUpReport(Boolean enableSecretPickUpReport) {
            this.enableSecretPickUpReport = enableSecretPickUpReport;
        }

        public String getSecretPickUpReportQueueName() {
            return secretPickUpReportQueueName;
        }

        public void setSecretPickUpReportQueueName(String secretPickUpReportQueueName) {
            this.secretPickUpReportQueueName = secretPickUpReportQueueName;
        }

        public Boolean getEnableNumberManagementReport() {
            return enableNumberManagementReport;
        }

        public void setEnableNumberManagementReport(Boolean enableNumberManagementReport) {
            this.enableNumberManagementReport = enableNumberManagementReport;
        }

        public String getNumberManagementReportQueueName() {
            return numberManagementReportQueueName;
        }

        public void setNumberManagementReportQueueName(String numberManagementReportQueueName) {
            this.numberManagementReportQueueName = numberManagementReportQueueName;
        }
    }

    /**
     * ali oss profile
     */
    public static class Oss {
        /** endpoint */
        private String endpoint;

        /** access-key-id */
        private String accessKeyId;

        /** access-key-secret */
        private String accessKeySecret;

        /** bucket-name */
        private String bucketName;

        /** domain */
        private String domain;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }
}
