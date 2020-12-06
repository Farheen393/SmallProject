package com.examples.processors;

import io.swagger.client.model.Error;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class MyGenericErrorProcessor implements Processor
{
    public void process(Exchange exchange) throws Exception
    {
        Logger LOGGER = Logger.getLogger(Exception.class);
        Exception exp = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        LOGGER.error("Error Message :Exception occurred while processing invalid request!:" + exp);
        Error err=new Error();
        err.setErrorCode(400);
        err.setErrorMsg("Generic Exception occurred while processing invalid request!");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE,400);
        exchange.getIn().setBody(err);

    }
}
