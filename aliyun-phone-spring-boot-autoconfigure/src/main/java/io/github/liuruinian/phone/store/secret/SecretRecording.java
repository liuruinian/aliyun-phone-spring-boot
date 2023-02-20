package io.github.liuruinian.phone.store.secret;

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
public class SecretRecording implements Serializable {

    private Long id;

    private String poolKey;

    private String subId;

    private String callId;

    private String callTime;
}
