package com.gouden.invoke.client;

import com.gouden.packet.InvokeRequest;
import com.gouden.packet.InvokeResponse;
import com.gouden.invoke.Invoker;
import com.gouden.packet.Packet;
import com.gouden.packet.PacketIdGenerator;
import com.gouden.registry.Url;
import com.gouden.route.Router;
import com.gouden.transport.client.RpcClient;

public class ClientInvoker implements Invoker {

    public static final ClientInvoker DEFAULT_CLIENT_INVOKER = new ClientInvoker();

    private final Router router = Router.DEFAULT_ROUTER;

    @Override
    public InvokeResponse invoke(InvokeRequest request) {
        Url url = new Url();
        url.setInterfaceName(request.getInterfaceName());
        url.setMethodName(request.getInterfaceName());
        RpcClient rpcClient = router.route(url);

        Packet packet = new Packet();
        packet.setPacketId(PacketIdGenerator.getPacketId());
        packet.setType((byte) 1);
        packet.setObject(request);
        Packet responsePacket = rpcClient.syncInvoke(packet);

        if(responsePacket == null){
            throw new RuntimeException("执行超时");
        }

        return (InvokeResponse) responsePacket.getObject();
    }
}
