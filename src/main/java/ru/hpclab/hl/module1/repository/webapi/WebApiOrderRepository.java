package ru.hpclab.hl.module1.repository.webapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.model.order.OrderCustomerPrice;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebApiOrderRepository {
    private final WebClient totalCostOfOrderServiceWebClient;

    public List<OrderCustomerPrice> findAll() {
        return totalCostOfOrderServiceWebClient.get()
                .uri("orders/total-prices")
                .retrieve()
                .bodyToFlux(OrderCustomerPrice.class)
                .collectList()
                .block();
    }
}
