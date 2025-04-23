package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.postgresql.CustomerEntity;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.repository.postgresql.jpa.CustomerRepositoryJpa;
import ru.hpclab.hl.module1.repository.postgresql.mapper.CustomerMapper;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService extends AbstractService {
    private final CustomerRepositoryJpa repository;
    private final CustomerMapper customerMapper;
    private final ObservabilityService observabilityService;

    public Customer create(Customer entity) {
        observabilityService.start(getClass().getSimpleName() + ":create");

        CustomerEntity productEntity = customerMapper.modelToEntity(entity);
        CustomerEntity savedEntity = repository.save(productEntity);

        observabilityService.stop(getClass().getSimpleName() + ":create");

        return customerMapper.entityToModel(savedEntity);
    }

    public List<Customer> getAll() {
        observabilityService.start(getClass().getSimpleName() + ":getAll");

        List<CustomerEntity> entities = repository.findAll();

        observabilityService.stop(getClass().getSimpleName() + ":getAll");

        return entities.stream()
                .map(customerMapper::entityToModel)
                .collect(Collectors.toList());
    }

    public Customer getById(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getById");

        Optional<CustomerEntity> entityOpt = repository.findById(id);

        observabilityService.stop(getClass().getSimpleName() + ":getById");

        return entityOpt.map(customerMapper::entityToModel).orElse(null);
    }

    public void delete(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":delete");

        repository.deleteById(id);

        observabilityService.stop(getClass().getSimpleName() + ":delete");
    }

    public Customer update(Long id, Customer updatedEntity) {
        observabilityService.start(getClass().getSimpleName() + ":update");

        CustomerEntity existedEntity = repository.findById(id).orElseThrow();

        BeanUtils.copyProperties(updatedEntity, existedEntity, getNullPropertyNames(updatedEntity));

        repository.save(existedEntity);

        observabilityService.stop(getClass().getSimpleName() + ":update");

        return customerMapper.entityToModel(existedEntity);
    }

    public void clear() {
        observabilityService.start(getClass().getSimpleName() + ":clear");

        repository.deleteAll();

        observabilityService.stop(getClass().getSimpleName() + ":clear");
    }
}
