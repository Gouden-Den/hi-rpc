package com.gouden.route;

import com.gouden.registry.SubscribeConfig;
import com.gouden.registry.Url;
import com.gouden.transport.client.RpcClient;

import java.util.Random;
import java.util.Set;

public class DefaultRouter implements Router {

    private final SubscribeConfig subscribeConfig = SubscribeConfig.DEFAULT_SUBSCRIBE_CONFIG;

    @Override
    public RpcClient route(Url url) {
        Set<RpcClient> set = subscribeConfig.getConnection(url.getInterfaceName());
        Random random = new Random(37);
        return set.toArray(new RpcClient[]{})[random.nextInt(set.size())];
    }
}
