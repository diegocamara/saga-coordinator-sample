package com.item.controller;

import com.item.entity.Item;
import com.item.service.ItemService;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Item create(@Body Item item) {
        return this.itemService.create(item);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Item findById(String id) {
        return this.itemService.findById(id);
    }

    @Put
    @Status(HttpStatus.NO_CONTENT)
    public void update(@Body Item item) {
        this.itemService.update(item);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void delete(String id) {
        this.itemService.delete(id);
    }

    @Get
    @Status(HttpStatus.OK)
    public List<Item> findAll() {
        return this.itemService.findAll();
    }

}
