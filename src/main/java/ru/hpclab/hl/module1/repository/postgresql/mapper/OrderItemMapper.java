package ru.hpclab.hl.module1.repository.postgresql.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hpclab.hl.module1.dto.OrderItemDTO;
import ru.hpclab.hl.module1.entity.postgresql.OrderItemEntity;
import ru.hpclab.hl.module1.model.order.OrderItem;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItem entityToModel(OrderItemEntity entity);

    @Mapping(target = "order", ignore = true)
    OrderItemEntity modelToEntity(OrderItem model);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.identifier", target = "productIdentifier")
    OrderItemDTO entityToDTO(OrderItemEntity entity);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderItemEntity dtoToEntity(OrderItemDTO dto);
}

