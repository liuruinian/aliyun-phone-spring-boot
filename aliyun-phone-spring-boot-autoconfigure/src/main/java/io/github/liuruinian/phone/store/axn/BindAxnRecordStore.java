package io.github.liuruinian.phone.store.axn;

import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public interface BindAxnRecordStore {

    boolean addBindAxnRecords(Collection<BindAxnRecord> records);
}
