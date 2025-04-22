package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.postgresql.ProductEntity;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.repository.postgresql.jpa.ProductRepositoryJpa;
import ru.hpclab.hl.module1.repository.postgresql.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService extends AbstractService {
    private final ProductRepositoryJpa repository;
    private final ProductMapper productMapper;

    public Product create(Product entity) {
        ProductEntity productEntity = productMapper.modelToEntity(entity);
        ProductEntity savedEntity = repository.save(productEntity);

        return productMapper.entityToModel(savedEntity);
    }

    public List<Product> getAll() {
        List<ProductEntity> entities = repository.findAll();

        return entities.stream()
                .map(productMapper::entityToModel)
                .collect(Collectors.toList());
    }

    public Product getById(Long id) {
        Optional<ProductEntity> entityOpt = repository.findById(id);

        return entityOpt.map(productMapper::entityToModel).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product update(Long id, Product updatedEntity) {
        ProductEntity existedEntity = repository.findById(id).orElseThrow();

        BeanUtils.copyProperties(updatedEntity, existedEntity, getNullPropertyNames(updatedEntity));

        repository.save(existedEntity);

        return productMapper.entityToModel(existedEntity);
    }

    public void clear() {
        repository.deleteAll();
    }
}
