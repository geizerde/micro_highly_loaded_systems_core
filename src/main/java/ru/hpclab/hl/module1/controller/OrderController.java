package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.OrderDTO;
import ru.hpclab.hl.module1.model.order.Order;
import ru.hpclab.hl.module1.service.OrderService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderDTO create(@RequestBody Order entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable Long id, @RequestBody Order entity) {
        return service.update(id, entity);
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable Long id) {
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

    @GetMapping("/{id}/total-price")
    public BigDecimal calculateTotalPrice(@PathVariable Long id) {
        return service.calculateTotalPrice(id);
    }
}
