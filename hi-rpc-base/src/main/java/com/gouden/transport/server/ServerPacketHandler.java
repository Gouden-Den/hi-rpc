package com.gouden.transport.server;

import com.gouden.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ServiceLoader;

public class ServerPacketHandler extends SimpleChannelInboundHandler<Packet> {

    private final ServiceLoader<ServerPacketProcessor> serviceLoader;

    public ServerPacketHandler(){
        this.serviceLoader = ServiceLoader.load(ServerPacketProcessor.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        Packet responsePacket = new Packet();
        responsePacket.setPacketId(packet.getPacketId());
        responsePacket.setType((byte) 2);

        for (ServerPacketProcessor serverPacketProcessor : serviceLoader) {
            serverPacketProcessor.process(packet, responsePacket);
        }

        ctx.channel().writeAndFlush(responsePacket);
    }
}
