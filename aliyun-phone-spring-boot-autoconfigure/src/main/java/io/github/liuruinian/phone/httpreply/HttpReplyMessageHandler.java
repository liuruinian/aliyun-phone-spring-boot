package io.github.liuruinian.phone.httpreply;

import com.alibaba.fastjson.JSONArray;

/**
 * @author liuruinian
 * @since 2023-02-24
 */
public interface HttpReplyMessageHandler {

    /**
     * @param messageBody http reply message body
     * @return handle message number
     */
    Integer handleReplyMessage(JSONArray messageBody);
}
