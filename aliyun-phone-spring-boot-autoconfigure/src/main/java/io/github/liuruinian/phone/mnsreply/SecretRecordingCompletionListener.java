package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import io.github.liuruinian.phone.store.secret.SecretRecording;
import io.github.liuruinian.phone.store.secret.SecretRecordingStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;

/**
 * @author liuruinian
 * @since 2023-02-13
 *
 * The recording completes the event message listening.
 */
@Slf4j
public class SecretRecordingCompletionListener implements MessageListener, ApplicationContextAware {

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

        // parameters reference: https://help.aliyun.com/document_detail/109203.html
        String pool_key = messageBody.getString("pool_key");
        Long sub_id = messageBody.getLong("sub_id");
        String call_id = messageBody.getString("call_id");
        String call_time = messageBody.getString("call_time");
        if (log.isInfoEnabled()) {
            log.info("pool_key: {}", pool_key);
            log.info("sub_id: {}", sub_id);
            log.info("call_id: {}", call_id);
            log.info("call_time: {}", call_time);
        }

        // build SecretRecording
        SecretRecording secretRecording = SecretRecording.builder()
                .poolKey(pool_key)
                .subId(sub_id)
                .callId(call_id)
                .callTime(call_time)
                .build();

        try {
            SecretRecordingStore recordingStore = context.getBean(SecretRecordingStore.class);
            boolean added = recordingStore.addSecretEndReports(Collections.singleton(secretRecording));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a secret recording added, call_id: {}, call_time: {}.", secretRecording.getCallId(), secretRecording.getCallTime());
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store secret recording failed, no bean found: SecretRecordingStore.");
            }
        }

        // ACK
        return true;
    }
}
