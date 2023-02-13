package io.github.liuruinian.phone.store;

import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public interface SecretStartReportStore {

    boolean addSecretStartReports(Collection<SecretStartReport> reports);
}
