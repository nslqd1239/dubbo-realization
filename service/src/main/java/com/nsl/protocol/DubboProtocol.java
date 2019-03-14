package com.nsl.protocol;

import com.nsl.transport.Server;
import com.nsl.transport.Transporters;
import com.nsl.transport.netty.ChannelHandler;
import com.nsl.transport.netty.NettyServiceHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dubbo协议
 */
public class DubboProtocol implements Protocol {

    /**
     * 导出的服务
     */
    private Map<String, Class<?>> exportedServices = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 打开的服务集合
     */
    private Map<String, Server> serverMap = new ConcurrentHashMap<>();

    @Override
    public void export(String interfaceName, Class<?> impl, Integer port) throws InterruptedException {
        // 每个将每个接口的名字作为一个key，key用来作为对应实现的唯一键
        exportedServices.put(interfaceName, impl);
        //打开服务其进行监听
        openServer(port);
        System.out.println("开启监听：" + exportedServices.size());
    }

    private void openServer(Integer port) throws InterruptedException {
        // 服务器监听地址
        String address = "127.0.0.1";
        // 每个地址只打开一次
        String key = address + ":" + port;
        Server server = serverMap.get(key);
        if (null == server) {
            // 创建一个server
            serverMap.put(key, this.createServer(address, port));
        }
    }

    // 创建server
    private Server createServer(String address, int port) throws InterruptedException {
        ChannelHandler handler = new NettyServiceHandler(exportedServices);
        Server server = Transporters.bind(address, port, handler);
        System.out.println("创建server：" + exportedServices.size());
        return server;
    }

}
