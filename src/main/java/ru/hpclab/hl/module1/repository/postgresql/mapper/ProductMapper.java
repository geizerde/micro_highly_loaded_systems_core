package ru.hpclab.hl.module1.repository.postgresql.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.hpclab.hl.module1.entity.postgresql.ProductEntity;
import ru.hpclab.hl.module1.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product entityToModel(ProductEntity entity);

    ProductEntity modelToEntity(Product model);
}
