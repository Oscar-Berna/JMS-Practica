
package JMS;

import org.apache.activemq.broker.BrokerService;
public class Broker {
   

    public static void main(String[] args) throws Exception {
        BrokerService b = new BrokerService();
        b.setPersistent(false);
        b.setUseJmx(false);
        b.addConnector("tcp://0.0.0.0:61616");
        b.start();
        System.out.println("Broker listo en el puerto 61616");
        b.waitUntilStopped();
    }
}

