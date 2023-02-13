package io.github.liuruinian.phone.reply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import io.github.liuruinian.phone.constants.ReleaseCauseMap;
import io.github.liuruinian.phone.store.SecretEndReport;
import io.github.liuruinian.phone.store.SecretEndReportStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;

/**
 * @author liuruinian
 * @since 2023-02-13
 *
 * listen for the secret end report after the call ends.
 */
@Slf4j
public class SecretEndReportListener implements MessageListener, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean dealMessage(Message message) {
        if (log.isInfoEnabled()) {
            log.info("message handle: {}", message.getReceiptHandle());
            log.info("message id: {}", message.getMessageId());
            log.info("message dequeue count: {}", message.getDequeueCount());
        }

        // utf-8 message body
        String s = message.getMessageBodyAsString();
        JSONObject messageBody = JSONObject.parseObject(s);

        // parameters reference: https://help.aliyun.com/document_detail/109201.html
        String pool_key = messageBody.getString("pool_key");
        Long sub_id = messageBody.getLong("sub_id");
        String record_url = messageBody.getString("record_url");
        String ring_record_url = messageBody.getString("ring_record_url");
        String call_id = messageBody.getString("call_id");
        String phone_no = messageBody.getString("phone_no");
        String secret_no = messageBody.getString("secret_no");
        String peer_no = messageBody.getString("peer_no");
        String called_display_no = messageBody.getString("called_display_no");
        Integer call_type = messageBody.getInteger("call_type");
        String call_time = messageBody.getString("call_time");
        String start_time = messageBody.getString("start_time");
        String call_out_time = messageBody.getString("call_out_time");
        String ring_time = messageBody.getString("ring_time");
        String free_ring_time = messageBody.getString("free_ring_time");
        String release_time = messageBody.getString("release_time");
        Integer sms_number = messageBody.getInteger("sms_number");
        Integer release_dir = messageBody.getInteger("release_dir");
        String out_id = messageBody.getString("out_id");
        Integer unconnected_cause = messageBody.getInteger("unconnected_cause");
        Integer release_cause = messageBody.getInteger("release_cause");
        String control_msg = messageBody.getString("control_msg");

        if (log.isInfoEnabled()) {
            log.info("pool_key: {}", pool_key);
            log.info("sub_id: {}", sub_id);
            log.info("record_url: {}", record_url);
            log.info("ring_record_url: {}", ring_record_url);
            log.info("call_id: {}", call_id);
            log.info("phone_no: {}", phone_no);
            log.info("secret_no: {}", secret_no);
            log.info("peer_no: {}", peer_no);
            log.info("called_display_no: {}", called_display_no);
            log.info("call_type: {}", call_type);
            log.info("call_time: {}", call_time);
            log.info("start_time: {}", start_time);
            log.info("call_out_time: {}", call_out_time);
            log.info("ring_time: {}", ring_time);
            log.info("free_ring_time: {}", free_ring_time);
            log.info("release_time: {}", release_time);
            log.info("sms_number: {}", sms_number);
            log.info("release_dir: {}", release_dir);
            log.info("out_id: {}", out_id);
            log.info("unconnected_cause: {}", unconnected_cause);
            log.info("release_cause: {}", release_cause);
            log.info("control_msg: {}", control_msg);
        }

        // build SecretEndReport
        SecretEndReport secretEndReport = SecretEndReport.builder()
                .poolKey(pool_key)
                .subId(sub_id)
                .recordUrl(record_url)
                .ringRecordUrl(ring_record_url)
                .callId(call_id)
                .phoneNo(phone_no)
                .secretNo(secret_no)
                .peerNo(peer_no)
                .calledDisplayNo(called_display_no)
                .callType(call_type)
                .callTime(call_time)
                .startTime(start_time)
                .callOutTime(call_out_time)
                .ringTime(ring_time)
                .freeRingTime(free_ring_time)
                .releaseTime(release_time)
                .smsNumber(sms_number)
                .releaseDir(release_dir)
                .outId(out_id)
                .unconnectedCause(unconnected_cause)
                .releaseCause(release_cause)
                .releaseCauseDescribe(ReleaseCauseMap.obtainCauseDescribe(release_cause))
                .controlMsg(control_msg)
                .build();

        // get bean: SecretEndReportStore
        try {
            SecretEndReportStore reportStore = applicationContext.getBean(SecretEndReportStore.class);
            boolean added = reportStore.addSecretEndReports(Collections.singleton(secretEndReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a secret end report added, call_id: {}, call_time: {}.", secretEndReport.getCallId(), secretEndReport.getCallTime());
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store secret end report failed, no bean found: SecretEndReportStore.");
            }
        }

        // ACK
        return true;
    }
}
