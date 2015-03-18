package hms.webrtc.rtp.ssrc.relay.utils;

/**
 * Created by isuru on 3/17/15.
 */
public class SDP {
    private final String hostname;
    private final int port;

    public SDP(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}
