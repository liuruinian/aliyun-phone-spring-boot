package io.github.liuruinian.phone.api.axn.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import io.github.liuruinian.phone.api.state.delegate.StateQueryDelegate;
import io.github.liuruinian.phone.domain.axn.AxnBindExtensionRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindRequest;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;
import io.github.liuruinian.phone.exception.BindAxnException;
import io.github.liuruinian.phone.store.axn.BindAxnRecord;
import io.github.liuruinian.phone.store.axn.BindAxnRecordStore;
import io.github.liuruinian.phone.store.detail.BindDetail;
import io.github.liuruinian.phone.store.detail.BindDetailStore;
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

    private BindAxnRecordStore bindAxnRecordStore = null;

    private BindDetailStore bindDetailStore = null;

    private StateQueryDelegate stateQueryDelegate = null;

    public AbstractAxnBindProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.bindAxnRecordStore = applicationContext.getBean(BindAxnRecordStore.class);
        this.bindDetailStore = applicationContext.getBean(BindDetailStore.class);
        this.stateQueryDelegate = applicationContext.getBean(StateQueryDelegate.class);
    }

    @Override
    public BindAxnResponse bindAxn(AxnBindRequest request) throws BindAxnException {
        try {
            BindAxnRequest axnRequest = buildBindAxnRequest(request);

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

                    // BindDetailStore
                    saveBindDetail(dto.getSecretNo(), dto.getSubsId(), request.getPoolKey());
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
                if (bindAxnRecordStore != null) {
                    PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
                    BindAxnRecord bindAxnRecord = new BindAxnRecord();
                    BindAxnExtensionResponse.SecretBindDTO dto = response.getSecretBindDTO();

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

                    saveBindDetail(dto.getSecretNo(), dto.getSubsId(), request.getPoolKey());
                }
            }

            return response;
        } catch (Exception e) {
            throw new BindAxnException(e);
        }
    }

    protected abstract BindAxnExtensionRequest buildBindAxnExtensionRequest(AxnBindExtensionRequest request);

    protected boolean saveBindDetail(String secretNo, String subsId, String poolKey) {
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        if (stateQueryDelegate != null) {
            SubscriptionDetailRequest subscriptionDetailRequest =
                    SubscriptionDetailRequest.builder().subsId(subsId).phoneNoX(secretNo).build();

            QuerySubscriptionDetailResponse subscriptionDetailResponse =
                    stateQueryDelegate.querySubscriptionDetail(subscriptionDetailRequest);

            BindDetail bindDetail = new BindDetail();
            QuerySubscriptionDetailResponse.SecretBindDetailDTO bindDetailDTO = subscriptionDetailResponse.getSecretBindDetailDTO();
            propertyMapper.from(subscriptionDetailResponse::getCode)
                    .to(bindDetail::setCode);
            propertyMapper.from(subscriptionDetailResponse::getMessage)
                    .to(bindDetail::setMessage);
            propertyMapper.from(subscriptionDetailResponse::getRequestId)
                    .to(bindDetail::setRequestId);
            propertyMapper.from(poolKey)
                    .to(bindDetail::setPoolKey);
            propertyMapper.from(bindDetailDTO::getSubsId)
                    .to(bindDetail::setSubsId);
            propertyMapper.from(bindDetailDTO::getStatus)
                    .to(bindDetail::setStatus);
            propertyMapper.from(bindDetailDTO::getExtension)
                    .to(bindDetail::setExtension);
            propertyMapper.from(bindDetailDTO::getPhoneNoA)
                    .to(bindDetail::setPhoneNoA);
            propertyMapper.from(bindDetailDTO::getPhoneNoB)
                    .to(bindDetail::setPhoneNoB);
            propertyMapper.from(bindDetailDTO::getPhoneNoX)
                    .to(bindDetail::setPhoneNoX);
            propertyMapper.from(bindDetailDTO::getGroupId)
                    .to(bindDetail::setGroupId);
            propertyMapper.from(bindDetailDTO::getGmtCreate)
                    .to(bindDetail::setGmtCreate);
            propertyMapper.from(bindDetailDTO::getExpireDate)
                    .to(bindDetail::setExpireDate);
            propertyMapper.from(bindDetailDTO::getNeedRecord)
                    .to(bindDetail::setNeedRecord);
            propertyMapper.from(bindDetailDTO::getCallRestrict)
                    .to(bindDetail::setCallRestrict);
            propertyMapper.from(bindDetailDTO::getASRStatus)
                    .to(bindDetail::setAsrStatus);
            propertyMapper.from(bindDetailDTO::getASRModelId)
                    .to(bindDetail::setAsrModelId);

            if (bindDetailStore != null) {
                return bindDetailStore.addBindDetails(Collections.singleton(bindDetail));
            }
        }

        return false;
    }
}
