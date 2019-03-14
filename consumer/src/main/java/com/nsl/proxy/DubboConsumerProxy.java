package com.nsl.proxy;

import com.nsl.support.Request;
import com.nsl.transporter.Transporters;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 服务消费者端的代理，使用JDK动态代理
 */
public class DubboConsumerProxy implements InvocationHandler {

    private Class<?> interfaces;

    public DubboConsumerProxy(Class<?> interfaces) {
        this.interfaces = interfaces;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[]{interfaces}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setInterfaceName(interfaces.getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setArgs(args);

        //远程调用服务提供者
        return Transporters.connectAndExecute(request);
    }
}
