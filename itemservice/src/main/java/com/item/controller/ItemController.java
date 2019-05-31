package com.item.controller;

import com.item.entity.Item;
import com.item.service.ItemService;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;

@Controller("/")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    public void create(@Body Item item) {
        this.itemService.create(item);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Item findById(String id) {
        return itemService.findById(id);
    }
}
