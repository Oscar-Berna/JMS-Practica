
package JMS;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receptor {
    public static void main(String[] args) throws Exception {
        String tipo = args.length > 0 ? args[0] : "queue";    // en la Parte 2 cambia a "topic"
        String nombre = args.length > 1 ? args[1] : "COLA1";  // en la Parte 2 usa "NOTICIAS"
        String quien = args.length > 2 ? args[2] : "R" + ProcessHandle.current().pid();
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection con = cf.createConnection();
        con.start();
        Session s = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination d = tipo.equals("topic") ? s.createTopic(nombre) : s.createQueue(nombre);
        MessageConsumer c = s.createConsumer(d);
        System.out.println(quien + " esperando mensajes...");
        while (true) {
            Message m = c.receive();
            if (m instanceof TextMessage) {
                System.out.println(quien + " recibió: " + ((TextMessage) m).getText());
            }
        }
    }
    
}
