package com.demo.AdvicesAOP.XmlConfiguration;

import com.demo.AdvicesAOP.XmlConfiguration.Advices.Order;
import org.springframework.context.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContextWrapper.getApplicationContext();

        Order order = (Order) applicationContext.getBean("order");

//        order.getOrderProducts().clear(); // AfterReturning Advice will not run

        order.displayOrderProducts();
    }
}
