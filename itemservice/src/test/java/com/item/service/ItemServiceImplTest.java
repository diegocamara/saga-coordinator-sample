package com.item.service;


import com.item.entity.Item;
import com.item.repository.ItemRepository;
import com.item.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemServiceImplTest {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void shouldReturnPersistedItem() {

        Item item = new Item();
        item.setDescription("Item Description");

        Item itemCreated = new Item();
        itemCreated.setId(UUID.randomUUID().toString());
        itemCreated.setDescription(item.getDescription());

        when(itemRepository.save(any(Item.class))).thenReturn(itemCreated);

        Item resultItem = itemService.create(item);

        assertEquals(itemCreated.getId(), resultItem.getId());

    }

}
