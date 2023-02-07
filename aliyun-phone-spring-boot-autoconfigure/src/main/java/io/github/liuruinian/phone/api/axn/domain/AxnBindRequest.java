package io.github.liuruinian.phone.api.axn.domain;

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
public class AxnBindRequest implements Serializable {

    /**
     * 号码池Key
     * eg: FC2256****
     */
    private String poolKey;

    /**
     * AXN中的A号码。 A号码可设置为手机号码或固定电话，固定电话需要加区号，区号和号码中间不需要加连字符.
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoA")
    private String phoneNoA;

    /**
     * AXN中的B号码，A号码拨打X号码时会转接到B号码。
     * B号码可设置为手机号码或固定电话，固定电话需要加区号，区号和号码中间不需要加连字符。
     * <p>
     * eg: 138****0000
     */
    @JsonProperty(value = "PhoneNoB")
    private String phoneNoB;

    /**
     * AXN中的X号码。 X号码是您绑定号码前登录号码隐私保护控制台或通过BuySecretNo接口购买的电话号码，用于转接电话。
     * 如果未指定X号码时，将根据参数ExpectCity从指定号码池中随机指定一个号码作为X号码。
     * <p>
     * eg: 139****0000
     */
    @JsonProperty(value = "PhoneNoX")
    private String phoneNoX;

    /**
     * 指定城市进行X号码的选号。
     * 1.如果当前号码池中没有该城市的可用号码，或未指定此参数，将从当前号码池中随机分配一个其他城市的号码作为X号码.
     * 2.如果X号码分配模式配置为严格匹配模式，当符合条件的号码不存在时，系统会提示分配错误。
     * <p>
     * eg: 上海
     */
    @JsonProperty(value = "ExpectCity")
    private String expectCity;

    /**
     * 绑定关系的过期时间, 必须晚于当前时间1分钟以上。
     * 格式：yyyy-MM-dd HH:mm:ss
     * <p>
     * eg: 2021-09-05 12:00:00
     */
    @JsonProperty(value = "Expiration")
    private String expiration;

    /**
     * 是否需要针对该绑定关系产生的所有通话录制通话录音
     * true: 录音
     * false: 不录音（默认值）
     */
    @JsonProperty(value = "IsRecordingEnabled")
    private Boolean isRecordingEnabled = false;

    /**
     * 号码类型. 适用于原阿里云大客户，阿里云用户可忽略。
     * eg: AXB_170
     */
    @JsonProperty(value = "NoType")
    private String noType;

    /**
     * 外部业务扩展字段，通话记录回执消息中会回传此参数。
     * eg: abc123456
     */
    @JsonProperty(value = "OutId")
    private String outId;

    /**
     * 外部业务ID。
     * eg: 34553330****
     */
    @JsonProperty(value = "OutOrderId")
    private String outOrderId;

    /**
     * 重置绑定关系中的号码显示逻辑。固定取值：1（主被叫显示中间号码X）。
     * 由于运营商监管限制，呼叫时显示真实号码的设置不生效。
     */
    @JsonProperty(value = "CallDisplayType")
    private Integer callDisplayType = 1;

    /**
     * 设置AXN分机号绑定关系中的企业彩铃放音编码，内容如下：
     * 1.AXN分机号中，A号码打X的铃音设置（有回拨号码）：AXNRing_AB
     * 2.AXN分机号中，A号码打X的铃音设置（无回拨号码）：AXNRing_A
     * 3.AXN分机号中，N侧号码打X的铃音设置：AXNRing_N
     * 4.企业彩铃编码：企业彩铃编码可以在管理控制台中查询，请登录号码隐私保护控制台，在号码池管理企业彩铃管理进行查看，支持上传、删除等操作。
     * <p>
     * tips: 企业彩铃优先使用绑定级别设置的彩铃，如果未设置或设置未生效，则会使用号码池级别的彩铃音。
     * <p>
     * eg: {"AXNRing_N":"100000001","AXNRing_A":"100000001"}
     */
    @JsonProperty(value = "RingConfig")
    private String ringConfig;

    /**
     * ASR状态
     * false：关闭（默认值）
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
     * 顺振时长。单位：秒。
     * <p>
     * eg: 10
     */
    @JsonProperty(value = "CallTimeout")
    private Integer callTimeout;

    /**
     * 单通呼叫限制的状态。
     * CONTROL_AX_DISABLE：A号码无法呼叫X号码。
     * CONTROL_BX_DISABLE：B号码无法呼叫X号码。
     * <p>
     * eg: CONTROL_AX_DISABLE
     */
    @JsonProperty(value = "CallRestrict")
    private String callRestrict;
}
