package com.item.service;

import com.item.entity.Item;

public interface ItemService {
    Item create(Item item);
    Item findById(String id);
}
