package ru.hpclab.hl.module1.repository.postgresql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hpclab.hl.module1.entity.postgresql.CustomerEntity;
import ru.hpclab.hl.module1.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer entityToModel(CustomerEntity entity);

    CustomerEntity modelToEntity(Customer model);
}
