package com.gouden.registry;

import com.gouden.transport.client.RpcClient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubscribeConfig {

    public static final SubscribeConfig DEFAULT_SUBSCRIBE_CONFIG = new SubscribeConfig();

    private Map<String, Set<Url>> interfaceNameMap = new HashMap<>();

    private Map<String, RpcClient> connectNameMap = new HashMap<>();

    private Map<String, Set<RpcClient>> connectionMap = new HashMap<>();

    public synchronized void subscribe(Url url) {
        Set<Url> set = interfaceNameMap.computeIfAbsent(url.getInterfaceName(), k -> new HashSet<>());
        if (set.contains(url)) {
            return;
        }

        set.add(url);

        RpcClient rpcClient = connectNameMap.get(url.getConnectName());
        if (rpcClient == null) {
            rpcClient = new RpcClient(url.getIp(), url.getPort());
            connectNameMap.put(url.getConnectName(), rpcClient);
        }
        connectionMap.computeIfAbsent(url.getInterfaceName(), k -> new HashSet<>()).add(rpcClient);
    }

    public synchronized void unSubscribeByConnectName(String connectName){

    }

    public Set<RpcClient> getConnection(String interfaceName){
        return connectionMap.get(interfaceName);
    }

}
