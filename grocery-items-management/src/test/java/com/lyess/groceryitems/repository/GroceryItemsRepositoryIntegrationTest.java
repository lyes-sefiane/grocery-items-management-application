package com.lyess.groceryitems.repository;

import com.lyess.groceryitems.domain.entity.GroceryItem;
import com.lyess.groceryitems.exception.GroceryItemNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.repository
 * Class : GroceryItemsRepositoryIntegrationTest.java
 * User : Lyes Sefiane
 * Created : 2023-09-30 9:33 AM
 */
@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroceryItemsRepositoryIntegrationTest {
    @Autowired
    private GroceryItemRepository groceryItemRepository;
    
    private GroceryItem groceryItem;

    @BeforeEach
    void setUp() {
        groceryItem = new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks");
    }

    @Test
    @Order(1)
    @DisplayName("Save a Grocery Item")
    @Rollback(value = false)
    public void testSave() {
        // Given groceryItem defined in the setUp method.
        // When
        GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
        // Then
        assertEquals(savedGroceryItem, groceryItem);
    }

    @Test
    @Order(2)
    @DisplayName("Find All Grocery Items")
    public void testFindAll() {
        // Given groceryItem saved in the repository
        // When
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        // Then
        assertEquals(groceryItems.size(), 1);
    }

    @Test
    @Order(3)
    @DisplayName("Find A Grocery Item")
    public void testFindOne() {
        // Given groceryItem saved in the repository
        // When
        GroceryItem existingGroceryItem = groceryItemRepository.findById("Whole Wheat Biscuit").orElseThrow(GroceryItemNotFoundException::new);
        // Then
        assertTrue(existingGroceryItem.getId().equalsIgnoreCase("Whole Wheat Biscuit") && //
                existingGroceryItem.getName().equalsIgnoreCase("Whole Wheat Biscuit") && //
                existingGroceryItem.getQuantity() == 5 && //
                existingGroceryItem.getCategory().equalsIgnoreCase("snacks"));
    }

    @Test
    @Order(4)
    @DisplayName("Update A Grocery Item")
    public void testUpdate() {
        // Given groceryItem saved in the repository
        groceryItem.setQuantity(groceryItem.getQuantity() + 2);
        // When
        GroceryItem updatedGroceryItem = groceryItemRepository.save(groceryItem);
        // Then
        assertEquals(updatedGroceryItem.getQuantity(), 7);
    }

    @Test
    @Order(5)
    @DisplayName("Delete A Grocery Item")
    public void testDelete() {
        // Given the groceryItem saved in the repository
        // When
        groceryItemRepository.deleteById("Whole Wheat Biscuit");
        // Then
        assertEquals(groceryItemRepository.count(), 0);
    }
}
