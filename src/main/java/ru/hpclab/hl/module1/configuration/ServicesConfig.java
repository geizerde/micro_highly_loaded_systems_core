package ru.hpclab.hl.module1.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServicesConfig {
    @Value("${total-cost-of-order.service.host}")
    private String totalCostOfOrderServiceHost;

    @Value("${total-cost-of-order.service.port}")
    private String totalCostOfOrderServicePort;

    @Bean
    public WebClient totalCostOfOrderServiceWebClient(WebClient.Builder builder) {
        String baseUrl = "http://" + totalCostOfOrderServiceHost + ":" + totalCostOfOrderServicePort;
        return builder
                .baseUrl(baseUrl)
                .build();
    }
}
