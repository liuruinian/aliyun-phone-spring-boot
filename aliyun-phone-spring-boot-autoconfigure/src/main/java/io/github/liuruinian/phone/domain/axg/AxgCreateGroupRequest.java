package io.github.liuruinian.phone.domain.axg;

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
public class AxgCreateGroupRequest implements Serializable {

    /**
     * 号码池Key <br>
     * <p>
     * eg: FC2256****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * G号码组名称。<br>
     * 如果未指定G号码组名称，将以G号码组的ID作为名称。<br>
     * 取值范围为1~128个字符，支持中文和英文。
     * <p>
     * eg: 测试分组
     */
    @JsonProperty(value = "Name")
    private String name;

    /**
     * G号码组的备注信息。<br>
     * 取值范围为0~256个字符，支持中文和英文.
     * <p>
     * eg: 测试用分组
     */
    @JsonProperty(value = "Remark")
    private String remark;

    /**
     * 向新建的G分组中添加的电话号码. <br>
     * 多个号码之间用英文逗号（,）分隔，创建时最多添加200个号码。
     * <p>
     * eg: 1390000****,1380000****
     */
    @JsonProperty(value = "Numbers")
    private String numbers;
}
