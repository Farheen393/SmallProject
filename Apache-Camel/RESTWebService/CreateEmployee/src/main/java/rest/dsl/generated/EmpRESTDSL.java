package rest.dsl.generated;

import javax.annotation.Generated;
import com.examples.processors.MyParseErrorProcessor;
import com.examples.processors.PostProcessor;
import io.swagger.client.model.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * Generated from OpenApi specification by Camel REST DSL generator.
 */
@Generated("org.apache.camel.generator.openapi.PathGenerator")
public final class EmpRESTDSL extends RouteBuilder {
    /**
     * Defines Apache Camel routes using REST DSL fluent API.
     */
    public void configure() {

        // JSON Data format For Error
        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Error.class);

        onException(Exception.class)
                .handled(true)
                .process(new com.example.MyGenericErrorProcessor())
                .marshal(jsonDataFormat)
                .setBody(simple("${body}"))
                .end();

        //Expose Rest Service
        restConfiguration().component("jetty").host(getContext().resolvePropertyPlaceholders("{{SharedConnections.REST.host}}")).port(getContext().resolvePropertyPlaceholders("{{SharedConnections.REST.port}}")).bindingMode(RestBindingMode.json);

        rest("/e1")
            .post("/employee")
                .consumes("application/json")
                .produces("application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                .endParam()
                .type(Employee.class)
                .to("direct:empPost");

        from("direct:empPost").routeId("EmployeePostRoute")
                .setHeader("Content-Type", constant("application/json"))
                .setBody(simple("${body}"))
                .doTry()
                  .choice()
                    .when(exchangeProperty("Title").isEqualToIgnoreCase("Engineer"))
                     .wireTap("direct:Engineer")
                     .end()
                    .when(exchangeProperty("Title").isEqualToIgnoreCase("Accountant"))
                      .wireTap("direct:Accountant")
                      .end()
                    .otherwise()
                       .log("Log for other Employees")
                .to("direct:setPostBody")
                .endDoTry()

                .doCatch(Exception.class)
                    .process(new MyParseErrorProcessor())
                    .to("log:?level=INFO&showBody=true")
                    .marshal(jsonDataFormat)
                .to("direct:setPostBody");

        from("direct:setPostBody")
                .setBody(simple("${body}"))
                .to("log:?level=INFO&showBody=true");
    }
}
