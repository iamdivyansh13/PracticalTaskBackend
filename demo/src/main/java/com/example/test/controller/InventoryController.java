package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.entity.InventoryItem;
import com.example.test.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/items")
    public InventoryItem createItem(@RequestBody InventoryItem item) {
        return inventoryService.createItem(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<InventoryItem> updateItem(@PathVariable Long id, @RequestBody InventoryItem itemDetails) {
        InventoryItem updatedItem = inventoryService.updateItem(id, itemDetails);
        if (updatedItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/items")
    public List<InventoryItem> getAllItems() {
        return inventoryService.getAllItems();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<InventoryItem> getItem(@PathVariable Long id) {
        InventoryItem item = inventoryService.getItem(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @GetMapping("/stock")
    public ResponseEntity<Integer> getInventoryStock() {
        int stock = inventoryService.getInventoryStock();
        return ResponseEntity.ok(stock);
    }
}
