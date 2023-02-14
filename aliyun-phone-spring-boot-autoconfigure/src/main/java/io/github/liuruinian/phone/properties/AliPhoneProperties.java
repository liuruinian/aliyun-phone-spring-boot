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

    /**
     * poolKey
     */
    private String poolKey;

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

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
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
         * default: SecretStartReport、SecretReport、SecretSmsIntercept、SecretRecording
         */
        private String messageType = "SecretStartReport,SecretReport,SecretSmsIntercept,SecretRecording";

        /**
         * Whether to enable the secret start report for call initiation
         */
        private Boolean enableSecretStartReport = false;

        /**
         * Name of the secret start report queue when the call is initiated
         */
        private String secretStartReportQueueName;

        /**
         * Whether to enable the secret end report after call end
         */
        private Boolean enableSecretEndReport = false;

        /**
         * Name of the secret end report queue after the call ends
         */
        private String secretEndReportQueueName;

        /**
         * Whether to enable recording completion event messages
         */
        private Boolean enableRecordingCompletionEvent = false;

        /**
         * Recording completion event message queue name
         */
        private String secretRecordingCompletionQueueName;

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
    }
}
