package com.example.demo.Routes;

import com.example.demo.CustomersAndUsers.UserType.IsBasicUser;
import com.example.demo.CustomersAndUsers.UserType.IsPremiumUser;
import com.example.demo.CustomersAndUsers.UserType.IsStandardUser;
import com.example.demo.Greetings.BasicGreeting;
import com.example.demo.Greetings.PremiumGreeting;
import com.example.demo.Greetings.StandardGreeting;
import com.example.demo.Translator;
import static com.example.demo.Config.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingsRoute extends RouteBuilder {
    private final StandardGreeting standardGreeting;
    private final BasicGreeting basicGreeting;
    private final PremiumGreeting premiumGreeting;
    private final IsStandardUser isStandardUser;
    private final IsPremiumUser isPremiumUser;
    private final IsBasicUser isBasicUser;
    private Translator translator;
    // private final NoUserMatchError noUserMatchError;


    @Autowired
    public GreetingsRoute(PremiumGreeting premiumGreeting,
                        StandardGreeting standardGreeting,
                        BasicGreeting basicGreeting,
                        IsPremiumUser isPremiumUser,
                        IsStandardUser isStandardUser,
                        IsBasicUser isBasicUser

    ) {
        this.premiumGreeting = premiumGreeting;
        this.standardGreeting = standardGreeting;
        this.basicGreeting = basicGreeting;
        this.isPremiumUser = isPremiumUser;
        this.isStandardUser = isStandardUser;
        this.isBasicUser = isBasicUser;
    }


    @Override
    public void configure() throws Exception {
        from(ENDPOINT_GREETING)
                .choice()
                    .when(isPremiumUser)
                        .setProperty("response", method(premiumGreeting, "premiumGreeting"))
                        .bean(premiumGreeting, "premiumGreeting")
                    .when(isStandardUser)
                        .setProperty("response", method(standardGreeting, "standardGreeting"))
                        .bean(standardGreeting, "standardGreeting")
                    .when(isBasicUser)
                        .setProperty("response", method(basicGreeting, "basicGreeting"))
                        .bean(basicGreeting, "basicGreeting")
                .end();
    }

}
