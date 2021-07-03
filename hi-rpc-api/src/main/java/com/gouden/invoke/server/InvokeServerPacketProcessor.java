package com.gouden.invoke.server;

import com.gouden.packet.InvokeRequest;
import com.gouden.packet.InvokeResponse;
import com.gouden.invoke.Invoker;
import com.gouden.packet.Packet;
import com.gouden.transport.server.ServerPacketProcessor;

public class InvokeServerPacketProcessor implements ServerPacketProcessor {

    private final Invoker serverInvoker = ServerInvoker.DEFAULT_SERVER_INVOKER;

    @Override
    public void process(Packet requestPacket, Packet responsePacket) {
        try {
            InvokeResponse response = serverInvoker.invoke((InvokeRequest) requestPacket.getObject());
            responsePacket.setObject(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
