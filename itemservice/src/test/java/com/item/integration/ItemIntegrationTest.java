package com.item.integration;

import com.item.entity.Item;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class ItemIntegrationTest {

    @Inject
    @Client("/")
    private RxHttpClient rxHttpClient;

    @Disabled
    @Test
    void shouldReturn201Created() {

        Item item = new Item();
        item.setDescription("Item Description");

        HttpRequest<?> httpRequest = HttpRequest.POST("/", item);
        HttpResponse httpResponse = rxHttpClient.toBlocking().exchange(httpRequest);

        assertEquals(201, httpResponse.code());

    }

}
