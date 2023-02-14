package io.github.liuruinian.phone.api.axg.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import io.github.liuruinian.phone.domain.axg.AxgCreateGroupRequest;
import io.github.liuruinian.phone.domain.axg.UpdateAxgGroupRequest;
import io.github.liuruinian.phone.exception.AxgGroupException;
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
    public BindAxgResponse bindAxg(AxgBindRequest request) throws BindAxgException {
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

    @Override
    public CreateAxgGroupResponse createAxgGroup(AxgCreateGroupRequest request) throws AxgGroupException {
        try {
            CreateAxgGroupRequest groupRequest = buildAxgCreateGroupRequest(request);

            CreateAxgGroupResponse response = acsClient.getAcsResponse(groupRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new AxgGroupException(e);
        }

        return null;
    }

    protected abstract CreateAxgGroupRequest buildAxgCreateGroupRequest(AxgCreateGroupRequest request);

    @Override
    public OperateAxgGroupResponse updateAxgGroup(UpdateAxgGroupRequest request) throws AxgGroupException {
        try {
            OperateAxgGroupRequest groupRequest = buildOperateAxgGroupRequest(request);

            OperateAxgGroupResponse response = acsClient.getAcsResponse(groupRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new AxgGroupException(e);
        }

        return null;
    }

    protected abstract OperateAxgGroupRequest buildOperateAxgGroupRequest(UpdateAxgGroupRequest request);
}
