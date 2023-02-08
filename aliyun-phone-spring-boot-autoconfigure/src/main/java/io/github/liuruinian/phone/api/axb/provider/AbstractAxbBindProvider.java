package io.github.liuruinian.phone.api.axb.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import io.github.liuruinian.phone.domain.axb.AxbBindRequest;
import io.github.liuruinian.phone.exception.BindAxbException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public abstract class AbstractAxbBindProvider implements AxbBindProvider {

    private final IAcsClient acsClient;

    public AbstractAxbBindProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public BindAxbResponse bindAxb(AxbBindRequest request) throws BindAxbException {
        try {
            BindAxbRequest axbRequest = buildBindAxbRequest(request);

            BindAxbResponse response = acsClient.getAcsResponse(axbRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new BindAxbException(e);
        }

        return null;
    }

    protected abstract BindAxbRequest buildBindAxbRequest(AxbBindRequest request);
}
