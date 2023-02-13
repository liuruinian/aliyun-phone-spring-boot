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
public class RecordDownloadUrlRequest implements Serializable {

    /**
     * 号码池Key <br>
     * <p>
     * eg: FC2256****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * 产品类型。
     * <p>
     * 取值：<br>
     * 1.AXB_170 <br>
     * 2.AXN_170 <br>
     * 3.AXN_95 <br>
     * 4.AXN_EXTENSION_REUSE
     * <p>
     * eg: AXB_170
     */
    @JsonProperty(value = "ProductType")
    private String productType;

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
