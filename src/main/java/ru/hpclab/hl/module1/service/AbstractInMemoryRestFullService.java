package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.api.model.Identifiable;
import ru.hpclab.hl.module1.api.repository.IRestFullRepository;
import ru.hpclab.hl.module1.api.service.IRestFullService;

import java.util.List;

public abstract class AbstractInMemoryRestFullService<T extends Identifiable> implements IRestFullService<T> {
    protected final IRestFullRepository<T> repository;

    public AbstractInMemoryRestFullService(IRestFullRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public T update(Long id, T updatedEntity) {
        updatedEntity.setId(id);
        return repository.update(updatedEntity);
    }

    @Override
    public void clear() {
        repository.clear();
    }
}

