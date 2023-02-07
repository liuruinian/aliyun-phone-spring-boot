package io.github.liuruinian.phone.configuration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.github.liuruinian.phone.axn.delegate.AxnBindDelegate;
import io.github.liuruinian.phone.properties.AliPhoneProperties;
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
    public AxnBindDelegate axnBindDelegate(IAcsClient acsClient) {
        return new AxnBindDelegate(acsClient);
    }
}
