package ru.hpclab.hl.module1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseCleanupService {
    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @Transactional
    public void clear() {
        orderService.clear();
        customerService.clear();
        productService.clear();
    }
}
