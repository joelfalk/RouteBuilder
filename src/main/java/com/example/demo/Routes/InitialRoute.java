package com.example.demo.Routes;

import com.example.demo.CustomersAndUsers.IsNewUser;
import com.example.demo.CustomersAndUsers.IsUser;
import com.example.demo.Order.IsOrder;
import com.example.demo.Translator;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;
import static com.example.demo.Config.*;

@Service
public class InitialRoute extends RouteBuilder {

    private static final String PACKAGE_NAME = InitialRoute.class.getPackage().getName();
    private final IsUser isUser;
    private final IsNewUser isNewUser;
    private final IsOrder isOrder;
    private Translator translator;
    // private final NoUserMatchError noUserMatchError;


    @Autowired
    public InitialRoute(IsUser isUser, IsNewUser isNewUser, IsOrder isOrder
                        ) {
        this.isUser = isUser;
        this.isNewUser = isNewUser;
        this.isOrder = isOrder;

    }




    @Override
    public void configure() throws Exception {
        from(ENDPOINT_URI)
                .process(exchange -> log.info("Customer headers: "
                        + exchange.getIn().getHeaders()))
                .choice()
                .when(isUser)
                    .to(ENDPOINT_GREETING)
                .when(isNewUser)
                    .to(ENDPOINT_NEW_USER)
                .when(isOrder)
                    .to(ENDPOINT_ORDER)
                .otherwise()
                    .to(ENDPOINT_ERROR);
    }

}

