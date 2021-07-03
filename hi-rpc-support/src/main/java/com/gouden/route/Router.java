package com.gouden.route;

import com.gouden.registry.Url;
import com.gouden.transport.client.RpcClient;

public interface Router {

    Router DEFAULT_ROUTER = new DefaultRouter();

    RpcClient route(Url url);

}
