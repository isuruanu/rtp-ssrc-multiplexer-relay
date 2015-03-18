package hms.webrtc.rtp.ssrc.relay.utils;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by isuru on 3/17/15.
 */
public class SsrcInMemCacheResolver implements SsrcResolver {

    private final Cache<String, SDP> ssrcSDPMappings;

    public SsrcInMemCacheResolver() {
        ssrcSDPMappings = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public void save(String ssrc, SDP sdp) {
        ssrcSDPMappings.put(ssrc, sdp);
    }

    @Override
    public Optional<SDP> get(String ssrc) {
        return Optional.fromNullable(ssrcSDPMappings.getIfPresent(ssrc));
    }
}
