package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Item;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.service.ItemService;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.findAll();
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody CreateItemRequest request) {
        if (request == null || request.name() == null || request.name().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name must not be empty");
        }
        Item created = itemService.create(request.name().trim());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    public record CreateItemRequest(String name) {
    }
}