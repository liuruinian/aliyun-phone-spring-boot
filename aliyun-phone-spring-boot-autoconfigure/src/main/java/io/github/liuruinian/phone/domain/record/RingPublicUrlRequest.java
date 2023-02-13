package io.github.liuruinian.phone.domain.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RingPublicUrlRequest implements Serializable {

    /**
     * 是否需要校验绑定关系标识.
     * <p>
     * eg: false
     */
    @JsonProperty(value = "CheckSubs")
    private Boolean checkSubs;

    /**
     * 号码池Key.
     * <p>
     * eg: FC12256****
     */
    @JsonProperty(value = "PartnerKey")
    private String partnerKey;

    /**
     * 呼叫记录ID，用于标识一条通话记录.
     * <p>
     * eg: abcedf1234
     */
    @JsonProperty(value = "CallId")
    private String callId;

    /**
     * 呼叫记录中的呼叫发起时间.
     * <p>
     * eg: 2019-03-05 12:00:00
     */
    @JsonProperty(value = "CallTime")
    private String callTime;
}
