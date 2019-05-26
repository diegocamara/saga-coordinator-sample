package com.item.repository.impl;

import com.item.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class RepositoryImpl<T, ID extends Serializable> implements Repository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T saveOrUpdate(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(persistentClass(), id);
    }

    abstract Class<T> persistentClass();

}
