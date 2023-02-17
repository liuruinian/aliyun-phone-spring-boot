package io.github.liuruinian.phone.domain.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDetailRequest implements Serializable {

    /**
     * 创建绑定关系API接口所返回的订购关系ID.
     * <p>
     * eg: 100000076879****
     */
    @JsonProperty(value = "SubsId")
    private String subsId;

    /**
     * 创建绑定关系API接口所返回的X号码
     * <p>
     * eg: 13900001234
     */
    @JsonProperty(value = "PhoneNoX")
    private String phoneNoX;

    /**
     * 号码池key
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * 产品类型.
     * <p>
     * AXB_170。<br>
     * AXN_170。<br>
     * AXN_95。<br>
     * AXN_EXTENSION_REUSE
     */
    @JsonProperty(value = "ProductType")
    private String productType;
}
