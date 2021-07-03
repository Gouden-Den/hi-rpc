package com.gouden.invoke.client;

import com.gouden.packet.InvokeRequest;
import com.gouden.packet.InvokeResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxyFactory {

    private static final ClientInvoker clientInvoker = ClientInvoker.DEFAULT_CLIENT_INVOKER;

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                InvokeRequest invokeRequest = new InvokeRequest();
                invokeRequest.setInterfaceName(clazz.getName());
                invokeRequest.setMethodName(method.getName());
                invokeRequest.setArgTypes(method.getParameterTypes());
                invokeRequest.setArgs(args);
                InvokeResponse invokeResponse = clientInvoker.invoke(invokeRequest);
                return invokeResponse.getObject();
            }
        });
    }

}
