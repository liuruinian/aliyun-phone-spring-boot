package io.github.liuruinian.phone.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecretStartReport implements Serializable {

    private String poolKey;

    private Long subId;

    private String callId;

    private String phoneNo;

    private String secretNo;

    private String peerNo;

    private String calledDisplayNo;

    private Integer callType;

    private String callTime;

    private String outId;

    private String controlMsg;
}
