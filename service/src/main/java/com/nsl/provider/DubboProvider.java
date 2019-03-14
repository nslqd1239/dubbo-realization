package com.nsl.provider;

import com.nsl.protocol.DubboProtocol;
import com.nsl.protocol.Protocol;

/**
 * 服务提供者
 */
public class DubboProvider<T> {

    private Protocol protocol;

    // 服务监听的端口
    private int port = 3347;

    // 接口
    private Class<?> interfaceClass;

    // 接口名字
    private String interfaceName;

    // 接口的实现引用
    private T ref;

    // 导出的服务，接口作为key，实现作为value
//    private ConcurrentHashMap<String, Class<?>> exportedServices = new ConcurrentHashMap();

    /**
     * 发布服务，每次只发布一个服务
     */
    public synchronized void export() throws InterruptedException {
        //调用协议层，根据具体协议暴露服务
        if (protocol == null) {
            protocol = new DubboProtocol();
        }
        protocol.export(this.interfaceName, this.ref.getClass(), this.port);
        //发布的服务缓存起来
        //exportedServices.put(this.interfaceName,this.ref.getClass());
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterface(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        setInterface(interfaceClass == null ? null : interfaceClass.getName());
    }

    public void setInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }
}
