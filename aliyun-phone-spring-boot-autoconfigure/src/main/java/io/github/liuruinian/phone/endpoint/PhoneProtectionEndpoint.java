package io.github.liuruinian.phone.endpoint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuruinian
 * @since 2023-02-08
 */
@RestController
@Slf4j
public class PhoneProtectionEndpoint {

    public String secretStartRecordCallback(@RequestBody JSONArray messageBody) {
        log.info("receive a http secret start record: \n{}", JSONObject.toJSONString(messageBody, true));
        return "{ \"code\": 0, \"msg\": \"成功\" }";
    }
}
