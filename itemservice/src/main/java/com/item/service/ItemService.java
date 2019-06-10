package com.item.service;

import com.item.entity.Item;

import java.util.List;

public interface ItemService {
    Item create(Item item);

    Item findById(String id);

    void update(Item item);

    void delete(String id);

    List<Item> findAll();
}
