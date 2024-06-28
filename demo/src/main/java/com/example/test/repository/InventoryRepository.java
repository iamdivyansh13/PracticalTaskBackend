package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test.entity.InventoryItem;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
