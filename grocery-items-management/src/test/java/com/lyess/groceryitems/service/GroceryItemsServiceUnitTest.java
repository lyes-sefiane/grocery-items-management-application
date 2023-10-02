package com.lyess.groceryitems.service;

import com.lyess.groceryitems.domain.entity.GroceryItem;
import com.lyess.groceryitems.repository.GroceryItemRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.service
 * Class : GroceryItemsServiceUnitTest.java
 * User : Lyes Sefiane
 * Created : 2023-09-30 1:17 PM
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroceryItemsServiceUnitTest {
    @Mock
    private GroceryItemRepository groceryItemRepository;

    @InjectMocks
    private GroceryItemService groceryItemService;

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
        // Given a groceryItem
        // When
        when(groceryItemRepository.save(any(GroceryItem.class))).thenReturn(groceryItem);
        // Then
        GroceryItem savedGroceryItem = groceryItemService.saveGroceryItem(groceryItem);
        assertEquals(savedGroceryItem, groceryItem);
    }

    @Test
    @Order(2)
    @DisplayName("Find All Grocery Items")
    public void testFindAll() {
        // Given
        // When
        when(groceryItemRepository.findAll()).thenReturn(List.of(groceryItem));
        // Then
        List<GroceryItem> groceryItems = groceryItemService.findAllGroceryItems();
        assertEquals(groceryItems.size(), 1);
    }

    @Test
    @Order(3)
    @DisplayName("Find A Grocery Item")
    public void testFindOne() {
        // Given
        // When
        when(groceryItemRepository.findById("Whole Wheat Biscuit")).thenReturn(Optional.of(groceryItem));
        // Then
        GroceryItem existingGroceryItems = groceryItemService.findById("Whole Wheat Biscuit");
        assertEquals(existingGroceryItems, groceryItem);
    }

    @Test
    @Order(4)
    @DisplayName("Update A Grocery Item")
    public void testUpdate() {
        // Given
        groceryItem.setCategory(groceryItem.getCategory() + 10);
        // When
        when(groceryItemRepository.save(groceryItem)).thenReturn(groceryItem);
        when(groceryItemRepository.findById("Whole Wheat Biscuit")).thenReturn(Optional.of(groceryItem));
        // Then
        GroceryItem updatedGroceryItem = groceryItemService.updateGroceryItem(groceryItem, "Whole Wheat Biscuit");
        assertEquals(updatedGroceryItem, groceryItem);
    }

    @Test
    @Order(5)
    @DisplayName("Delete A Grocery Item")
    public void testDelete() {
        // Given
        // When
        doNothing().when(groceryItemRepository).deleteById(groceryItem.getId());
        // Then
        groceryItemService.deleteGroceryItem(groceryItem.getId());
        verify(groceryItemRepository, times(1)).deleteById(groceryItem.getId());
    }
}
