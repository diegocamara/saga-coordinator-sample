package com.item.repository.impl;

import com.item.repository.Repository;
import org.hibernate.Session;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


    private Session hibernateSession() {
        return this.entityManager.unwrap(Session.class);
    }

    abstract Class<T> persistentClass();

//    public List<T> allEntries() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(persistentClass());
//        Root<T> rootEntry = cq.from(persistentClass());
//        CriteriaQuery<T> all = cq.select(rootEntry);
//        TypedQuery<T> allQuery = entityManager.createQuery(all);
//        return allQuery.getResultList();
//    }

}
