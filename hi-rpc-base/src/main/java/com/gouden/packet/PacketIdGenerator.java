package com.gouden.packet;

import java.util.concurrent.atomic.LongAdder;

public class PacketIdGenerator {

    private static LongAdder longAdder = new LongAdder();

    public static long getPacketId(){
        longAdder.increment();
        return longAdder.longValue();
    }

}
