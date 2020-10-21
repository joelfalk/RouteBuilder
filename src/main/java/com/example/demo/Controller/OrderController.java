package com.example.demo.Controller;

import com.example.demo.Order.OrderDAO;
import com.example.demo.Order.OrderRoute;
import com.example.demo.Order.ValidOrder;
import com.example.demo.Order.Order;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.example.demo.Config.*;

@RestController
public class OrderController {
    private final CamelContext context;
    private final OrderDAO orderDAO;
    private ValidOrder valid;


    public OrderController(CamelContext context, OrderDAO orderDAO) {
        this.context = context;
        this.orderDAO = orderDAO;
    }

    @PostMapping(value = "/Order", produces = "application/json", consumes = "application/json")
    public String index(@RequestBody Order order) {
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        Exchange exchange = context.getEndpoint(ENDPOINT_ORDER).createExchange();
        exchange.getIn().setBody(order);
        if (valid.ValidOrder(order)) {
            exchange.getIn().setHeader("asd", "fail");
        } else {


        }
        producerTemplate.send(ENDPOINT_ORDER, exchange);

        String response = (String) exchange.getProperty("response");
        return response;


    }

}





















