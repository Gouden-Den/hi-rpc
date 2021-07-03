package com.gouden.packet;

import com.gouden.serialization.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    public final static PacketDecoder INSTANCE = new PacketDecoder();

    public PacketDecoder(){

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet packet = new Packet();
        packet.setPacketId(in.readLong());
        packet.setMagicNum(in.readInt());
        packet.setVersion(in.readByte());
        packet.setLang(in.readByte());
        packet.setLangVersion(in.readByte());
        packet.setSerializerAlgorithm(in.readByte());
        packet.setType(in.readByte());
        int length = in.readInt();
        byte [] bytes = new byte[length];
        in.readBytes(bytes);

        if(packet.getType() == 1) {
            packet.setObject(Serializer.getSerializer(packet.getSerializerAlgorithm()).deserialize(InvokeRequest.class, bytes));
        }else if(packet.getType() == 2) {
            packet.setObject(Serializer.getSerializer(packet.getSerializerAlgorithm()).deserialize(InvokeResponse.class, bytes));
        }
        out.add(packet);
    }
}
