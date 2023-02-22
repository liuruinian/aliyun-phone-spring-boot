package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import io.github.liuruinian.phone.store.report.SecretStartReport;
import io.github.liuruinian.phone.store.report.SecretStartReportStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;

/**
 * @author liuruinian
 * @since 2023-02-13
 *
 * listen for the secret start report when a call is initiated.
 */
@Slf4j
public class SecretStartReportListener implements MessageListener, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean dealMessage(com.aliyun.mns.model.Message message) {
        if (log.isInfoEnabled()) {
            log.info("message handle: {}", message.getReceiptHandle());
            log.info("message id: {}", message.getMessageId());
            log.info("message dequeue count: {}", message.getDequeueCount());
        }

        // utf-8 message body
        String s = message.getMessageBodyAsString();
        JSONObject messageBody = JSONObject.parseObject(s);

        // parameters reference: https://help.aliyun.com/document_detail/109200.html
        String poolKey = messageBody.getString("pool_key");
        Long   subId = messageBody.getLong("sub_id");
        String callId = messageBody.getString("call_id");
        String phoneNo = messageBody.getString("phone_no");
        String secretNo = messageBody.getString("secret_no");
        String peerNo = messageBody.getString("peer_no");
        String calledDisplayNo = messageBody.getString("called_display_no");
        Integer callType = messageBody.getInteger("call_type");
        String callTime = messageBody.getString("call_time");
        String outId = messageBody.getString("out_id");
        String controlMsg = messageBody.getString("control_msg");

        if (log.isInfoEnabled()) {
            log.info("pool_key: {}", poolKey);
            log.info("sub_id: {}", subId);
            log.info("call_id: {}", callId);
            log.info("phone_no: {}", phoneNo);
            log.info("secret_no: {}", secretNo);
            log.info("peer_no: {}", peerNo);
            log.info("called_display_no: {}", calledDisplayNo);
            log.info("call_type: {}", callType);
            log.info("call_time: {}", callTime);
            log.info("out_id: {}", outId);
            log.info("control_msg: {}", controlMsg);
        }

        // build SecretStartReport
        SecretStartReport secretStartReport = SecretStartReport.builder()
                .poolKey(poolKey)
                .subId(subId)
                .callId(callId)
                .phoneNo(phoneNo)
                .secretNo(secretNo)
                .peerNo(peerNo)
                .calledDisplayNo(calledDisplayNo)
                .callType(callType)
                .callTime(callTime)
                .outId(outId)
                .controlMsg(controlMsg)
                .build();

        // get bean: SecretStartReportStore
        try {
            SecretStartReportStore reportStore = applicationContext.getBean(SecretStartReportStore.class);
            boolean added = reportStore.addSecretStartReports(Collections.singleton(secretStartReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a secret start report added, call_id: {}, call_time: {}.", secretStartReport.getCallId(), secretStartReport.getCallTime());
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store secret start report failed, no bean found: SecretStartReportStore.");
            }
        }

        // ACK
        return true;
    }
}
