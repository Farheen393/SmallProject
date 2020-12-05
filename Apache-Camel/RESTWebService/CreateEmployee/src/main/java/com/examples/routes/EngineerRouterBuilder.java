package com.examples.routes;

import org.apache.camel.builder.RouteBuilder;

public class EngineerRouterBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception
    {
        from("direct:Engineer")
                .log("Request message is sent to Engineer queue")
                .to("jms:queue:{{SharedConnections.JMS.EngineerQueue}}?ExchangePattern=InOnly")
                .to("log:?level=INFO&showBody=true");
    }

}

