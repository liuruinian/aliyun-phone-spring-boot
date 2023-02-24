package io.github.liuruinian.phone.store.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumberManagementReport implements Serializable {

    private Long id;

    private String secretNo;

    private String city;

    private String partnerKey;

    private String operatingTime;

    private Integer status;
}
