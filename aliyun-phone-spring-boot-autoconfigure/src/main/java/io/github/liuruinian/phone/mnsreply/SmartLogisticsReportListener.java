package io.github.liuruinian.phone.mnsreply;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import io.github.liuruinian.phone.store.report.SmartLogisticsReport;
import io.github.liuruinian.phone.store.report.SmartLogisticsReportStore;
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
public class SmartLogisticsReportListener implements MessageListener, ApplicationContextAware {

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

        // parameters reference: https://help.aliyun.com/document_detail/375220.html
        String cpCode = messageBody.getString("cpCode");
        String logisticsStatus = messageBody.getString("logisticsStatus");
        String mailNo = messageBody.getString("mailNo");
        String logisticsStatusDesc = messageBody.getString("logisticsStatusDesc");
        String lastLogisticDetail = messageBody.getString("lastLogisticDetail");
        String logisticsGmtModified = messageBody.getString("logisticsGmtModified");
        String packageDyn = messageBody.getString("packageDyn");
        String aliyunPrice = messageBody.getString("aliyunprice");
        String city = messageBody.getString("city");
        String bizKey = messageBody.getString("bizKey");
        String outerOrderCode = messageBody.getString("outerOrderCode");

        if (log.isInfoEnabled()) {
            log.info("cpCode: {}", cpCode);
            log.info("logisticsStatus: {}", logisticsStatus);
            log.info("mailNo: {}", mailNo);
            log.info("logisticsStatusDesc: {}", logisticsStatusDesc);
            log.info("lastLogisticDetail: {}", lastLogisticDetail);
            log.info("logisticsGmtModified: {}", logisticsGmtModified);
            log.info("packageDyn: {}", packageDyn);
            log.info("aliyunPrice: {}", aliyunPrice);
            log.info("city: {}", city);
            log.info("bizKey: {}", bizKey);
            log.info("outerOrderCode: {}", outerOrderCode);
        }

        // build SmartLogisticsReport
        SmartLogisticsReport smartLogisticsReport = SmartLogisticsReport.builder()
                .cpCode(cpCode)
                .logisticsStatus(logisticsStatus)
                .mailNo(mailNo)
                .logisticsStatusDesc(logisticsStatusDesc)
                .lastLogisticDetail(lastLogisticDetail)
                .logisticsGmtModified(logisticsGmtModified)
                .packageDyn(packageDyn)
                .aliyunPrice(aliyunPrice)
                .city(city)
                .bizKey(bizKey)
                .outerOrderCode(outerOrderCode)
                .build();

        // get bean: SmartLogisticsReportStore
        try {
            SmartLogisticsReportStore store = context.getBean(SmartLogisticsReportStore.class);
            boolean added = store.addSmartLogisticsReports(Collections.singleton(smartLogisticsReport));
            if (added) {
                if (log.isInfoEnabled()) {
                    log.info("a smart logistics report added.");
                }
            }
        } catch (BeansException e) {
            if (log.isErrorEnabled()) {
                log.error("store smart logistics report failed, no bean found: SmartLogisticsReportStore.");
            }
        }

        // ACK
        return true;
    }
}
