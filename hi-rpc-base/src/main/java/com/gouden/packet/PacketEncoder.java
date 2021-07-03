package com.gouden.packet;

import com.gouden.serialization.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    public final static PacketEncoder INSTANCE = new PacketEncoder();

    public PacketEncoder(){

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        out.writeLong(packet.getPacketId());
        out.writeInt(packet.getMagicNum());
        out.writeByte(packet.getVersion());
        out.writeByte(packet.getLang());
        out.writeByte(packet.getLangVersion());
        out.writeByte(packet.getSerializerAlgorithm());

        out.writeByte(packet.getType());

        byte [] info = Serializer.getSerializer(packet.getSerializerAlgorithm()).serialize(packet.getObject());
        out.writeInt(info.length);
        out.writeBytes(info);
    }
}
