
package JMS;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Emisor {
    public static void main(String[] args) throws Exception {
       String tipo = args.length > 0 ? args[0] : "queue";    // en la Parte 2 cambia a "topic"
       String nombre = args.length > 1 ? args[1] : "COLA1";  // en la Parte 2 usa "NOTICIAS"
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection con = cf.createConnection();
        try {
            Session s = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination d = tipo.equals("topic") ? s.createTopic(nombre) : s.createQueue(nombre);
            MessageProducer p = s.createProducer(d);
            for (int i = 1; i <= 6; i++) {
                p.send(s.createTextMessage("Mensaje " + i));
                System.out.println("Enviado: Mensaje " + i);
            }
        } finally {
            con.close();
        }
    }
    
}
