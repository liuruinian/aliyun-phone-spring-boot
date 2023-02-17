package io.github.liuruinian.phone.store.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BindDetail implements Serializable {

    private Long id;

    private String requestId;

    private String code;

    private String message;

    private String poolKey;

    private String subsId;

    private Long status;

    private String extension;

    private String phoneNoA;

    private String phoneNoB;

    private String phoneNoX;

    private Long groupId;

    private String gmtCreate;

    private String expireDate;

    private Boolean needRecord;

    private String callRestrict;

    private Boolean asrStatus;

    private String asrModelId;
}
