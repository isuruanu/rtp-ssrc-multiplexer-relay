package hms.webrtc.rtp.ssrc.relay.multiplexer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by isuru on 3/17/15.
 */
public class RtpcSsrcRelay {
    private static final int PORT = Integer.parseInt(System.getProperty("port", "40001"));

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new SsrcRelayHandler());

            b.bind(PORT).sync().channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
    }
}
