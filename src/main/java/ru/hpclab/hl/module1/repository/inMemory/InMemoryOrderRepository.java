package ru.hpclab.hl.module1.repository.inMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.api.repository.IOrderRepository;
import ru.hpclab.hl.module1.model.order.Order;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryOrderRepository extends AbstractInMemoryRepository<Order>
        implements IOrderRepository {
    private static final Map<Long, Order> orderStorage = new HashMap<>();

    @Override
    protected Map<Long, Order> getStorage() {
        return orderStorage;
    }
}
