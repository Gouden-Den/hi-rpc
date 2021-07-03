package client;

import com.gouden.invoke.client.ClientProxyFactory;
import com.gouden.registry.SubscribeConfig;
import com.gouden.registry.Url;
import server.Test;

public class ClientTest {

    public static void main(String[] args) throws InterruptedException {
        SubscribeConfig subscribeConfig = SubscribeConfig.DEFAULT_SUBSCRIBE_CONFIG;
        Url url = new Url();
        url.setIp("127.0.0.1");
        url.setPort((short) 8000);
        url.setInterfaceName(Test.class.getName());
        url.setMethodName("test");
        subscribeConfig.subscribe(url);

        Thread.sleep(3000);

        Test test = ClientProxyFactory.getProxy(Test.class);

        System.out.println(test.test());

    }

}
