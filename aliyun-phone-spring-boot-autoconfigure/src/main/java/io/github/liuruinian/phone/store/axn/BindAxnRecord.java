package io.github.liuruinian.phone.store.axn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author liuruinian
 * @since 2023-02-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BindAxnRecord implements Serializable {

    private Long id;

    private String code;

    private String message;

    private String requestId;

    private String extension;

    private String secretNo;

    private String subsId;

    private String poolKey;

    private String createTime;
}
