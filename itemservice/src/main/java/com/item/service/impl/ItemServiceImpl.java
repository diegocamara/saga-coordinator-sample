package com.item.service.impl;

import com.item.entity.Item;
import com.item.repository.ItemRepository;
import com.item.service.ItemService;

import javax.inject.Singleton;

@Singleton
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(Item item) {
        return itemRepository.create(item);
    }

}
