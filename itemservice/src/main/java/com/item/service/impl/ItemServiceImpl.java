package com.item.service.impl;

import com.item.entity.Item;
import com.item.repository.ItemRepository;
import com.item.service.ItemService;

import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;

@Singleton
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Item create(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Item findById(String id) {
        return this.itemRepository.findById(id);
    }

}
