package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.aliyuncs.dyplsapi.model.v20170525.GetSecretAsrDetailResponse;
import io.github.liuruinian.phone.store.report.SecretAsrReport;
import io.github.liuruinian.phone.store.report.SecretAsrReportStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;
import java.util.List;

/**
 * @author liuruinian
 * @since 2023-02-22
 */
@Slf4j
public class SecretAsrReportListener implements MessageListener, ApplicationContextAware {

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

        // parameters reference: https://help.aliyun.com/document_detail/305594.html
        String callId = messageBody.getString("callId");
        Long bizDuration = messageBody.getLong("bizDuration");
        String partnerKey = messageBody.getString("partnerKey");
        String secretNo = messageBody.getString("secretNo");
        String statusCode = messageBody.getString("statusCode");
        List<GetSecretAsrDetailResponse.Data.SecretAsrSentenceDTO> sentences =
                (List<GetSecretAsrDetailResponse.Data.SecretAsrSentenceDTO>) messageBody.get("sentences");

        if (log.isInfoEnabled()) {
            log.info("callId: {}", callId);
            log.info("bizDuration: {}", bizDuration);
            log.info("partnerKey: {}", partnerKey);
            log.info("secretNo: {}", secretNo);
            log.info("statusCode: {}", statusCode);
            log.info("sentences: \n{}", JSONObject.toJSONString(sentences, true));
        }

        // build SecretAsrReport
        SecretAsrReport secretAsrReport = SecretAsrReport.builder()
                .callId(callId)
                .bizDuration(bizDuration)
                .partnerKey(partnerKey)
                .secretNo(secretNo)
                .statusCode(statusCode)
                .sentences(sentences)
                .build();

        // get bean: SecretAsrReportStore
        try {
            SecretAsrReportStore store = context.getBean(SecretAsrReportStore.class);
            boolean added = store.addSecretAsrReports(Collections.singleton(secretAsrReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a secret asr report added.");
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store secret asr report failed, no bean found: SecretAsrReportStore.");
            }
        }

        // ACK
        return true;
    }
}
