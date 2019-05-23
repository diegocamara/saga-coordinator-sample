package com.example.coordinator.configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.saga.InMemorySagaService;
import org.apache.camel.saga.CamelSagaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelSagaServiceConfiguration {

    @Bean
    public CamelSagaService camelSagaService() {
        return new InMemorySagaService();
    }

    @Bean("orderSagaProducer")
    public ProducerTemplate orderSagaProducer(CamelContext camelContext) {
        Endpoint orderSageEndpoint = camelContext.getEndpoint("direct:orderSaga");
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.setDefaultEndpoint(orderSageEndpoint);
        return producerTemplate;
    }


}
