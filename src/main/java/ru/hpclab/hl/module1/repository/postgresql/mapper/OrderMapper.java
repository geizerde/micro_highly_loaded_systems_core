package ru.hpclab.hl.module1.repository.postgresql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.hpclab.hl.module1.dto.OrderDTO;
import ru.hpclab.hl.module1.entity.postgresql.OrderEntity;
import ru.hpclab.hl.module1.model.order.Order;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, OrderItemMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderItems", target = "orderItems")
    Order entityToModel(OrderEntity entity);

    @Mapping(source = "orderItems", target = "orderItems")
    OrderEntity modelToEntity(Order model);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.identifier", target = "customerIdentifier")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    OrderDTO entityToDTO(OrderEntity entity);

    @Mapping(target = "customer", ignore = true)
    OrderEntity dtoToEntity(OrderDTO dto);
}
