package com.example.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test.entity.InventoryItem;
import com.example.test.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryItem createItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public InventoryItem updateItem(Long id, InventoryItem itemDetails) {
        Optional<InventoryItem> optionalItem = inventoryRepository.findById(id);
        if (optionalItem.isPresent()) {
            InventoryItem item = optionalItem.get();
            item.setName(itemDetails.getName());
            item.setCategory(itemDetails.getCategory());
            item.setQuantity(itemDetails.getQuantity());
            item.setStatus(itemDetails.getStatus());
            return inventoryRepository.save(item);
        }
        return null;
    }

    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    public InventoryItem getItem(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public int getInventoryStock() {
        return inventoryRepository.findAll().stream().mapToInt(InventoryItem::getQuantity).sum();
    }
}
