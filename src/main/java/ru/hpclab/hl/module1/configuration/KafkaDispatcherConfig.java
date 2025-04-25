package ru.hpclab.hl.module1.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.queue.dispatch.KafkaMessageDispatcher;
import ru.hpclab.hl.module1.service.CustomerService;
import ru.hpclab.hl.module1.service.OrderService;
import ru.hpclab.hl.module1.service.ProductService;

@Configuration
public class KafkaDispatcherConfig {
    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            CustomerService customerService,
            ProductService productService,
            OrderService orderService,
            ObjectMapper objectMapper
    ) {
        return new KafkaMessageDispatcher(
                customerService,
                productService,
                orderService,
                objectMapper
        );
    }
}
