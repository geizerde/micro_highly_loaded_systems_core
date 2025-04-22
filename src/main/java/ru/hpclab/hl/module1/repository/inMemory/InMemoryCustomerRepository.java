package ru.hpclab.hl.module1.repository.inMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.api.repository.ICustomerRepository;
import ru.hpclab.hl.module1.model.Customer;

import java.util.HashMap;
import java.util.Map;


@Repository
public class InMemoryCustomerRepository
        extends AbstractInMemoryRepository<Customer>
        implements ICustomerRepository {
    private static final Map<Long, Customer> customerStorage = new HashMap<>();

    @Override
    protected Map<Long, Customer> getStorage() {
        return customerStorage;
    }
}

