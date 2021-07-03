package com.gouden.invoke.server;

import com.gouden.packet.InvokeRequest;
import com.gouden.packet.InvokeResponse;
import com.gouden.invoke.Invoker;
import com.gouden.registry.RegisterConfig;
import com.gouden.registry.Url;

import java.lang.reflect.Method;

public class ServerInvoker implements Invoker {

    public static final ServerInvoker DEFAULT_SERVER_INVOKER = new ServerInvoker();

    private final RegisterConfig registerConfig = RegisterConfig.DEFAULT_REGISTER_CONFIG;

    @Override
    public InvokeResponse invoke(InvokeRequest request) throws Exception {
        Url url = new Url();
        url.setInterfaceName(request.getInterfaceName());
        url.setMethodName(request.getMethodName());

        Class<?> clazz = Class.forName(request.getInterfaceName());

        Object service = registerConfig.getService(request.getInterfaceName());

        Method method = clazz.getMethod(request.getMethodName(), request.getArgTypes());

        Object object = method.invoke(service, request.getArgs());

        InvokeResponse invokeResponse = new InvokeResponse();
        invokeResponse.setObject(object);
        return invokeResponse;
    }
}
