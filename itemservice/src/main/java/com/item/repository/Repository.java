package com.item.repository;

import java.io.Serializable;

public interface Repository<T, ID extends Serializable> {

    T save(T entity);

    T saveOrUpdate(T entity);

    void delete(T entity);

    void delete(ID id);

    T findById(ID id);

}
