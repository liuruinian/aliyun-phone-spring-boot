package io.github.liuruinian.phone.store.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-22
 *
 * https://help.aliyun.com/document_detail/354287.html
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecretExceptionPhoneReport implements Serializable {

    private Long id;

    private String secretNo;

    private String partnerKey;

    private Integer noStatus;

    private Long timestamp;
}
