import com.examples.routes.AccountantRouteBuilder;
import com.examples.routes.EngineerRouterBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;
import rest.dsl.generated.EmpRESTDSL;
import com.examples.Connections.*;

public class RestDemoWithCamel {

    public static void main(String args[]) throws Exception
    {

        Logger LOGGER = Logger.getLogger(Exception.class);
        try {
            CamelContext context = new DefaultCamelContext();

            // encrypt username and password details
            JasyptPropertiesParser jasypt=new JasyptPropertiesParser();
            jasypt.setPassword("secret");

            // add properties file location and set to context
            PropertiesComponent pc = new PropertiesComponent();
            pc.addLocation("classpath:application.properties");

            //set jasypt to camel context
            pc.setPropertiesParser(jasypt);
            context.setPropertiesComponent(pc);

            Connections con = new Connections();
            JmsComponent emscomponent = con.JMSconnection(context.resolvePropertyPlaceholders("{{SharedConnections.JMS.url}}"),context.resolvePropertyPlaceholders("{{SharedConnections.JMS.username}}"),context.resolvePropertyPlaceholders("{{SharedConnections.JMS.password}}"));

            // add JMS camel component
            context.addComponent("jms", emscomponent);

            // add all routes to camel context
            context.addRoutes(new EmpRESTDSL());
            context.addRoutes(new AccountantRouteBuilder());
            context.addRoutes(new EngineerRouterBuilder());

            context.start();
            Thread.sleep(50000);
            context.stop();
        }
        catch(Exception ex)
        {
           LOGGER.error("Exception in Main Class " +ex);
        }
    }
    }
