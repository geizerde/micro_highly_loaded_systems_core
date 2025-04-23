package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.OrderDTO;
import ru.hpclab.hl.module1.model.order.Order;
import ru.hpclab.hl.module1.service.OrderService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final ObservabilityService observabilityService;

    @PostMapping
    public OrderDTO create(@RequestBody Order entity) {
        observabilityService.start(getClass().getSimpleName() + ":create");

        OrderDTO createdOrder = service.create(entity);

        observabilityService.stop(getClass().getSimpleName() + ":create");

        return createdOrder;
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        observabilityService.start(getClass().getSimpleName() + ":getAll");

        List<OrderDTO> orders = service.getAll();

        observabilityService.stop(getClass().getSimpleName() + ":getAll");

        return orders;
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable Long id, @RequestBody Order entity) {
        observabilityService.start(getClass().getSimpleName() + ":update");

        OrderDTO updatedOrder = service.update(id, entity);

        observabilityService.stop(getClass().getSimpleName() + ":update");

        return updatedOrder;
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getById");

        OrderDTO order = service.getById(id);

        observabilityService.stop(getClass().getSimpleName() + ":getById");

        return order;
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
