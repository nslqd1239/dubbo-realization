package com.nsl.transporter;

import com.nsl.support.Request;
import com.nsl.transporter.netty.NettyClient;
import com.nsl.transporter.netty.NettyClientServiceHandler;

/**
 * 传输层
 */
public class Transporters {

    public static Object connectAndExecute(Request request) throws InterruptedException {

        //处理器，用来处理返回的消息
        NettyClientServiceHandler handler = new NettyClientServiceHandler();

        //使用nettyclient来进行连接服务提供者和执行请求
        new NettyClient().connectAndExecute(request, handler);

        //从处理器中获取返回的消息
        return handler.getResponse().getResult();
    }
}
