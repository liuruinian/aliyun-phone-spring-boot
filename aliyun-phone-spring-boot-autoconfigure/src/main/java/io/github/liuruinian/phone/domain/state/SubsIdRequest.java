package io.github.liuruinian.phone.domain.state;

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
public class SubsIdRequest implements Serializable {

    /**
     * 号码池Key.
     * <p>
     * eg: FC123456
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;

    /**
     * 绑定关系中的隐私号码，即X号码.
     * <p>
     * eg: 1390000****
     */
    @JsonProperty(value = "PhoneNoX")
    private String phoneNoX;
}
