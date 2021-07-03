package com.gouden.registry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegisterConfig {

    public static final RegisterConfig DEFAULT_REGISTER_CONFIG = new RegisterConfig();

    private Map<String, Set<Url>> interfaceNameMap = new HashMap<>();

    private Map<String, Object> serviceMap = new HashMap<>();

    public synchronized void register(Url url, Object object){
        interfaceNameMap.computeIfAbsent(url.getInterfaceName(), k -> new HashSet<>()).add(url);
        serviceMap.put(url.getInterfaceName(), object);
    }

    public synchronized void unRegister(Url url){
        interfaceNameMap.computeIfAbsent(url.getInterfaceName(), k -> new HashSet<>()).remove(url);
        serviceMap.remove(url.getInterfaceName());
    }

    public Object getService(String interfaceName){
        return serviceMap.get(interfaceName);
    }

}
