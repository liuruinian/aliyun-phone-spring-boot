package io.github.liuruinian.phone.store.report;

import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-24
 */
public interface NumberManagementReportStore {

    boolean addNumberManagementReports(Collection<NumberManagementReport> reports);
}
