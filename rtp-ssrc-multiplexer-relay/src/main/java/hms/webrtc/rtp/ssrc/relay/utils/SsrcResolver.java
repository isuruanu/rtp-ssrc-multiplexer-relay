package hms.webrtc.rtp.ssrc.relay.utils;

import com.google.common.base.Optional;

/**
 * Created by isuru on 3/17/15.
 */
public interface SsrcResolver {

    void save(String ssrc, SDP sdp);

    Optional<SDP> get(String ssrc);
}
