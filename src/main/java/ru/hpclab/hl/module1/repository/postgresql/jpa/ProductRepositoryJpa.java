package ru.hpclab.hl.module1.repository.postgresql.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.entity.postgresql.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepositoryJpa extends CrudRepository<ProductEntity, Long> {

    @Override
    List<ProductEntity> findAll();
}
