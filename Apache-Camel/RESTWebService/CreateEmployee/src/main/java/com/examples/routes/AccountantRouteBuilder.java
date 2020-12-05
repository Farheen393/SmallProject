package com.examples.routes;

import org.apache.camel.builder.RouteBuilder;

public class AccountantRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception
    {
        from("direct:Accountant")
                .log("Request message is sent to Accountant queue")
                .to("jms:queue:{{SharedConnections.JMS.AccountantQueue}}?ExchangePattern=InOnly")
                .to("log:?level=INFO&showBody=true");
    }

}
