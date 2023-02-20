package io.github.liuruinian.phone.store.record;

import java.util.Collection;
import java.util.List;

/**
 * @author liuruinian
 * @since 2023-02-20
 */
public interface SecretRecordUrlStore {

    boolean addSecretRecordUrls(Collection<SecretRecordUrl> records);

    List<SecretRecordUrl> querySecretRecordUrl(String callId);
}
