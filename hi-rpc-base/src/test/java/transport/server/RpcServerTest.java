package transport.server;

import com.gouden.transport.server.RpcServer;

public class RpcServerTest {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer(8000);
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
