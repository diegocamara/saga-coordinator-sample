package com.item.repository.impl;

import com.item.entity.Item;
import com.item.repository.ItemRepository;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class ItemRepositoryImpl extends RepositoryImpl<Item, String> implements ItemRepository {

    @Override
    public Item save(Item entity) {
        entity.setId(UUID.randomUUID().toString());
        return super.save(entity);
    }

    @Override
    Class<Item> persistentClass() {
        return Item.class;
    }
}
