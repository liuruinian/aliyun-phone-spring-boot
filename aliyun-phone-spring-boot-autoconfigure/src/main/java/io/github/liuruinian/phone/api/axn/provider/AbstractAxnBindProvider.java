package io.github.liuruinian.phone.api.axn.provider;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.phone.domain.axn.AxnBindExtensionRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindRequest;
import io.github.liuruinian.phone.exception.BindAxnException;
import io.github.liuruinian.phone.store.axn.BindAxnRecord;
import io.github.liuruinian.phone.store.axn.BindAxnRecordStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;

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

        BindAxnRecordStore bindAxnRecordStore = null;
        try {
            bindAxnRecordStore = applicationContext.getBean(BindAxnRecordStore.class);
        } catch (BeansException e) {
            if (log.isDebugEnabled()) {
                log.debug("No bean founded: BindAxnRecordStore");
            }
        }

        try {
            BindAxnRequest axnRequest = buildBindAxnRequest(request);
            if (log.isInfoEnabled()) {
                log.info("[AbstractAxnBindProvider] - axnRequest: \n{}", JSONObject.toJSONString(axnRequest, true));
            }

            BindAxnResponse response = acsClient.getAcsResponse(axnRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                if (bindAxnRecordStore != null) {
                    PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
                    BindAxnRecord bindAxnRecord = new BindAxnRecord();
                    BindAxnResponse.SecretBindDTO dto = response.getSecretBindDTO();

                    propertyMapper.from(response::getCode)
                            .to(bindAxnRecord::setCode);
                    propertyMapper.from(response::getMessage)
                            .to(bindAxnRecord::setMessage);
                    propertyMapper.from(response::getRequestId)
                            .to(bindAxnRecord::setRequestId);
                    propertyMapper.from(dto::getSubsId)
                            .to(bindAxnRecord::setSubsId);
                    propertyMapper.from(dto::getExtension)
                            .to(bindAxnRecord::setExtension);
                    propertyMapper.from(dto::getSecretNo)
                            .to(bindAxnRecord::setSecretNo);
                    propertyMapper.from(request::getPoolKey)
                            .to(bindAxnRecord::setPoolKey);

                    bindAxnRecordStore.addBindAxnRecords(Collections.singleton(bindAxnRecord));
                }
            }

            return response;
        } catch (Exception e) {
            throw new BindAxnException(e);
        }
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
