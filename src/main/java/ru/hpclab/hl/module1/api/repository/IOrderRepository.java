package ru.hpclab.hl.module1.api.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.order.Order;

@Repository
public interface IOrderRepository extends IRestFullRepository<Order> {}