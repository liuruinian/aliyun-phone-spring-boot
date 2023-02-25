package io.github.liuruinian.phone.endpoint;

import com.alibaba.fastjson.JSONArray;
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
     * mode_secret_start_report <br>
     * mode_secret_asr_report <br>
     * mode_secret_recording <br>
     * mode_secret_end_report <br>
     * mode_smart_logistics_report <br>
     * mode_secret_ring_report <br>
     * mode_secret_pickup_report <br>
     * mode_number_management_report
     * <p>
     * reference: https://help.aliyun.com/document_detail/299955.html
     * <p>
     *
     * @see HttpReplyMessageHandleStrategy
     */
    @PostMapping(path = "/http/reply/callback/{type}")
    public String replyMessageCallback(@RequestBody JSONArray messageBody, @PathVariable("type") String type) {
        // handle reply callback
        HttpReplyMessageHandleStrategy.setStrategyName(type);
        HttpReplyMessageHandler strategy = HttpReplyMessageHandleStrategy.getStrategy();
        if (strategy != null) {
            strategy.handleReplyMessage(messageBody);
        }

        return "{ \"code\": 0, \"msg\": \"成功\" }";
    }
}
