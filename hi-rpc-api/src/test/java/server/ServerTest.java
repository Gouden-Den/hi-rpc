package server;

import com.gouden.registry.RegisterConfig;
import com.gouden.registry.Url;
import com.gouden.transport.server.RpcServer;

public class ServerTest {

    public static void main(String[] args) throws InterruptedException {
        RpcServer rpcServer = new RpcServer(8000);
        RegisterConfig registerConfig = RegisterConfig.DEFAULT_REGISTER_CONFIG;
        Url url = new Url();
        url.setIp("127.0.0.1");
        url.setPort((short) 8000);
        url.setInterfaceName(Test.class.getName());
        registerConfig.register(url, new TestImpl());
        Thread.sleep(1000000000);
    }

}
