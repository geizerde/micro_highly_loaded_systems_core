package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.postgresql.CustomerEntity;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.repository.postgresql.jpa.CustomerRepositoryJpa;
import ru.hpclab.hl.module1.repository.postgresql.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService extends AbstractService {
    private final CustomerRepositoryJpa repository;
    private final CustomerMapper customerMapper;

    public Customer create(Customer entity) {
        CustomerEntity productEntity = customerMapper.modelToEntity(entity);
        CustomerEntity savedEntity = repository.save(productEntity);

        return customerMapper.entityToModel(savedEntity);
    }

    public List<Customer> getAll() {
        List<CustomerEntity> entities = repository.findAll();

        return entities.stream()
                .map(customerMapper::entityToModel)
                .collect(Collectors.toList());
    }

    public Customer getById(Long id) {
        Optional<CustomerEntity> entityOpt = repository.findById(id);

        return entityOpt.map(customerMapper::entityToModel).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Customer update(Long id, Customer updatedEntity) {
        CustomerEntity existedEntity = repository.findById(id).orElseThrow();

        BeanUtils.copyProperties(updatedEntity, existedEntity, getNullPropertyNames(updatedEntity));

        repository.save(existedEntity);

        return customerMapper.entityToModel(existedEntity);
    }

    public void clear() {
        repository.deleteAll();
    }
}
