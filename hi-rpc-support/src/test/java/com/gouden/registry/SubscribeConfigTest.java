package com.gouden.registry;

import com.gouden.route.Router;

public class SubscribeConfigTest {

    public static void main(String[] args) {
        SubscribeConfig subscribeConfig = SubscribeConfig.DEFAULT_SUBSCRIBE_CONFIG;
        Url url = new Url();
        url.setIp("127.0.0.1");
        url.setPort((short) 8000);
        url.setInterfaceName(Router.class.getName());
        url.setMethodName("route");
        subscribeConfig.subscribe(url);
    }

}
