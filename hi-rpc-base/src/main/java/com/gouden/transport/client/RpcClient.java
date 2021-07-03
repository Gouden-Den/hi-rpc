package com.gouden.transport.client;

import com.gouden.packet.Packet;
import com.gouden.packet.PacketDecoder;
import com.gouden.packet.PacketEncoder;
import com.gouden.transport.RpcRemoting;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class RpcClient {

    private Channel channel;

    private RpcRemoting rpcRemoting = new RpcRemoting();

    public RpcClient(String ip, int port){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecoder()).addLast(new ClientPacketHandler(RpcClient.this.rpcRemoting)).addLast(new PacketEncoder());
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(ip, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接成功!");
                    //接收请求，调用服务端，请求发送之后即处理下一个请求，或者是丢到线程池统一处理
                }else{
                    System.out.println("连接失败!");
                }
            }
        });
        this.channel = channelFuture.channel();
        this.rpcRemoting.setChannel(channel);
    }

    public Packet syncInvoke(Packet packet){
         return rpcRemoting.syncInvoke(packet);
    }

}
