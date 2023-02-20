package io.github.liuruinian.phone.store.record;

import java.util.Collection;
import java.util.List;

/**
 * @author liuruinian
 * @since 2023-02-20
 */
public interface RingPublicUrlStore {

    boolean addRingPublicUrls(Collection<RingPublicUrl> records);

    List<RingPublicUrl> queryRingPublicUrl(String callId);
}
