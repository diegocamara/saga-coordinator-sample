package com.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.entity.Item;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.io.IOException;

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
        item.setDescription("Description");

        Item createdItem = createItem(item);

        Item resultItem = findItem(createdItem.getId());

        assertNotNull(resultItem.getId());

    }

    @Test
    void shouldUpdateStoredItem() throws IOException {

        Item item = new Item();
        item.setDescription("Description 1");

        Item createdItem = createItem(item);

        createdItem.setDescription("Description 2");

        updateItem(createdItem);

        Item updatedItem = findItem(createdItem.getId());

        Assertions.assertEquals(updatedItem.getDescription(), createdItem.getDescription());

    }

    @Test
    void shouldDeleteItem() throws IOException {

        Item item = new Item();
        item.setDescription("Description 1");

        Item createdItem = createItem(item);

        deleteItem(createdItem.getId());

        String itemUrl = "/".concat(createdItem.getId());

        HttpClientResponseException httpClientResponseException = Assertions.assertThrows(HttpClientResponseException.class, () ->
                httpClient.toBlocking().exchange(HttpRequest.GET(itemUrl))
        );

        Assertions.assertEquals(404, httpClientResponseException.getResponse().code());

    }

    private HttpResponse<?> deleteItem(String itemId) {
        String itemUrl = "/".concat(itemId);
        HttpRequest<?> httpRequest = HttpRequest.DELETE(itemUrl);
        return httpClient.toBlocking().exchange(httpRequest);
    }

    private Item createItem(Item item) throws IOException {
        HttpRequest<Item> httpRequest = HttpRequest.POST("/", item);
        return objectMapper.readValue(httpClient.toBlocking().retrieve(httpRequest), Item.class);
    }

    private HttpResponse<?> updateItem(Item createdItem) {
        HttpRequest<?> httpRequest = HttpRequest.PUT("/", createdItem);
        return httpClient.toBlocking().exchange(httpRequest);
    }

    private Item findItem(String itemId) throws IOException {
        String itemUrl = "/".concat(itemId);
        HttpRequest<Item> httpRequestItem = HttpRequest.GET(itemUrl);
        return objectMapper.readValue(httpClient.toBlocking().retrieve(httpRequestItem), Item.class);
    }

}
