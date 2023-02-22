package io.github.liuruinian.phone.store.report;

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
public class SecretEndReport implements Serializable {

    private String poolKey;

    private Long subId;

    private String recordUrl;

    private String ringRecordUrl;

    private String callId;

    private String phoneNo;

    private String secretNo;

    private String peerNo;

    private String calledDisplayNo;

    private Integer callType;

    private String callTime;

    private String startTime;

    private String callOutTime;

    private String ringTime;

    private String freeRingTime;

    private String releaseTime;

    private Integer smsNumber;

    private Integer releaseDir;

    private String outId;

    private Integer unconnectedCause;

    private Integer releaseCause;

    private String releaseCauseDescribe;

    private String controlMsg;
}
