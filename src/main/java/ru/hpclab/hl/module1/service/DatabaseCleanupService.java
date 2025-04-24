package ru.hpclab.hl.module1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@Service
@AllArgsConstructor
public class DatabaseCleanupService {
    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ObservabilityService observabilityService;

    @Transactional
    public void clear() {
        observabilityService.start(getClass().getSimpleName() + ":clear");

        orderService.clear();
        customerService.clear();
        productService.clear();

        observabilityService.stop(getClass().getSimpleName() + ":clear");
    }
}
