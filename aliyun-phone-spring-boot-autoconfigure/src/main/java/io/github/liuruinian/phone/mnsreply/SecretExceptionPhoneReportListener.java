package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import io.github.liuruinian.phone.store.report.SecretExceptionPhoneReport;
import io.github.liuruinian.phone.store.report.SecretExceptionPhoneReportStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.Collections;

/**
 * @author liuruinian
 * @since 2023-02-22
 *
 * Subscribe to the exception number status listener.
 */
@Slf4j
public class SecretExceptionPhoneReportListener implements MessageListener, ApplicationContextAware {

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

        // parameter reference: https://help.aliyun.com/document_detail/354287.html
        String secretNo = messageBody.getString("secretNo");
        String partnerKey = messageBody.getString("partnerKey");
        Integer noStatus = messageBody.getInteger("noStatus");
        Long timestamp = messageBody.getLong("timestamp");

        if (log.isInfoEnabled()) {
            log.info("secretNo: {}", secretNo);
            log.info("partnerKey: {}", partnerKey);
            log.info("noStatus: {}", noStatus);
            log.info("timestamp: {}", timestamp);
        }

        // build SecretExceptionPhoneReport
        SecretExceptionPhoneReport exceptionPhoneReport = SecretExceptionPhoneReport.builder()
                .noStatus(noStatus)
                .partnerKey(partnerKey)
                .secretNo(secretNo)
                .timestamp(timestamp)
                .build();

        try {
            SecretExceptionPhoneReportStore store = context.getBean(SecretExceptionPhoneReportStore.class);
            boolean added = store.addSecretEndReports(Collections.singleton(exceptionPhoneReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a secret exception phone report added, secret_no: {}, timestamp: {}.", exceptionPhoneReport.getSecretNo(), exceptionPhoneReport.getTimestamp());
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store secret exception phone report failed, no bean found: SecretExceptionPhoneReportStore.");
            }
        }

        // ACK
        return true;
    }
}
