package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleRoute extends RouteBuilder {

    private static final String PACKAGE_NAME = SingleRoute.class.getPackage().getName();
    public static final String ENDPOINT_URI =  String.format("direct:ROUTE", PACKAGE_NAME);
    private Translator translator;

    @Autowired
    public SingleRoute(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void configure() throws Exception {
            from(ENDPOINT_URI).bean(translator, "translate");
    }

}

