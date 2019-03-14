package com.nsl.transport;

import com.nsl.transport.netty.ChannelHandler;
import com.nsl.transport.netty.NettyServer;

/**
 * 传输层方便的工具类
 */
public class Transporters {

    // 绑定，监听
    public static Server bind(String address, int port, ChannelHandler handler) throws InterruptedException {
        return new NettyServer(address, port, handler);
    }
}
