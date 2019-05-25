package com.example.ordercoordinator.controller;

import com.example.ordercoordinator.entity.Order;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ordercoordinator")
@RestController
public class CoordinatorController {

    @Qualifier("orderSagaProducer")
    @Autowired
    private ProducerTemplate orderSagaProducer;

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        this.orderSagaProducer.sendBody("direct:orderSaga", order);
    }

}
