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
 * https://help.aliyun.com/document_detail/375220.html
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmartLogisticsReport implements Serializable {

    private Long id;

    private String cpCode;

    private String logisticsStatus;

    private String mailNo;

    private String logisticsStatusDesc;

    private String lastLogisticDetail;

    private String logisticsGmtModified;

    private String packageDyn;

    private String aliyunPrice;

    private String city;

    private String bizKey;

    private String outerOrderCode;
}
