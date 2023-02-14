package io.github.liuruinian.phone.endpoint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(path = "/secret_start_record/callback")
    public String secretStartRecordCallback(@RequestBody JSONArray messageBody) {
        log.info("receive a http secret start record: \n{}", JSONObject.toJSONString(messageBody, true));

        // handle secret start report callback

        return "{ \"code\": 0, \"msg\": \"成功\" }";
    }
}
