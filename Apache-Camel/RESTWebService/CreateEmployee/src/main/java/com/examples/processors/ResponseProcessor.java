package com.examples.processors;

import io.swagger.client.model.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception
    {
        Employee e = exchange.getIn().getBody(Employee.class);
        String empTitle=e.getTitle();
        String  responseMsg;

        if((empTitle.equalsIgnoreCase("Engineer")) || empTitle.equalsIgnoreCase("Accountant"))
        {
            responseMsg=empTitle + " : New Employee is added Successfully(not really)!";
        }
        else
            responseMsg=" Employee  with '"+ empTitle+"' title can not be created (not really)!";

        exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"text/plain");
        exchange.getIn().setBody(responseMsg);
    }
}

