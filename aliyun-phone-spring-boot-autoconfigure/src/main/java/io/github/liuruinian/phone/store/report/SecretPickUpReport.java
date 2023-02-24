package io.github.liuruinian.phone.store.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-24
 *
 * https://help.aliyun.com/document_detail/400454.html
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecretPickUpReport implements Serializable {

    private Long id;

    private String phoneNo;

    private String secretNo;

    private String peerNo;

    private Integer callType;

    private String callId;

    private String callTime;

    private String poolKey;

    private String ringTime;

    private String startTime;

    private Long subId;

    private String city;

    private String outId;
}
