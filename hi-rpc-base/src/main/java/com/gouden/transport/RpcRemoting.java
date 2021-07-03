package com.gouden.transport;

import com.gouden.packet.Packet;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcRemoting {

    private Channel channel;

    private long defaultTimeoutMillis = 3000;

    private Map<Long, RpcFuture> rpcFutureMap = new ConcurrentHashMap<>();

    public Packet syncInvoke(Packet packet) {
        RpcFuture rpcFuture = new RpcFuture();
        try {
            rpcFutureMap.put(packet.getPacketId(), rpcFuture);
            channel.writeAndFlush(packet).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("发送成功!");
                    } else {
                        System.out.println("发送失败!");
                    }
                }
            });
            Packet response = null;
            try {
                response = rpcFuture.waitResponse(defaultTimeoutMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (response == null) {
                throw new RuntimeException("调用超时!");
            }
            return response;
        } finally {
            rpcFutureMap.remove(packet.getPacketId());
        }
    }

    public RpcFuture getRpcFuture(Long packetId){
        return rpcFutureMap.get(packetId);
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
