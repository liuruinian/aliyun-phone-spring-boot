package io.github.liuruinian.phone.listener;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuruinian
 * @since 2023-02-13
 *
 * The recording completes the event message listening.
 */
@Slf4j
public class SecretRecordingCompletionListener implements MessageListener {

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


        // ACK
        return true;
    }
}
