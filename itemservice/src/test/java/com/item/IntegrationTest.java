package com.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.entity.Item;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
    private EntityManager entityManager;

    @Inject
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
//        server = ApplicationContext.build().run(EmbeddedServer.class);
//        httpClient = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    static void tearDown() {
//        server.stop();
    }

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

//        HttpRequest<?> httpRequest = HttpRequest.POST("/", item);
//        HttpResponse httpResponse = httpClient.toBlocking().exchange(httpRequest);

        entityManager.persist(item);
        entityManager.flush();

        String url = "/".concat(item.getId());

        HttpRequest<?> httpGetRequest = HttpRequest.GET(url);
        Item resultItem = objectMapper.readValue(httpClient.toBlocking().retrieve(httpGetRequest), Item.class);

        assertNotNull(resultItem.getId());

    }

}
