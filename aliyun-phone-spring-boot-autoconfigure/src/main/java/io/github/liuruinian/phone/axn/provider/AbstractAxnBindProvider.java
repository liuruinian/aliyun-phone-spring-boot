package io.github.liuruinian.phone.axn.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnExtensionResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.phone.axn.domain.AxnBindExtensionRequest;
import io.github.liuruinian.phone.axn.domain.AxnBindRequest;
import io.github.liuruinian.phone.exception.BindAxnException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public abstract class AbstractAxnBindProvider implements AxnBindProvider {

    private final IAcsClient acsClient;

    public AbstractAxnBindProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public BindAxnResponse bindAxn(AxnBindRequest request) throws BindAxnException {

        try {
            BindAxnRequest axnRequest = buildBindAxnRequest(request);

            BindAxnResponse response = acsClient.getAcsResponse(axnRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
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
