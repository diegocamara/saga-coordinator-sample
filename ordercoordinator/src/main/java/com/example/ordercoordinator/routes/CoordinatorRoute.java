package com.example.ordercoordinator.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.saga.CamelSagaService;
import org.springframework.stereotype.Component;

@Component
public class CoordinatorRoute extends RouteBuilder {

    private CamelSagaService camelSagaService;

    public CoordinatorRoute(CamelSagaService camelSagaService) throws Exception {
        this.camelSagaService = camelSagaService;
        getContext().addService(camelSagaService);
    }


    @Override
    public void configure() throws Exception {

        from("direct:orderSaga")
                .saga()
                .to("direct:reserveOrderItems");

        from("direct:reserveOrderItems")
                .setBody().body().marshal().json(JsonLibrary.Jackson)
                .saga()
                .option("order", body())
                .propagation(SagaPropagation.MANDATORY)
                .compensation("direct:cancelOrderItems")
                .to("http4://localhost:8081/api");

        from("direct:cancelOrderItems")
                .transform(header("order"))
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.DELETE)
                .to("http4://localhost:8081/api");


    }
}
