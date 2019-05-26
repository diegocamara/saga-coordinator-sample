package com.item;

import com.item.entity.Item;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class IntegrationTest {

    private static EmbeddedServer server;
    private static HttpClient httpClient;

    @BeforeAll
    static void setUp(){
        server = ApplicationContext.build().run(EmbeddedServer.class);
        httpClient = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    static void tearDown(){
        server.stop();
    }

    @Test
    void shouldReturn201Created() {

        Item item = new Item();
        item.setDescription("Item Description");

        HttpRequest<?> httpRequest = HttpRequest.POST("/testing",item);
        HttpResponse httpResponse = httpClient.toBlocking().exchange(httpRequest);

        assertEquals(201, httpResponse.code());

    }

}
