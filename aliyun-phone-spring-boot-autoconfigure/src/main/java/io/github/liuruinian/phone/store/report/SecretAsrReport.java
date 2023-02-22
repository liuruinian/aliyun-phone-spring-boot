package io.github.liuruinian.phone.store.report;

import com.aliyuncs.dyplsapi.model.v20170525.GetSecretAsrDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuruinian
 * @since 2023-02-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecretAsrReport implements Serializable {

    private Long id;

    private String callId;

    private Long bizDuration;

    private String partnerKey;

    private String secretNo;

    private String statusCode;

    private List<GetSecretAsrDetailResponse.Data.SecretAsrSentenceDTO> sentences;
}
