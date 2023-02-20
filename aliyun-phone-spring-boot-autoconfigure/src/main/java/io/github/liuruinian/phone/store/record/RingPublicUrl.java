package io.github.liuruinian.phone.store.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RingPublicUrl implements Serializable {

    private Long id;

    private String callId;

    private String callTime;

    private String ringPublicUrl;

    private String PhonePublicUrl;

    private String createTime;

    private String updateTime;
}
