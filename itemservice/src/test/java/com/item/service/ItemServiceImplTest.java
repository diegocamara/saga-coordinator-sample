package com.item.service;


import com.item.entity.Item;
import com.item.repository.ItemRepository;
import com.item.service.impl.ItemServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldReturnPersistedItemById(){

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Item Description");

        when(itemRepository.findById(item.getId())).thenReturn(item);

        Item resultItem = itemService.findById(item.getId());

        Assertions.assertNotNull(resultItem);

    }

    @Test
    void shouldCallItemRepositoryUpdate(){

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Item Description");

        itemService.update(item);

        verify(itemRepository).saveOrUpdate(item);

    }

    @Test
    void shouldCallItemRepositoryDelete(){
        String itemId = UUID.randomUUID().toString();

        itemService.delete(itemId);

        verify(itemRepository).delete(itemId);

    }

}
