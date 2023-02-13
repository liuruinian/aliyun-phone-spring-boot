package io.github.liuruinian.phone.domain.axg;

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
public class UpdateAxgGroupRequest implements Serializable {

    /**
     * 号码池Key <br>
     * <p>
     * eg: FC2256****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * AXG中的G组ID，即GID。
     * <p>
     * eg: 1234
     */
    @JsonProperty(value = "GroupId")
    private Long groupId;

    /**
     * 对G号码组的操作类型.
     * <p>
     * 取值：<br>
     * 1.addNumbers：添加号码。<br>
     * 2.deleteNumbers：删除号码。<br>
     * 3.overwriteNumbers：全量替换号码。会将G号码组中原有的电话号码全部删除，并重新添加号码。
     * <p>
     * eg: addNumbers
     */
    @JsonProperty(value = "OperateType")
    private String operateType;

    /**
     * 向新建的G分组中添加的电话号码，多个号码之间用英文逗号（,）分隔，每次最多添加200个号码。
     * <p>
     * eg: 1390000****,1380000****
     */
    @JsonProperty(value = "Numbers")
    private String numbers;
}
