package com.item.controller;

import com.item.entity.Item;
import com.item.service.ItemService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ItemControllerTest {

    private ItemService itemService;
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        itemService = mock(ItemService.class);
        itemController = new ItemController(itemService);
    }

    @Test
    void shouldReturnItemWithId() {

        Item item = new Item();
        item.setDescription("Item Descriiption");

        Item itemCreated = new Item();
        itemCreated.setId(UUID.randomUUID().toString());
        itemCreated.setDescription("Item Descriiption");

        when(itemService.create(item)).thenReturn(itemCreated);

        Item resultItem = itemController.create(item);

        Assertions.assertNotNull(resultItem.getId());

    }

    @Test
    void shouldReturnItem() {

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Item Descriiption");

        when(itemService.findById(item.getId())).thenReturn(item);

        Item resultItem = itemController.findById(item.getId());

        Assertions.assertEquals(item, resultItem);

    }

}
