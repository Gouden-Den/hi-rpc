package com.gouden.registry;

import com.gouden.route.Router;

public class RegisterConfigTest {

    public static void main(String[] args) {
        RegisterConfig registerConfig = RegisterConfig.DEFAULT_REGISTER_CONFIG;
        Url url = new Url();
        url.setIp("127.0.0.1");
        url.setPort((short) 8000);
        url.setInterfaceName(Router.class.getName());
        registerConfig.register(url, Router.DEFAULT_ROUTER);
        Object object = registerConfig.getService(Router.class.getName());
        System.out.println(object);
    }

}
