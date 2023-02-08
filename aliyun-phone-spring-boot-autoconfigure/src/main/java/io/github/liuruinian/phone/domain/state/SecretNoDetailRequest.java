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
public class SecretNoDetailRequest implements Serializable {

    /**
     * 隐私号码.
     * <p>
     * eg: 1390000****
     */
    @JsonProperty(value = "SecretNo")
    private String secretNo;

    /**
     * 号码池Key.
     * <p>
     * eg: FC2258****
     */
    @JsonProperty(value = "PoolKey")
    private String poolKey;
}
