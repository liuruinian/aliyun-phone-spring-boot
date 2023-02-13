package io.github.liuruinian.phone.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.concurrent.TimeUnit;

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
    private ThreadPool threadPool;

    /**
     * poolKey
     */
    private String poolKey;

    public Mns getMns() {
        return mns;
    }

    public Acs getAcs() {
        return acs;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    /**
     * thread pool config
     *
     * @author liuruinian
     * @since 2023-02-07
     */
    public static class ThreadPool {

        private int corePoolSize = Runtime.getRuntime().availableProcessors();

        private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;

        private long keepAliveTime = 60L;

        private TimeUnit unit = TimeUnit.SECONDS;

        private int queueCapacity = Integer.MAX_VALUE;

        private RejectPolicy rejectPolicy = RejectPolicy.Abort;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public long getKeepAliveTime() {
            return keepAliveTime;
        }

        public void setKeepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }

        public TimeUnit getUnit() {
            return unit;
        }

        public void setUnit(TimeUnit unit) {
            this.unit = unit;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        public RejectPolicy getRejectPolicy() {
            return rejectPolicy;
        }

        public void setRejectPolicy(RejectPolicy rejectPolicy) {
            this.rejectPolicy = rejectPolicy;
        }
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
