package ru.hpclab.hl.module1.api.service;

import ru.hpclab.hl.module1.api.model.Identifiable;

import java.util.List;

public interface IRestFullService<T extends Identifiable> {
    T create(T entity);
    List<T> getAll();
    T getById(Long id);
    void delete(Long id);
    T update(Long id, T entity);
    void clear();
}