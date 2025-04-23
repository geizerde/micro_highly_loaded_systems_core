package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.service.CustomerService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    private final ObservabilityService observabilityService;

    @PostMapping
    public Customer create(@RequestBody Customer entity) {
        observabilityService.start(getClass().getSimpleName() + ":create");

        Customer createdCustomer = service.create(entity);

        observabilityService.stop(getClass().getSimpleName() + ":create");

        return createdCustomer;
    }

    @GetMapping
    public List<Customer> getAll() {
        observabilityService.start(getClass().getSimpleName() + ":getAll");

        List<Customer> customers = service.getAll();

        observabilityService.stop(getClass().getSimpleName() + ":getAll");

        return customers;
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer entity) {
        observabilityService.start(getClass().getSimpleName() + ":update");

        Customer updatedCustomer = service.update(id, entity);

        observabilityService.stop(getClass().getSimpleName() + ":update");

        return updatedCustomer;
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getById");

        Customer customer = service.getById(id);

        observabilityService.stop(getClass().getSimpleName() + ":getById");

        return customer;
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
