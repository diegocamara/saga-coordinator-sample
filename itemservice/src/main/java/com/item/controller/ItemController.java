package com.item.controller;

import com.item.entity.Item;
import com.item.service.ItemService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;

@Controller
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Status(HttpStatus.CREATED)
    @Post("/testing")
    public void create(@Body Item item) {
        this.itemService.create(item);
    }

}
