package com.item.controller;

import com.item.entity.Item;
import com.item.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void shouldCallItemServiceCreateMethod() {

        Item item = new Item();
        item.setDescription("Item Descriiption");

        itemController.create(item);

        verify(itemService, times(1)).create(item);

    }

}
