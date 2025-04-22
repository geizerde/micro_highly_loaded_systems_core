package ru.hpclab.hl.module1.repository.inMemory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.api.repository.IProductRepository;
import ru.hpclab.hl.module1.model.Product;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryProductRepository extends AbstractInMemoryRepository<Product>
        implements IProductRepository {
    private static final Map<Long, Product> productStorage = new HashMap<>();

    @Override
    protected Map<Long, Product> getStorage() {
        return productStorage;
    }
}
