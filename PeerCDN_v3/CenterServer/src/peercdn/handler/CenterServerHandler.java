package peercdn.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class CenterServerHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("Server OK, at port "+12000);
    }
    @Override
    public void messageReceived(ChannelHandlerContext ctx,MessageEvent e){
        ChannelBuffer buf = (ChannelBuffer)e.getMessage();
        while(buf.readable()){
            System.out.println((char)buf.readByte());
        }

//        Channel ch = e.getChannel();
//        ch.write(e.getMessage());
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,ExceptionEvent e){
        e.getCause().printStackTrace();
        Channel ch = e.getChannel();
        ch.close();
    }

}
