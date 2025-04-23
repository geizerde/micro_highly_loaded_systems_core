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
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService extends AbstractService {
    private final OrderRepositoryJpa repository;
    private final OrderMapper orderMapper;
    private final ObservabilityService observabilityService;

    public OrderDTO create(Order entity) {
        observabilityService.start(getClass().getSimpleName() + ":create");

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

        observabilityService.stop(getClass().getSimpleName() + ":create");

        return orderMapper.entityToDTO(existedEntity);
    }

    public List<OrderDTO> getAll() {
        observabilityService.start(getClass().getSimpleName() + ":getAll");

        List<OrderEntity> entities = repository.findAll();
        List<OrderDTO> result = entities.stream()
                .map(orderMapper::entityToDTO)
                .collect(Collectors.toList());

        observabilityService.stop(getClass().getSimpleName() + ":getAll");

        return result;
    }

    public OrderDTO getById(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getById");

        Optional<OrderEntity> entityOpt = repository.findById(id);
        OrderDTO result = entityOpt.map(orderMapper::entityToDTO).orElse(null);

        observabilityService.stop(getClass().getSimpleName() + ":getById");

        return result;
    }

    public void delete(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":delete");

        repository.deleteById(id);

        observabilityService.stop(getClass().getSimpleName() + ":delete");
    }

    public OrderDTO update(Long id, Order updatedEntity) {
        observabilityService.start(getClass().getSimpleName() + ":update");

        OrderEntity existedEntity = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedEntity, existedEntity, getNullPropertyNames(updatedEntity));

        OrderEntity savedEntity = repository.save(existedEntity);

        observabilityService.stop(getClass().getSimpleName() + ":update");

        return orderMapper.entityToDTO(savedEntity);
    }

    public void clear() {
        observabilityService.start(getClass().getSimpleName() + ":clear");

        repository.deleteAll();

        observabilityService.stop(getClass().getSimpleName() + ":clear");
    }
}
