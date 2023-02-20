package io.github.liuruinian.phone.store.secret;

import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
public interface SecretRecordingStore {

    boolean addSecretEndReports(Collection<SecretRecording> recordings);
}
