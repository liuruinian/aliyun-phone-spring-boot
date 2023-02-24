package io.github.liuruinian.phone.store.report;

import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-24
 */
public interface SmartLogisticsReportStore {

    boolean addSmartLogisticsReports(Collection<SmartLogisticsReport> reports);
}
