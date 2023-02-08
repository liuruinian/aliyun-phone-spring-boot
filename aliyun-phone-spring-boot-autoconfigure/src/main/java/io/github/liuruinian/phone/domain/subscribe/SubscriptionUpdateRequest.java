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
public class SubscriptionUpdateRequest implements Serializable {

    /**
     * 号码池Key
     * <p>
     * eg: FC2256****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * 产品类型。
     * <p>
     * 取值：
     * AXB_170。
     * AXN_170。
     * AXN_95。
     * AXN_EXTENSION_REUSE
     * <p>
     * 适用于原阿里大于客户，阿里云用户可忽略。
     * <p>
     * 当PoolKey为空时必填。
     * <p>
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
     * 号码绑定关系中的X号码.
     * <p>
     * eg: 1390000****
     */
    @JsonProperty(value = "PhoneNoX")
    private String phoneNoX;

    /**
     * 设置绑定关系中的A号码.
     * <p>
     * eg: 1390000****
     */
    @JsonProperty(value = "PhoneNoA")
    private String phoneNoA;

    /**
     * 设置绑定关系中的B号码.
     * <p>
     * eg: 1390000****
     */
    @JsonProperty(value = "PhoneNoB")
    private String phoneNoB;

    /**
     * 设置绑定关系中的G号码组ID.
     * <p>
     * eg: 1234
     */
    @JsonProperty(value = "GroupId")
    private String groupId;

    /**
     * 设置单通呼叫限制.
     * <p>
     * 取值：
     * CONTROL_AX_DISABLE：A号码无法呼叫X号码。
     * CONTROL_BX_DISABLE：B号码无法呼叫X号码。
     * CONTROL_CLEAR_DISABLE：清除呼叫限制。
     * <p>
     * 当OperateType指定为updateCallRestrict时必填.
     * <p>
     * eg: CONTROL_BX_DISABLE
     */
    @JsonProperty(value = "CallRestrict")
    private String callRestrict;

    /**
     * 重新设置绑定关系的过期时间.
     * <p>
     * OperateType为updateExpire时必填.
     * <p>
     * 过期时间必须晚于当前时间1分钟以上.
     */
    @JsonProperty(value = "Expiration")
    private String expiration;

    /**
     * 重置绑定关系中的号码显示逻辑。固定取值：1（主被叫显示中间号码X）。
     * <p>
     * 由于运营商监管限制，呼叫时显示真实号码的设置不生效。
     */
    @JsonProperty(value = "CallDisplayType")
    private Integer callDisplayType = 1;

    /**
     * 重新设置绑定关系中的OutId
     * <p>
     * eg: abcdef
     */
    @JsonProperty(value = "OutId")
    private String outId;

    /**
     * 重新设置绑定关系中的录音状态.
     * <p>
     * 该参数没有默认值，不传该参数就不更新对应字段.
     * <p>
     * eg: true
     */
    @JsonProperty(value = "IsRecordingEnabled")
    private Boolean isRecordingEnabled;

    /**
     * 修改绑定关系的操作.
     * <p>
     * 1.updateNoA：修改A号码。
     * <p>
     * 2.updateNoB：修改B号码。
     * <p>
     * 3.updateExpire：修改绑定关系有效期。
     * <p>
     * 4.updateAxgGroup：修改G号码组。
     * <p>
     * 5.updateCallRestrict：设置单通呼叫限制。
     * <p>
     * 6.updateCallDisplayType：更新呼叫显号逻辑。
     * <p>
     * 7.updateOutId：更新OutId字段。
     * <p>
     * 8.updateIsRecordingEnabled：更新绑定中录音状态。
     * <p>
     * eg: updateNoA
     */
    @JsonProperty(value = "OperateType")
    private String operateType;

    /**
     * 更新绑定关系中的企业彩铃放音编码.
     * <p>
     * AXB产品：<br>
     * ------- AXB中，A号码打X的铃音设置：AXBRing_A <br>
     * ------- AXB中，B号码打X的铃音设置：AXBRing_B
     * <p>
     * AXN产品：
     * ------- AXN中，A号码打X的铃音设置（有回拨号码）：AXNRing_AB <br>
     * ------- AXN中，A号码打X的铃音设置（无回拨号码）：AXNRing_A <br>
     * ------- AXN中，N侧号码打X的铃音设置：AXNRing_N
     * <p>
     * AXG产品：<br>
     * ------- AXG中，A号码打X的铃音设置（有回拨号码）：AXGRing_AB <br>
     * ------- AXG中，A号码打X的铃音设置（无回拨号码）：AXGRing_A <br>
     * ------- AXG中，G组号码打X的铃音设置：AXGRing_G
     */
    @JsonProperty(value = "RingConfig")
    private String ringConfig;

    /**
     * ASR状态
     * <p>
     * false：关闭（默认值）<br>
     * true：开启
     */
    @JsonProperty(value = "ASRStatus")
    private Boolean asrStatus;

    /**
     * ASR模型ID。
     * <p>
     * eg: 980abddb908f48e8b987cb2cd303****
     */
    @JsonProperty(value = "ASRModelId")
    private String asrModelId;
}
