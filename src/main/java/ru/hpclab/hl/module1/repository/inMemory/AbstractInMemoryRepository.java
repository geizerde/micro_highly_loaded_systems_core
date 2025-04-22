package ru.hpclab.hl.module1.repository.inMemory;

import ru.hpclab.hl.module1.api.model.Identifiable;
import ru.hpclab.hl.module1.api.repository.IRestFullRepository;

import java.util.*;

abstract class AbstractInMemoryRepository<T extends Identifiable>
        implements IRestFullRepository<T> {

    protected abstract Map<Long, T> getStorage();

    public List<T> findAll() {
        return new ArrayList<>(getStorage().values());
    }

    public T findById(Long id) {
        if (!getStorage().containsKey(id)) {
            throw new NoSuchElementException("Entity not found: " + id);
        }
        return getStorage().get(id);
    }

    public void delete(Long id) {
        if (getStorage().remove(id) == null) {
            throw new NoSuchElementException("Entity not found: " + id);
        }
    }

    public T save(T entity) {
        UUID identifier = UUID.randomUUID();
        try {
            entity.setIdentifier(identifier);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set identifier", e);
        }

        getStorage().put(entity.getId(), entity);

        return entity;
    }

    public T update(T entity) {
        try {
            var id = entity.getId();

            if (!getStorage().containsKey(id)) {
                throw new NoSuchElementException("Entity not found: " + id);
            }
            getStorage().put(id, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Unable to update entity", e);
        }
    }

    public void clear() {
        getStorage().clear();
    }
}
