package com.item.repository.impl;

import com.item.entity.Item;
import com.item.repository.ItemRepository;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Singleton
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ItemRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Item create(Item item) {
        item.setId(UUID.randomUUID().toString());
        entityManager.persist(item);
        return item;
    }
}
