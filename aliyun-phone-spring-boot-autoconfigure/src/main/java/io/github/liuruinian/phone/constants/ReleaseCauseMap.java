package io.github.liuruinian.phone.constants;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public class ReleaseCauseMap implements Serializable {

    private static final Map<Integer, String> map = new ConcurrentHashMap<>();

    static {
        map.put(1, "未分配的号码");
        map.put(2, "无路由到指定的转接网");
        map.put(3, "无路由到目的地");
        map.put(4, "发送专用信息音");
        map.put(16, "正常的呼叫拆线");
        map.put(17, "用户忙");
        map.put(18, "用户未响应");
        map.put(19, "用户未应答");
        map.put(20, "用户缺席");
        map.put(21, "呼叫拒收");
        map.put(22, "号码改变");
        map.put(27, "目的地不可达");
        map.put(28, "无效的号码格式（地址不全）");
        map.put(29, "性能拒绝");
        map.put(31, "正常—未指定");
        map.put(34, "无电路或通路可用");
        map.put(42, "交换设备拥塞");
        map.put(50, "所请求的性能未预定");
        map.put(53, "CUG中限制去呼叫");
        map.put(55, "CUG中限制来呼叫");
        map.put(57, "承载能力无权");
        map.put(58, "承载能力目前不可用");
        map.put(65, "承载能力未实现");
        map.put(69, "所请求的性能未实现");
        map.put(87, "被叫用户不是CUG的成员");
        map.put(88, "不兼容的目的地");
        map.put(90, "不存在的CUG");
        map.put(91, "无效的转接网选择");
        map.put(95, "无效的消息，未指定");
        map.put(97, "消息类型不存在或未实现");
        map.put(99, "参数不存在或未实现");
        map.put(102, "定时器终了时恢复");
        map.put(103, "参数不存在或未实现—传递");
        map.put(110, "消息带有未被识别的参数—舍弃");
        map.put(111, "协议错误，未指定");
        map.put(127, "互通，未指定");
    }

    public static String obtainCauseDescribe(Integer releaseCause) {
        return map.get(releaseCause);
    }
}
