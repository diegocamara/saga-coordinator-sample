package com.order.controller;

import com.order.entity.Order;
import com.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class OrderControllerTest {

    private OrderService orderService;
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        orderService = mock(OrderService.class);
        orderController = new OrderController(orderService);
    }

    @Test
    public void shouldCallOrderServiceCreateMethod() {

        Order order = new Order();
        order.setDescription("Order Descriiption");

        orderController.create(order);

        verify(orderService, times(1)).create(order);

    }

}
