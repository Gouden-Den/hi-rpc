package transport.client;

import com.gouden.transport.client.RpcClient;

public class RpcClientTest {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient("127.0.0.1", 8000);
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
