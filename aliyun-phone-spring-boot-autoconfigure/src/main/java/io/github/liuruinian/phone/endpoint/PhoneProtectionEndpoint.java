package io.github.liuruinian.phone.endpoint;

import com.aliyuncs.dyplsapi.model.v20170525.*;
import io.github.liuruinian.phone.api.axb.delegate.AxbBindDelegate;
import io.github.liuruinian.phone.api.axg.delegate.AxgBindDelegate;
import io.github.liuruinian.phone.api.axn.delegate.AxnBindDelegate;
import io.github.liuruinian.phone.api.record.delegate.PhoneRecordDelegate;
import io.github.liuruinian.phone.api.state.delegate.StateQueryDelegate;
import io.github.liuruinian.phone.api.subscribe.delegate.SubscriptionOperationDelegate;
import io.github.liuruinian.phone.domain.axb.AxbBindRequest;
import io.github.liuruinian.phone.domain.axg.AxgBindRequest;
import io.github.liuruinian.phone.domain.axg.AxgCreateGroupRequest;
import io.github.liuruinian.phone.domain.axg.UpdateAxgGroupRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindExtensionRequest;
import io.github.liuruinian.phone.domain.axn.AxnBindRequest;
import io.github.liuruinian.phone.domain.record.RecordDownloadUrlRequest;
import io.github.liuruinian.phone.domain.record.RingPublicUrlRequest;
import io.github.liuruinian.phone.domain.record.SecretAsrDetailRequest;
import io.github.liuruinian.phone.domain.state.SecretNoDetailRequest;
import io.github.liuruinian.phone.domain.state.SubsIdRequest;
import io.github.liuruinian.phone.domain.state.SubscriptionDetailRequest;
import io.github.liuruinian.phone.domain.subscribe.SubscriptionUnbindRequest;
import io.github.liuruinian.phone.domain.subscribe.SubscriptionUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author liuruinian
 * @since 2023-02-08
 */
@RestController
@Slf4j
public class PhoneProtectionEndpoint {

    @Resource
    private AxnBindDelegate axnBindDelegate;

    @Resource
    private AxbBindDelegate axbBindDelegate;

    @Resource
    private AxgBindDelegate axgBindDelegate;

    @Resource
    private StateQueryDelegate stateQueryDelegate;

    @Resource
    private SubscriptionOperationDelegate subscriptionOperationDelegate;

    @Resource
    private PhoneRecordDelegate phoneRecordDelegate;

    // ~ --------------------------------------------- AXN -------------------------------------------------

    @PostMapping(path = "/bind_axn")
    public ResponseEntity<BindAxnResponse> bindAxn(@RequestBody AxnBindRequest request) {
        return ResponseEntity.ok(axnBindDelegate.bindAxn(request));
    }

    @PostMapping(path = "/bind_axn_extension")
    public ResponseEntity<BindAxnExtensionResponse> bindAxnExtension(@RequestBody AxnBindExtensionRequest request) {
        return ResponseEntity.ok(axnBindDelegate.bindAxnExtension(request));
    }

    // ~ --------------------------------------------- AXB -------------------------------------------------

    @PostMapping(path = "/bind_axb")
    public ResponseEntity<BindAxbResponse> bindAxb(@RequestBody AxbBindRequest request) {
        return ResponseEntity.ok(axbBindDelegate.bindAxb(request));
    }

    // ~ --------------------------------------------- AXG -------------------------------------------------

    @PostMapping(path = "/bind_axg")
    public ResponseEntity<BindAxgResponse> bindAxg(@RequestBody AxgBindRequest request) {
        return ResponseEntity.ok(axgBindDelegate.bindAxg(request));
    }

    @PostMapping(path = "/create_axg_group")
    public ResponseEntity<CreateAxgGroupResponse> createAxgGroup(@RequestBody AxgCreateGroupRequest request) {
        return ResponseEntity.ok(axgBindDelegate.createAxgGroup(request));
    }

    @PostMapping(path = "/update_axg_group")
    public ResponseEntity<OperateAxgGroupResponse> updateAxgGroup(@RequestBody UpdateAxgGroupRequest request) {
        return ResponseEntity.ok(axgBindDelegate.updateAxgGroup(request));
    }

    // ~ ---------------------------------------------- Record ---------------------------------------------

    @PostMapping(path = "/query_record_file_download_url")
    public ResponseEntity<QueryRecordFileDownloadUrlResponse> queryRecordFileDownloadUrl(@RequestBody RecordDownloadUrlRequest request) {
        return ResponseEntity.ok(phoneRecordDelegate.queryRecordFileDownloadUrl(request));
    }

    @PostMapping(path = "/query_ring_public_url")
    public ResponseEntity<GetTotalPublicUrlResponse> queryRingPublicUrl(@RequestBody RingPublicUrlRequest request) {
        return ResponseEntity.ok(phoneRecordDelegate.queryRingPublicUrl(request));
    }

    @PostMapping(path = "/query_secret_asr_detail")
    public ResponseEntity<GetSecretAsrDetailResponse> querySecretAsrDetail(@RequestBody SecretAsrDetailRequest request) {
        return ResponseEntity.ok(phoneRecordDelegate.querySecretAsrDetail(request));
    }

    // ~ ---------------------------------------------- State ----------------------------------------------

    @PostMapping(path = "/query_subscription_detail")
    public ResponseEntity<QuerySubscriptionDetailResponse> querySubscriptionDetail(@RequestBody SubscriptionDetailRequest request) {
        return ResponseEntity.ok(stateQueryDelegate.querySubscriptionDetail(request));
    }

    @PostMapping(path = "/query_subsId")
    public ResponseEntity<QuerySubsIdResponse> querySubsId(@RequestBody SubsIdRequest request) {
        return ResponseEntity.ok(stateQueryDelegate.querySubsId(request));
    }

    @PostMapping(path = "/query_secretno_detail")
    public ResponseEntity<QuerySecretNoDetailResponse> querySecretNoDetail(@RequestBody SecretNoDetailRequest request) {
        return ResponseEntity.ok(stateQueryDelegate.querySecretNoDetail(request));
    }

    // ~ ----------------------------------------------- Subscription ---------------------------------------

    @PostMapping(path = "/update_subscription")
    public ResponseEntity<UpdateSubscriptionResponse> updateSubscription(@RequestBody SubscriptionUpdateRequest request) {
        return ResponseEntity.ok(subscriptionOperationDelegate.updateSubscription(request));
    }

    @PostMapping(path = "/unbind_subscription")
    public ResponseEntity<UnbindSubscriptionResponse> unbindSubscription(@RequestBody SubscriptionUnbindRequest request) {
        return ResponseEntity.ok(subscriptionOperationDelegate.unbindSubscription(request));
    }
}
