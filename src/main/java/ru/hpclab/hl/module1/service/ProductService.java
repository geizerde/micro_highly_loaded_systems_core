package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.postgresql.ProductEntity;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.repository.postgresql.jpa.ProductRepositoryJpa;
import ru.hpclab.hl.module1.repository.postgresql.mapper.ProductMapper;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService extends AbstractService {
    private final ProductRepositoryJpa repository;
    private final ProductMapper productMapper;
    private final ObservabilityService observabilityService;

    public Product create(Product entity) {
        observabilityService.start(getClass().getSimpleName() + ":create");

        ProductEntity productEntity = productMapper.modelToEntity(entity);
        ProductEntity savedEntity = repository.save(productEntity);

        observabilityService.stop(getClass().getSimpleName() + ":create");

        return productMapper.entityToModel(savedEntity);
    }

    public List<Product> getAll() {
        observabilityService.start(getClass().getSimpleName() + ":getAll");

        List<ProductEntity> entities = repository.findAll();
        List<Product> result = entities.stream()
                .map(productMapper::entityToModel)
                .collect(Collectors.toList());

        observabilityService.stop(getClass().getSimpleName() + ":getAll");

        return result;
    }

    public Product getById(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getById");

        Optional<ProductEntity> entityOpt = repository.findById(id);
        Product result = entityOpt.map(productMapper::entityToModel).orElse(null);

        observabilityService.stop(getClass().getSimpleName() + ":getById");

        return result;
    }

    public void delete(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":delete");

        repository.deleteById(id);

        observabilityService.stop(getClass().getSimpleName() + ":delete");
    }

    public Product update(Long id, Product updatedEntity) {
        observabilityService.start(getClass().getSimpleName() + ":update");

        ProductEntity existedEntity = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedEntity, existedEntity, getNullPropertyNames(updatedEntity));

        ProductEntity savedEntity = repository.save(existedEntity);

        observabilityService.stop(getClass().getSimpleName() + ":update");

        return productMapper.entityToModel(savedEntity);
    }

    public void clear() {
        observabilityService.start(getClass().getSimpleName() + ":clear");

        repository.deleteAll();

        observabilityService.stop(getClass().getSimpleName() + ":clear");
    }
}
