package io.github.liuruinian.phone.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@ConfigurationProperties(prefix = "phone.protection.ali")
public class AliPhoneProperties {

    /** Privacy Number Product name (The privacy product name is fixed and does not need to be changed) */
    public static final String               PRODUCT = "Dyplsapi";

    /** Privacy Number Product domain name (Interface address fixed, do not change) */
    public static final String               DOMAIN = "dyplsapi.aliyuncs.com";

    @NestedConfigurationProperty
    private Acs         acs;

    /**
     * poolKey
     */
    private String      poolKey;


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
        private String      endpoint = "cn-hangzhou";

        /**
         * accessKeyId
         */
        private String      accessKeyId;

        /**
         * accessKeySecret
         */
        private String      accessKeySecret;

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

    public Acs getAcs() {
        return acs;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }
}
