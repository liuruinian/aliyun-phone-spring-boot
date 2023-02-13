package io.github.liuruinian.phone.endpoint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author liuruinian
 * @since 2023-02-08
 */
@AliPhoneEndpoint
@Slf4j
public class PhoneProtectionEndpoint {

    @PostMapping(path = "/secret_start_record/callback")
    public String secretStartRecordCallback(@RequestBody JSONArray messageBody) {
        log.info("receive a http secret start record: \n{}", JSONObject.toJSONString(messageBody, true));
        return "{ \"code\": 0, \"msg\": \"成功\" }";
    }
}
