package ru.hpclab.hl.module1.repository.postgresql.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.entity.postgresql.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepositoryJpa extends CrudRepository<OrderEntity, Long> {

    @Override
    List<OrderEntity> findAll();

    @Query("SELECT SUM(oi.quantity * p.price) FROM OrderItemEntity oi JOIN oi.product p WHERE oi.order.id = :orderId")
    BigDecimal calculateTotalPrice(@Param("orderId") Long orderId);

}
