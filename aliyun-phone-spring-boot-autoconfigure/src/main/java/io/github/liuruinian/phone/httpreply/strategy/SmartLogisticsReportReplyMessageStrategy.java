package io.github.liuruinian.phone.httpreply.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.phone.httpreply.HttpReplyMessageHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmartLogisticsReportReplyMessageStrategy implements HttpReplyMessageHandler {

    @Override
    public Integer handleReplyMessage(JSONArray messageBody) {
        if (log.isInfoEnabled()) {
            log.info("receive smart logistics report reply message body: \n{}", JSONObject.toJSONString(messageBody, true));
        }

        return null;
    }
}
