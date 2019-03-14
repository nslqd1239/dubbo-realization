package com.nsl.transporter.netty;

import com.nsl.support.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务消费者端的Netty消息处理器
 */
public class NettyClientServiceHandler extends SimpleChannelInboundHandler {

    private Response response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) {
        response = (Response) object;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
