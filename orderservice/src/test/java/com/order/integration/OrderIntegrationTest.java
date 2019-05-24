package com.order.integration;

import com.order.entity.Order;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class OrderIntegrationTest {

    @Inject
    @Client("/")
    private RxHttpClient rxHttpClient;

    @Test
    public void shouldReturn201Created(){

        Order order = new Order();
        order.setDescription("Order Description");

        HttpRequest httpRequest = HttpRequest.POST("/", order);
        HttpResponse httpResponse = rxHttpClient.toBlocking().exchange(httpRequest);

        assertEquals(201, httpResponse.code());

    }

}
