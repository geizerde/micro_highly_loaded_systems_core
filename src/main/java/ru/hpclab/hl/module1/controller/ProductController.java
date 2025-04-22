package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product entity) {
        return service.update(id, entity);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @DeleteMapping("/clear")
    public void clear() {
        service.clear();
    }
}
