package io.github.liuruinian.phone.configuration;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.github.liuruinian.phone.api.axb.delegate.AxbBindDelegate;
import io.github.liuruinian.phone.api.axg.delegate.AxgBindDelegate;
import io.github.liuruinian.phone.api.axn.delegate.AxnBindDelegate;
import io.github.liuruinian.phone.api.record.delegate.PhoneRecordDelegate;
import io.github.liuruinian.phone.api.state.delegate.StateQueryDelegate;
import io.github.liuruinian.phone.api.subscribe.delegate.SubscriptionOperationDelegate;
import io.github.liuruinian.phone.initializer.ReplyMessageQueueInitializer;
import io.github.liuruinian.phone.mnsreply.SecretEndReportListener;
import io.github.liuruinian.phone.mnsreply.SecretRecordingCompletionListener;
import io.github.liuruinian.phone.mnsreply.SecretStartReportListener;
import io.github.liuruinian.phone.properties.AliPhoneProperties;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@Configuration
@ComponentScan(basePackages = {"io.github.liuruinian.phone.endpoint"})
@EnableConfigurationProperties(value = {AliPhoneProperties.class})
public class AliPhoneConfiguration {

    @Bean
    public IAcsClient acsClient(AliPhoneProperties properties) {
        AliPhoneProperties.Acs acs = properties.getAcs();
        String endPoint = acs.getEndpoint();
        String accessKeyId = acs.getAccessKeyId();
        String accessKeySecret = acs.getAccessKeySecret();

        IClientProfile profile = DefaultProfile.getProfile(endPoint, accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(endPoint, AliPhoneProperties.PRODUCT, AliPhoneProperties.DOMAIN);

        return new DefaultAcsClient(profile);
    }

//    @Bean
//    public AsyncThreadPoolExecutor asyncThreadPoolExecutor(AliPhoneProperties properties) {
//        return new DefaultAsyncThreadPoolExecutor(properties);
//    }

    /**
     * ali message puller
     */
    @Bean
    @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
    @Conditional(DefaultAlicomMessagePullerCondition.class)
    public DefaultAlicomMessagePuller defaultAlicomMessagePuller() {
        return new DefaultAlicomMessagePuller();
    }

    public static class DefaultAlicomMessagePullerCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ClassLoader classLoader = context.getClassLoader();
            try {
                assert classLoader != null;
                classLoader.loadClass("com.alicom.mns.tools.DefaultAlicomMessagePuller");
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }
    }

    // ~ ---------------------------------------- mns message listener -------------------------------------------------

    @Bean
    public SecretStartReportListener secretStartReportListener() {
        return new SecretStartReportListener();
    }

    @Bean
    public SecretEndReportListener secretEndReportListener() {
        return new SecretEndReportListener();
    }

    @Bean
    public SecretRecordingCompletionListener secretRecordingCompletionListener() {
        return new SecretRecordingCompletionListener();
    }

    @Bean
    public ReplyMessageQueueInitializer replyMessageQueueInitializer(AliPhoneProperties properties,
                                                                     SecretStartReportListener secretStartReportListener,
                                                                     SecretEndReportListener secretEndReportListener) {
        return new ReplyMessageQueueInitializer(properties, secretStartReportListener, secretEndReportListener);
    }

    // ~ ---------------------------------------- api delegate bean ----------------------------------------------------

    @Bean
    public AxnBindDelegate axnBindDelegate(IAcsClient acsClient) {
        return new AxnBindDelegate(acsClient);
    }

    @Bean
    public AxbBindDelegate axbBindDelegate(IAcsClient acsClient) {
        return new AxbBindDelegate(acsClient);
    }

    @Bean
    public AxgBindDelegate axgBindDelegate(IAcsClient acsClient) {
        return new AxgBindDelegate(acsClient);
    }

    @Bean
    public StateQueryDelegate stateQueryDelegate(IAcsClient acsClient) {
        return new StateQueryDelegate(acsClient);
    }

    @Bean
    public SubscriptionOperationDelegate subscriptionOperationDelegate(IAcsClient acsClient) {
        return new SubscriptionOperationDelegate(acsClient);
    }

    @Bean
    public PhoneRecordDelegate phoneRecordDelegate(IAcsClient acsClient) {
        return new PhoneRecordDelegate(acsClient);
    }
}
