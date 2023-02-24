package io.github.liuruinian.phone.endpoint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.phone.httpreply.HttpReplyMessageHandleStrategy;
import io.github.liuruinian.phone.httpreply.HttpReplyMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuruinian
 * @since 2023-02-14
 */
@RestController
@Slf4j
public class HttpReplyCallbackEndpoint {

    /**
     * type for callback.
     * <p>
     * reference: https://help.aliyun.com/document_detail/299955.html
     * <p>
     * SecretStartReport <br>
     * SecretEndReport <br>
     * SecretAsrReport <br>
     * SecretRecording <br>
     * SmartLogisticsReport <br>
     * SecretRingReport <br>
     * SecretPickUpReport <br>
     * NumberManagementReport
     */
    @PostMapping(path = "/http/reply/callback/{type}")
    public String replyMessageCallback(@RequestBody JSONArray messageBody, @PathVariable("type") String type) {
        if (log.isInfoEnabled()) {
            log.info("receive message body: \n{}", JSONObject.toJSONString(messageBody, true));
        }

        // handle secret start report callback
        HttpReplyMessageHandleStrategy.setStrategyName(type);
        HttpReplyMessageHandler strategy = HttpReplyMessageHandleStrategy.getStrategy();
        if (strategy != null) {
            strategy.handleReplyMessage(messageBody);
        }

        return "{ \"code\": 0, \"msg\": \"成功\" }";
    }
}
