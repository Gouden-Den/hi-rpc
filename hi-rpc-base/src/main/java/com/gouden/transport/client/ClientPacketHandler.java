package com.gouden.transport.client;

import com.gouden.packet.Packet;
import com.gouden.transport.RpcFuture;
import com.gouden.transport.RpcRemoting;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientPacketHandler extends SimpleChannelInboundHandler<Packet> {

    private RpcRemoting rpcRemoting;

    public ClientPacketHandler(RpcRemoting rpcRemoting) {
        if (rpcRemoting == null) {
            throw new IllegalArgumentException("rpcRemoting不能为空");
        }
        this.rpcRemoting = rpcRemoting;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        RpcFuture rpcFuture = rpcRemoting.getRpcFuture(packet.getPacketId());
        if (rpcFuture == null) {
            return;
        }
        rpcFuture.putResponse(packet);
    }
}
