package io.github.liuruinian.phone.api.state.provider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import io.github.liuruinian.phone.domain.state.SecretNoDetailRequest;
import io.github.liuruinian.phone.domain.state.SubsIdRequest;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;
import io.github.liuruinian.phone.exception.StateQueryException;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public abstract class AbstractStateQueryProvider implements StateQueryProvider {

    private final IAcsClient acsClient;

    public AbstractStateQueryProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public QuerySubscriptionDetailResponse querySubscriptionDetail(SubscriptionDetailRequest request) throws StateQueryException {
        try {
            QuerySubscriptionDetailRequest detailRequest = buildQuerySubscriptionDetailRequest(request);

            QuerySubscriptionDetailResponse response = acsClient.getAcsResponse(detailRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new StateQueryException(e);
        }

        return null;
    }

    protected abstract QuerySubscriptionDetailRequest buildQuerySubscriptionDetailRequest(SubscriptionDetailRequest request);

    @Override
    public QuerySubsIdResponse querySubsId(SubsIdRequest request) throws StateQueryException {
        try {
            QuerySubsIdRequest subsIdRequest = buildQuerySubsIdRequest(request);

            QuerySubsIdResponse response = acsClient.getAcsResponse(subsIdRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new StateQueryException(e);
        }

        return null;
    }

    protected abstract QuerySubsIdRequest buildQuerySubsIdRequest(SubsIdRequest request);

    @Override
    public QuerySecretNoDetailResponse querySecretNoDetail(SecretNoDetailRequest request) throws StateQueryException {
        try {
            QuerySecretNoDetailRequest secretNoDetailRequest = buildQuerySecretNoDetailRequest(request);

            QuerySecretNoDetailResponse response = acsClient.getAcsResponse(secretNoDetailRequest);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            throw new StateQueryException(e);
        }

        return null;
    }

    protected abstract QuerySecretNoDetailRequest buildQuerySecretNoDetailRequest(SecretNoDetailRequest request);
}
