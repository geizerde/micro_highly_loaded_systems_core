package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.service.ProductService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ObservabilityService observabilityService;

    @PostMapping
    public Product create(@RequestBody Product entity) {
        observabilityService.start(getClass().getSimpleName() + ":create");

        Product createdProduct = service.create(entity);

        observabilityService.stop(getClass().getSimpleName() + ":create");

        return createdProduct;
    }

    @GetMapping
    public List<Product> getAll() {
        observabilityService.start(getClass().getSimpleName() + ":getAll");

        List<Product> products = service.getAll();

        observabilityService.stop(getClass().getSimpleName() + ":getAll");

        return products;
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product entity) {
        observabilityService.start(getClass().getSimpleName() + ":update");

        Product updatedProduct = service.update(id, entity);

        observabilityService.stop(getClass().getSimpleName() + ":update");

        return updatedProduct;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getById");

        Product product = service.getById(id);

        observabilityService.stop(getClass().getSimpleName() + ":getById");

        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        observabilityService.start(getClass().getSimpleName() + ":delete");

        service.delete(id);

        observabilityService.stop(getClass().getSimpleName() + ":delete");
    }

    @DeleteMapping("/clear")
    public void clear() {
        observabilityService.start(getClass().getSimpleName() + ":clear");

        service.clear();

        observabilityService.stop(getClass().getSimpleName() + ":clear");
    }
}
