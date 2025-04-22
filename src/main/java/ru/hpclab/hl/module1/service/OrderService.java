package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.OrderDTO;
import ru.hpclab.hl.module1.entity.postgresql.OrderEntity;
import ru.hpclab.hl.module1.entity.postgresql.OrderItemEntity;
import ru.hpclab.hl.module1.model.order.Order;
import ru.hpclab.hl.module1.repository.postgresql.jpa.OrderRepositoryJpa;
import ru.hpclab.hl.module1.repository.postgresql.mapper.OrderMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService extends AbstractService {
    private final OrderRepositoryJpa repository;
    private final OrderMapper orderMapper;

    public OrderDTO create(Order entity) {
        var orderItems = entity.getOrderItems();

        entity.setOrderItems(null);

        OrderEntity orderEntity = orderMapper.modelToEntity(entity);

        OrderEntity existedEntity = repository.save(orderEntity);

        entity.setOrderItems(orderItems);

        BeanUtils.copyProperties(existedEntity, entity, getNullPropertyNames(existedEntity));

        orderEntity = orderMapper.modelToEntity(entity);

        if (orderEntity.getOrderItems() != null) {
            for (OrderItemEntity item : orderEntity.getOrderItems()) {
                item.setOrder(orderEntity);
            }
        }

        existedEntity = repository.save(orderEntity);

        return orderMapper.entityToDTO(existedEntity);
    }

    public List<OrderDTO> getAll() {
        List<OrderEntity> entities = repository.findAll();
        return entities.stream().map(orderMapper::entityToDTO).collect(Collectors.toList());
    }

    public OrderDTO getById(Long id) {
        Optional<OrderEntity> entityOpt = repository.findById(id);
        return entityOpt.map(orderMapper::entityToDTO).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public OrderDTO update(Long id, Order updatedEntity) {
        OrderEntity existedEntity = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedEntity, existedEntity, getNullPropertyNames(updatedEntity));
        OrderEntity savedEntity = repository.save(existedEntity);
        return orderMapper.entityToDTO(savedEntity);
    }

    public void clear() {
        repository.deleteAll();
    }

    public BigDecimal calculateTotalPrice(Long orderId) {
        return repository.calculateTotalPrice(orderId);
    }
}

