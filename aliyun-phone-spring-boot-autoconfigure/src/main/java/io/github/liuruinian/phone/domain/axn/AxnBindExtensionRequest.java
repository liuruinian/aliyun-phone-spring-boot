package io.github.liuruinian.phone.domain.axn;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AxnBindExtensionRequest implements Serializable {

    /**
     * 号码池Key.
     * <p>
     * eg: FC123456
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * AXN中的A号码。
     * <p>
     * A号码可设置为手机号码或固定电话，固定电话需要加区号，区号和号码中间不需要加连字符.
     * <p>
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoA")
    private String phoneNoA;

    /**
     * X号码的分机号码，1~3位.
     * <p>
     * 如果指定分机号，必须同时指定X号码，即Extension、PhoneNoX两个字段必须同时被指定.
     * <p>
     * eg: 130
     */
    @JsonProperty(value = "Extension")
    private String extension;

    /**
     * AXN中的B号码，A号码拨打X号码时会转接到B号码，可通过接口UpdateSubscription更新B号码。
     * <p>
     * B号码可设置为手机号码或固定电话，固定电话需要加区号，区号和号码中间不需要加连字符.
     * <p>
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoB")
    private String phoneNoB;

    /**
     * AXN中的X号码。未指定X号码时，将根据参数ExpectCity从指定号码池中随机指定一个号码作为X号码.
     * <p>
     * X号码是您绑定号码前在控制台或通过接口BuySecretNo购买的电话号码，用于转接电话.
     * <p>
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoX")
    private String phoneNoX;

    /**
     * 绑定关系的过期时间，时间单位精确到秒.
     * <p>
     * 必须晚于当前时间1分钟以上.
     * <p>
     * eg: 2019-09-05 12:00:00
     */
    @JsonProperty(value = "Expiration")
    private String expiration;

    /**
     * 指定城市进行X号码的选号.
     * <p>
     * 1.如果当前号池中没有该城市的可用号码，或未指定此参数，将从当前号码池中随机分配一个其他城市的号码作为X号码.
     * 2.如果配置了严格模式，则不存在符合条件的号码时会提示分配错误.
     * <p>
     * eg: 北京
     */
    @JsonProperty(value = "ExpectCity")
    private String expectCity;

    /**
     * 是否需要针对该绑定关系产生的所有通话进行通话录音.
     * <p>
     * true：录音.
     * <p>
     * false：不录音（默认值）.
     * <p>
     * eg: true
     */
    @JsonProperty(value = "IsRecordingEnabled")
    private Boolean isRecordingEnabled;

    /**
     * 外部业务扩展字段，通话记录回执消息中会回传此参数.
     * <p>
     * eg: abcdef
     */
    @JsonProperty(value = "OutId")
    private String outId;

    /**
     * 外部业务ID.
     * <p>
     * eg: abcdef
     */
    @JsonProperty(value = "OutOrderId")
    private String outOrderId;

    /**
     * 重置绑定关系中的号码显示逻辑。固定取值：1
     * <p>
     * 主被叫显示中间号码X, 由于运营商监管限制，呼叫时显示真实号码的设置不生效.
     */
    @JsonProperty(value = "CallDisplayType")
    private Integer callDisplayType = 1;

    /**
     * 设置AXN分机号绑定级别企业彩铃放音，支持针对该绑定关系中设置，内容包括如下：
     * <p>
     * 1.AXN分机号中，A号码打X的铃音设置（有回拨号码）：AXNRing_AB
     * <p>
     * 2.AXN分机号中，A号码打X的铃音设置（无回拨号码）：AXNRing_A
     * <p>
     * 3.AXN分机号中，N侧号码打X的铃音设置：AXNRing_N
     * <p>
     * 企业彩铃优先使用绑定级别设置的彩铃，如果未设置或设置未生效，则会使用号码池级别的彩铃音。
     * <p>
     * eg: {"AXNRing_N":"100000001","AXNRing_A":"100000001"}
     */
    @JsonProperty(value = "RingConfig")
    private String ringConfig;

    /**
     * ASR状态
     * <p>
     * false：关闭（默认值）
     * <p>
     * true：开启
     */
    @JsonProperty(value = "ASRStatus")
    private Boolean asrStatus = false;

    /**
     * ASR模型ID。
     * <p>
     * eg: 980abddb908f48e8b987cb2cd303****
     */
    @JsonProperty(value = "ASRModelId")
    private String asrModelId;

    /**
     * 单通呼叫限制的状态。
     * <p>
     * CONTROL_AX_DISABLE：A号码无法呼叫X号码。
     * <p>
     * CONTROL_BX_DISABLE：B号码无法呼叫X号码。
     * <p>
     * eg: CONTROL_AX_DISABLE
     */
    @JsonProperty(value = "CallRestrict")
    private String callRestrict;
}
