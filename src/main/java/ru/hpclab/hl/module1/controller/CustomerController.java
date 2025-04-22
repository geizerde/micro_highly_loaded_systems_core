package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer create(@RequestBody Customer entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer entity) {
        return service.update(id, entity);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
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
