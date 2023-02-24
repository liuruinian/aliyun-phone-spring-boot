package io.github.liuruinian.phone.store.report;

import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public interface SecretRecordingStore {

    boolean addSecretRecordings(Collection<SecretRecording> recordings);
}
