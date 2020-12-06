package com.examples.processors;

import io.swagger.client.model.Error;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class MyParseErrorProcessor implements Processor {

    public void process(Exchange exchange) throws Exception
    {
        Logger LOGGER = Logger.getLogger(Exception.class);
        Exception exp = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        LOGGER.error("Parse Exception Occurred while marshalling/unmarshalling xml/JSON:" + exp);
        Error err=new Error();
        err.setErrorCode(400);
        err.setErrorMsg("Invalid Req/Resp :Parse Exception Occurred while marshalling/unmarshalling JSON Data");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE,400);
        exchange.getIn().setBody(err);
    }
}
