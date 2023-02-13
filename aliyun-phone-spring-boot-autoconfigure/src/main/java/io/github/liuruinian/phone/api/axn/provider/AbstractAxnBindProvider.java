package io.github.liuruinian.phone.api.axn.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.phone.domain.axn.AxnBindExtensionRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindRequest;
import io.github.liuruinian.phone.api.axn.processor.BindAxnPostProcessor;
import io.github.liuruinian.phone.exception.BindAxnException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@Slf4j
public abstract class AbstractAxnBindProvider implements AxnBindProvider, ApplicationContextAware {

    private final IAcsClient acsClient;

    private ApplicationContext applicationContext;

    public AbstractAxnBindProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public BindAxnResponse bindAxn(AxnBindRequest request) throws BindAxnException {

        BindAxnPostProcessor bindAxnPostProcessor = null;
        try {
            bindAxnPostProcessor = applicationContext.getBean(BindAxnPostProcessor.class);
        } catch (BeansException e) {
            if (log.isDebugEnabled()) {
                log.debug("No bean founded: BindAxnPostProcessor");
            }
        }

        try {
            BindAxnRequest axnRequest = buildBindAxnRequest(request);
            if (bindAxnPostProcessor != null) {
                bindAxnPostProcessor.postProcessBeforeBind(axnRequest);
            }

            BindAxnResponse response = acsClient.getAcsResponse(axnRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                if (bindAxnPostProcessor != null) {
                    bindAxnPostProcessor.postProcessAfterBind(response);
                }
                return response;
            }
        } catch (Exception e) {
            throw new BindAxnException(e);
        }

        return null;
    }

    protected abstract BindAxnRequest buildBindAxnRequest(AxnBindRequest request);

    @Override
    public BindAxnExtensionResponse bindAxnExtension(AxnBindExtensionRequest request) throws BindAxnException {
        try {
            BindAxnExtensionRequest axnExtensionRequest = buildBindAxnExtensionRequest(request);

            BindAxnExtensionResponse response = acsClient.getAcsResponse(axnExtensionRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new BindAxnException(e);
        }
        return null;
    }

    protected abstract BindAxnExtensionRequest buildBindAxnExtensionRequest(AxnBindExtensionRequest request);
}
