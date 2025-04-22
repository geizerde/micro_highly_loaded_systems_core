package ru.hpclab.hl.module1.repository.postgresql.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.entity.postgresql.CustomerEntity;

import java.util.List;

@Repository
public interface CustomerRepositoryJpa extends CrudRepository<CustomerEntity, Long> {

    @Override
    List<CustomerEntity> findAll();
}
