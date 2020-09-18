package com.example.demo;

import com.example.demo.Greetings.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleRoute extends RouteBuilder {

    private static final String PACKAGE_NAME = SingleRoute.class.getPackage().getName();
    public static final String ENDPOINT_URI =  String.format("direct:ROUTE", PACKAGE_NAME);
    private StandardGreeting standardGreeting;
    private BasicGreeting basicGreeting;
    private PremiumGreeting premiumGreeting;
    private Translator translator;
    private StandardUser standardUser;
    private PremiumUser premiumUser;

    @Autowired
    public SingleRoute(PremiumGreeting premiumGreeting, StandardGreeting standardGreeting, BasicGreeting basicGreeting, StandardUser standardUser, PremiumUser premiumUser) {
      //  this.translator = translator;
        this.premiumGreeting = premiumGreeting;
        this.standardGreeting = standardGreeting;
        this.basicGreeting = basicGreeting;
        this.premiumUser = premiumUser;
        this.standardUser = standardUser;

    }

    @Override
    public void configure() throws Exception {
            from(ENDPOINT_URI)
                    .choice()
                        .when(premiumUser)
                            .setProperty("response", method(premiumGreeting, "premiumGreeting"))
                            .bean(premiumGreeting, "premiumGreeting")
                        .when(standardUser)
                            .setProperty("response", method(standardGreeting, "standardGreeting"))
                            .bean(standardGreeting, "standardGreeting")
                    .otherwise()
                            .setProperty("response", constant("You are a Basic User"));
    }

}

