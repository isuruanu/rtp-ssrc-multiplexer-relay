package hms.webrtc.rtp.ssrc.relay.multiplexer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.Hex;
import org.littleshoot.mina.common.IoSession;
import org.littleshoot.stun.stack.StunMessageDecoder;
import org.littleshoot.stun.stack.encoder.StunMessageEncoder;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by isuru on 3/17/15.
 */
public class SsrcRelayHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Random random = new Random();

    // Quotes from Mohandas K. Gandhi:
    private static final String[] quotes = {
            "Where there is love there is life.",
            "First they ignore you, then they laugh at you, then they fight you, then you win.",
            "Be the change you want to see in the world.",
            "The weak can never forgive. Forgiveness is the attribute of the strong.",
    };

    private static String nextQuote() {
        int quoteId;
        synchronized (random) {
            quoteId = random.nextInt(quotes.length);
        }
        return quotes[quoteId];
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // We don't close the channel because we can keep serving requests.
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuffer byteBuffer = datagramPacket.content().nioBuffer();


        if ("QOTM?".equals(datagramPacket.content().toString(CharsetUtil.UTF_8))) {
            channelHandlerContext.write(new DatagramPacket(
                    Unpooled.copiedBuffer("QOTM: " + nextQuote(), CharsetUtil.UTF_8), datagramPacket.sender()));
        }
    }
}
