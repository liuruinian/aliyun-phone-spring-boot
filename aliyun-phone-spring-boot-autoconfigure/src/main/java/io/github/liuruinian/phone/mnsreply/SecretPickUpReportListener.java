package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import io.github.liuruinian.phone.store.report.SecretPickUpReport;
import io.github.liuruinian.phone.store.report.SecretPickUpReportStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;

/**
 * @author liuruinian
 * @since 2023-02-24
 */
@Slf4j
public class SecretPickUpReportListener implements MessageListener, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
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

        // parameters reference: https://help.aliyun.com/document_detail/400454.html
        String phone_no = messageBody.getString("phone_no");
        String secret_no = messageBody.getString("secret_no");
        String peer_no = messageBody.getString("peer_no");
        Integer call_type = messageBody.getInteger("call_type");
        String call_id = messageBody.getString("call_id");
        String call_time = messageBody.getString("call_time");
        String pool_key = messageBody.getString("pool_key");
        String ringTime = messageBody.getString("ringTime");
        String start_time = messageBody.getString("start_time");
        Long sub_id = messageBody.getLong("sub_id");
        String city = messageBody.getString("city");
        String out_id = messageBody.getString("out_id");

        if (log.isInfoEnabled()) {
            log.info("phone_no: {}", phone_no);
            log.info("secret_no: {}", secret_no);
            log.info("peer_no: {}", peer_no);
            log.info("call_type: {}", call_type);
            log.info("call_id: {}", call_id);
            log.info("call_time: {}", call_time);
            log.info("pool_key: {}", pool_key);
            log.info("ringTime: {}", ringTime);
            log.info("start_time: {}", start_time);
            log.info("sub_id: {}", sub_id);
            log.info("city: {}", city);
            log.info("out_id: {}", out_id);
        }

        // build SecretPickUpReport
        SecretPickUpReport secretPickUpReport = SecretPickUpReport.builder()
                .phoneNo(phone_no)
                .secretNo(secret_no)
                .peerNo(peer_no)
                .callType(call_type)
                .callId(call_id)
                .callTime(call_time)
                .poolKey(pool_key)
                .ringTime(ringTime)
                .startTime(start_time)
                .subId(sub_id)
                .city(city)
                .outId(out_id)
                .build();

        // get bean: SecretPickUpReportStore
        try {
            SecretPickUpReportStore store = context.getBean(SecretPickUpReportStore.class);
            boolean added = store.addSecretPickUpReports(Collections.singleton(secretPickUpReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a secret pick up report added.");
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store secret pick up report failed, no bean found: SecretPickUpReportStore.");
            }
        }

        // ACK
        return true;
    }
}
