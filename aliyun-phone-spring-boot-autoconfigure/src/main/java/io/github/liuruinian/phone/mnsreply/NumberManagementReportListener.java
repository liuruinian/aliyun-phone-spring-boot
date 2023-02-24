package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import io.github.liuruinian.phone.store.report.NumberManagementReport;
import io.github.liuruinian.phone.store.report.NumberManagementReportStore;
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
public class NumberManagementReportListener implements MessageListener, ApplicationContextAware {

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

        // parameters reference: https://help.aliyun.com/document_detail/421097.html
        String secret_no = messageBody.getString("secret_no");
        String city = messageBody.getString("city");
        String partner_key = messageBody.getString("partner_key");
        String operating_time = messageBody.getString("operating_time");
        Integer status = messageBody.getInteger("status");

        if (log.isInfoEnabled()) {
            log.info("secret_no: {}", secret_no);
            log.info("city: {}", city);
            log.info("partner_key: {}", partner_key);
            log.info("operating_time: {}", operating_time);
            log.info("status: {}", status);
        }

        // build NumberManagementReport
        NumberManagementReport numberManagementReport = NumberManagementReport.builder()
                .secretNo(secret_no)
                .city(city)
                .partnerKey(partner_key)
                .operatingTime(operating_time)
                .status(status)
                .build();

        // get bean: NumberManagementReportStore
        try {
            NumberManagementReportStore store = context.getBean(NumberManagementReportStore.class);
            boolean added = store.addNumberManagementReports(Collections.singleton(numberManagementReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a number management report added.");
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store number management report failed, no bean found: NumberManagementReportStore.");
            }
        }

        // ACK
        return true;
    }
}
