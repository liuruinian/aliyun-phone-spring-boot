package io.github.liuruinian.phone.api.axg.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxgRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxgResponse;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import io.github.liuruinian.phone.exception.BindAxgException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public abstract class AbstractAxgBindProvider implements AxgBindProvider {

    private final IAcsClient acsClient;

    public AbstractAxgBindProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public BindAxgResponse bingAxg(AxgBindRequest request) throws BindAxgException {
        try {
            BindAxgRequest axgRequest = buildBindAxgRequest(request);

            BindAxgResponse response = acsClient.getAcsResponse(axgRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new BindAxgException(e);
        }

        return null;
    }

    protected abstract BindAxgRequest buildBindAxgRequest(AxgBindRequest request);
}
