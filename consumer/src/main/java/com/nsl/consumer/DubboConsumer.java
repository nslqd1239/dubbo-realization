package com.nsl.consumer;

import com.nsl.proxy.DubboConsumerProxy;

/**
 * 服务消费者端，获取代理
 */
public class DubboConsumer<T> {

    //接口名字
    private String interfaceName;

    //接口
    private Class<?> interfaceClass;

    //代理类
    private T ref;

    public T get(){
        //获取代理
        ref = (T) new DubboConsumerProxy(interfaceClass).getProxy();
        return ref;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setInterface(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        setInterface(interfaceClass == null ? null : interfaceClass.getName());
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
}
