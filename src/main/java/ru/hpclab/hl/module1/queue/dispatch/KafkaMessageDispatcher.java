package ru.hpclab.hl.module1.queue.dispatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.model.order.Order;
import ru.hpclab.hl.module1.model.queue.EntityType;
import ru.hpclab.hl.module1.model.queue.KafkaOperationMessage;
import ru.hpclab.hl.module1.model.queue.OperationType;
import ru.hpclab.hl.module1.service.CustomerService;
import ru.hpclab.hl.module1.service.OrderService;
import ru.hpclab.hl.module1.service.ProductService;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public void dispatch(KafkaOperationMessage msg) {
        EntityType entity = msg.getEntity();
        OperationType operation = msg.getOperation();

        switch (entity) {
            case CUSTOMER -> handleCustomer(operation, msg.getPayload());
            case PRODUCT -> handleProduct(operation, msg.getPayload());
            case ORDER -> handleOrder(operation, msg.getPayload());
        }
    }

    private void handleCustomer(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> customerService.create(deserialize(payload, Customer.class));
            case PUT -> customerService.update(payload.get("id").asLong(), deserialize(payload, Customer.class));
            case DELETE -> customerService.delete(payload.get("id").asLong());
            case CLEAR -> customerService.clear();
        }
    }

    private void handleProduct(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> productService.create(deserialize(payload, Product.class));
            case PUT -> productService.update(payload.get("id").asLong(), deserialize(payload, Product.class));
            case DELETE -> productService.delete(payload.get("id").asLong());
            case CLEAR -> productService.clear();
        }
    }

    private void handleOrder(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> orderService.create(deserialize(payload, Order.class));
            case PUT -> orderService.update(payload.get("id").asLong(), deserialize(payload, Order.class));
            case DELETE -> orderService.delete(payload.get("id").asLong());
            case CLEAR -> orderService.clear();
        }
    }

    private <T> T deserialize(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload to " + clazz.getSimpleName(), e);
        }
    }
}
