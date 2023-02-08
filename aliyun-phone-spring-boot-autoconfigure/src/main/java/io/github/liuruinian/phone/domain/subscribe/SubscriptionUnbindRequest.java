package io.github.liuruinian.phone.domain.subscribe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionUnbindRequest implements Serializable {

    /**
     * 号码池Key
     * <p>
     * eg: FC2256****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * 产品类型。
     *
     * 固定取值: AXB_170 <br>
     * 适用于原阿里大于客户，阿里云用户可忽略。<br>
     * eg: AXB_170
     */
    @JsonProperty(value = "ProductType")
    private String productType;

    /**
     * 绑定关系ID。
     * <p>
     * eg: 100000076879****
     */
    @JsonProperty(value = "SubsId")
    private String subsId;

    /**
     * 隐私号码。<br>
     * 调用BindAXG等号码绑定接口时指定或自动分配的X号码。<br>
     * eg: 1390000****
     */
    @JsonProperty(value = "SecretNo")
    private String secretNo;
}
