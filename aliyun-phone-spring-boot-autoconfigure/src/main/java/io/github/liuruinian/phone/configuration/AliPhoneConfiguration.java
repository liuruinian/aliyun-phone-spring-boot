package io.github.liuruinian.phone.configuration;

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
import io.github.liuruinian.phone.properties.AliPhoneProperties;
import io.github.liuruinian.phone.threadpool.AsyncThreadPoolExecutor;
import io.github.liuruinian.phone.threadpool.DefaultAsyncThreadPoolExecutor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@Configuration
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

    @Bean
    public AsyncThreadPoolExecutor asyncThreadPoolExecutor(AliPhoneProperties properties) {
        return new DefaultAsyncThreadPoolExecutor(properties);
    }

    @Bean
    public AxnBindDelegate axnBindDelegate(IAcsClient acsClient, AsyncThreadPoolExecutor asyncThreadPoolExecutor) {
        return new AxnBindDelegate(acsClient, asyncThreadPoolExecutor);
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
