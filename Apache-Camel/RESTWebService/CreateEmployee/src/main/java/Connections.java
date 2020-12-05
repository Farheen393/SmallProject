import com.tibco.tibjms.TibjmsConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.log4j.Logger;

import javax.jms.Connection;
import javax.jms.JMSException;

public class Connections {

    public JmsComponent JMSconnection(String emsurl, String username, String password) throws JMSException {
        Logger LOGGER = Logger.getLogger(Connections.class);
        TibjmsConnectionFactory cf = new TibjmsConnectionFactory(emsurl);
        cf.setUserName(username);
        cf.setUserPassword(password);
        Connection connection = cf.createConnection();
        connection.start();
         LOGGER.info("TIBCO JMS Connection Successful!!!");
        return JmsComponent.jmsComponent(cf);
    }

 /*
    public static void main(String args[]) throws Exception

    {
        Connections con=new Connections();
        JmsComponent emscomponent = con.JMSconnection("tcp://localhost:7222","admin","admin");

    }
 */

}