package com.examples.processors;

import io.swagger.client.model.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PostProcessor implements Processor {
    public void process(Exchange exchange) throws Exception
    {
        Employee e = exchange.getIn().getBody(Employee.class);

        exchange.setProperty("Title",e.getTitle());
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"application/json");
        exchange.getIn().setBody(e);
}
}
