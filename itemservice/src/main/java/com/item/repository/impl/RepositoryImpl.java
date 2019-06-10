package com.item.repository.impl;

import com.item.repository.Repository;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    public void delete(ID id) {
        T entity = hibernateSession().load(persistentClass(), id);
        delete(entity);
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(persistentClass(), id);
    }


    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass());
        Root<T> rootEntry = criteriaQuery.from(persistentClass());
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    private Session hibernateSession() {
        return this.entityManager.unwrap(Session.class);
    }

    abstract Class<T> persistentClass();

}
