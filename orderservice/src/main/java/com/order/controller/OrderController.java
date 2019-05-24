package com.order.controller;

import com.order.entity.Order;
import com.order.service.OrderService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Status(HttpStatus.CREATED)
    @Post
    public void create(@Body Order order) {
        this.orderService.create(order);
    }

}
