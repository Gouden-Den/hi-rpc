package com.gouden.invoke;

import com.gouden.packet.InvokeRequest;
import com.gouden.packet.InvokeResponse;

public interface Invoker {

    InvokeResponse invoke(InvokeRequest request) throws Exception;

}
