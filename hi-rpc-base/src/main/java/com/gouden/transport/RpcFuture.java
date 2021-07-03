package com.gouden.transport;

import com.gouden.packet.Packet;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RpcFuture {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private Packet response;

    public Packet getResponse(){
        return response;
    }

    public Packet waitResponse(long timeoutMillis) throws InterruptedException {
        countDownLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
        return response;
    }

    public void putResponse(Packet response){
        this.response = response;
        countDownLatch.countDown();
    }

}
