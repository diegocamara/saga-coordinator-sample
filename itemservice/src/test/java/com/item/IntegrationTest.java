package com.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.entity.Item;
import com.item.repository.Repository;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class IntegrationTest {

    @Inject
    @Client("/")
    private RxHttpClient httpClient;

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn201Created() {

        Item item = new Item();
        item.setDescription("Item Description");

        HttpRequest<?> httpRequest = HttpRequest.POST("/", item);
        HttpResponse httpResponse = httpClient.toBlocking().exchange(httpRequest);

        assertEquals(201, httpResponse.code());

    }

    @Test
    void shouldReturnStoredItem() throws IOException {

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Description");

        HttpRequest<Item> httpRequest = HttpRequest.POST("/", item);
        Item createdItem = objectMapper.readValue(httpClient.toBlocking().retrieve(httpRequest), Item.class);

        String url = "/".concat(createdItem.getId());

        HttpRequest<?> httpGetRequest = HttpRequest.GET(url);
        Item resultItem = objectMapper.readValue(httpClient.toBlocking().retrieve(httpGetRequest), Item.class);

        assertNotNull(resultItem.getId());

    }

}
