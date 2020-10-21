package com.example.demo.Order;

import com.example.demo.Stock.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;


public class ValidOrder {
    @Bean
    public boolean ValidOrder(Order order) {
        boolean valid = true;
        Logger log = null;
        /*if(StockDAO.getQuantity(order.getPid) < order.getQuantity){
            log.config("Out of Stock");
            valid = false;
        } else if (StockDAO.getPrice(order.getPid) > CustomerDAO.getFunds()) {
            log.config("insufficient funds");
            valid = false;
        } else {

        }
}*/
        return valid;

    }
}
