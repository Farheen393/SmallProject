package com.examples.processors;

import io.swagger.client.model.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PostProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {

       // String receivedBody=(String) exchange.getIn().getBody();
        //System.out.println("-------Post Method Implementation-------" + receivedBody);

        Employee e = exchange.getIn().getBody(Employee.class);
        String empTitle=e.getTitle();
        String  responseMsg;

        if((empTitle.equalsIgnoreCase("Engineer")) || empTitle.equalsIgnoreCase("Accountant"))
        {
            responseMsg=empTitle + " : New Employee is added Successfully(not really)!";
        }
        else
            responseMsg=" Employee  with '"+ empTitle+"' title can not be created (not really)!";

        exchange.setProperty("Title",empTitle);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"application/json");
        exchange.getIn().setBody(responseMsg);
}
}
