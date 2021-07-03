package com.gouden.transport.server;

import com.gouden.packet.Packet;

public interface ServerPacketProcessor {

    void process(Packet requestPacket, Packet responsePacket);

}
