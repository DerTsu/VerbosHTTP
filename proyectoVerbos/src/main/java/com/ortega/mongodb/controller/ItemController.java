package com.ortega.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ortega.mongodb.model.GroceryItem;
import com.ortega.mongodb.repository.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GroceryItem>> getAllItems() {
        List<GroceryItem> items = itemRepository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertItem(@RequestBody GroceryItem groceryItem) {
        itemRepository.save(groceryItem);
        return new ResponseEntity<>("Item inserted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestBody GroceryItem groceryItem) {
        itemRepository.save(groceryItem);
        return new ResponseEntity<>("Item updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        itemRepository.deleteById(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/updateData/{id}")
    public ResponseEntity<String> updateData(@PathVariable String id, @RequestBody GroceryItem groceryItem) {
        GroceryItem existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            // Update data
            existingItem.setName(groceryItem.getName());
            existingItem.setQuantity(groceryItem.getQuantity());
            itemRepository.save(existingItem);
            return new ResponseEntity<>("Item data updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/getAll", method = RequestMethod.HEAD)
    public ResponseEntity<?> handleHeadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsUpdate() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "POST, PUT, DELETE, PATCH, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
    
}