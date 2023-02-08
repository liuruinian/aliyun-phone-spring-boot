package io.github.liuruinian.phone.domain.axb;

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
public class AxbBindRequest implements Serializable {

    /**
     * 号码池Key <br>
     * <p>
     * eg: FC2256****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * AXB中的A号码。<br>
     * A号码可设置为手机号码或固定电话，固定电话需要加区号，区号和号码中间不需要加连字符 <br>
     * <p>
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoA")
    private String phoneNoA;

    /**
     * AXB中的B号码，A号码拨打X号码时会转接到B号码。<br>
     * B号码可设置为手机号码或固定电话，固定电话需要加区号，区号和号码中间不需要加连字符。<br>
     * <p>
     * eg: 138****0000
     */
    @JsonProperty(value = "PhoneNoB")
    private String phoneNoB;

    /**
     * AXB中的X号码。<br>
     * X号码是您绑定号码前登录号码隐私保护控制台或通过BuySecretNo接口购买的电话号码，用于转接电话。<br>
     * 如果未指定X号码时，将根据参数ExpectCity从指定号码池中随机指定一个号码作为X号码。<br>
     * <p>
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoX")
    private String phoneNoX;

    /**
     * 绑定关系的过期时间, 必须晚于当前时间1分钟以上。<br>
     * <p>
     * eg: 2021-09-05 12:00:00
     */
    @JsonProperty(value = "Expiration")
    private String expiration;

    /**
     * 指定城市进行X号码的选号。<br>
     * 1.如果当前号码池中没有该城市的可用号码，或未指定此参数，将从当前号码池中随机分配一个其他城市的号码作为X号码.<br>
     * 2.如果X号码分配模式配置为严格匹配模式，当符合条件的号码不存在时，系统会提示分配错误。<br>
     * <p>
     * eg: 上海
     */
    @JsonProperty(value = "ExpectCity")
    private String expectCity;

    /**
     * 是否需要针对该绑定关系产生的所有通话录制通话录音. <br>
     * <p>
     * true: 录音 <br>
     * false: 不录音（默认值）
     */
    @JsonProperty(value = "IsRecordingEnabled")
    private Boolean isRecordingEnabled = false;

    /**
     * 外部业务扩展字段，通话记录回执消息中会回传此参数。<br>
     * <p>
     * eg: abc123456
     */
    @JsonProperty(value = "OutId")
    private String outId;

    /**
     * 外部业务ID。<br>
     * <p>
     * eg: 34553330****
     */
    @JsonProperty(value = "OutOrderId")
    private String outOrderId;

    /**
     * 单通呼叫限制的状态。<br>
     * <p>
     * CONTROL_AX_DISABLE：A号码无法呼叫X号码。<br>
     * CONTROL_BX_DISABLE：B号码无法呼叫X号码。
     * <p>
     * eg: CONTROL_AX_DISABLE
     */
    @JsonProperty(value = "CallRestrict")
    private String callRestrict;

    /**
     * 重置绑定关系中的号码显示逻辑。固定取值：1（主被叫显示中间号码X）。<br>
     * 由于运营商监管限制，呼叫时显示真实号码的设置不生效。
     */
    @JsonProperty(value = "CallDisplayType")
    private Integer callDisplayType = 1;

    /**
     * 设置AXB绑定关系中的企业彩铃放音编码，<br>
     * 内容如下：<br>
     * ------ AXB中，A号码打X的铃音设置：AXBRing_A <br>
     * ------ AXB中，B号码打X的铃音设置：AXBRing_B
     * <p>
     * eg: {"AXBRing_B":"1000****1","AXBRing_A":"1000****1"}
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
    private Boolean asrStatus = false;

    /**
     * ASR模型ID。
     * <p>
     * eg: 7ee372834d2f4cc7ac0d0ab****
     */
    @JsonProperty(value = "ASRModelId")
    private String asrModelId;

    /**
     * 顺振时长。单位：秒。取值范围为5~20。
     * <p>
     * eg: 10
     */
    @JsonProperty(value = "CallTimeout")
    private Integer callTimeout;
}
