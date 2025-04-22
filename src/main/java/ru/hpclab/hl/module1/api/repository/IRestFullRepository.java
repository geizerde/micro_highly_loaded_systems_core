package ru.hpclab.hl.module1.api.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.api.model.Identifiable;

import java.util.List;

@Repository
public interface IRestFullRepository<T extends Identifiable>  {
    List<T> findAll();
    T findById(Long id);
    void delete(Long id);
    T save(T entity);
    T update(T entity);
    void clear();
}
