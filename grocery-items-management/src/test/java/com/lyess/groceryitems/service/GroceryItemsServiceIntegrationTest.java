package com.lyess.groceryitems.service;

import com.lyess.groceryitems.domain.entity.GroceryItem;
import io.github.lyes_sefiane.exception_response.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.service
 * Class : GroceryItemsServiceIntegrationTest.java
 * User : Lyes Sefiane
 * Created : 2023-09-30 7:06 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroceryItemsServiceIntegrationTest {

    private final GroceryItemService groceryItemService;

    private GroceryItem groceryItem;

    @Autowired
    public GroceryItemsServiceIntegrationTest(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @BeforeEach
    void setUp() {
        groceryItem = new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks");
    }

    @Test
    @Order(1)
    @DisplayName("Save a Grocery Item")
    @Rollback(value = false)
    public void testSave() {
        GroceryItem savedGroceryItem = groceryItemService.saveGroceryItem(groceryItem);
        assertNotNull(savedGroceryItem);
        assertEquals(savedGroceryItem.getId(), groceryItem.getId());
        assertEquals(savedGroceryItem.getName(), groceryItem.getName());
        assertEquals(savedGroceryItem.getQuantity(), groceryItem.getQuantity());
        assertEquals(savedGroceryItem.getCategory(), groceryItem.getCategory());
    }

    @Test
    @Order(2)
    @DisplayName("Find All Grocery Items")
    public void testFindAll() {
        List<GroceryItem> groceryItems = groceryItemService.findAllGroceryItems();
        assertEquals(groceryItems.size(), 1);
    }

    @Test
    @Order(3)
    @DisplayName("Find A Grocery Item")
    public void testFindOne() {
        GroceryItem existingGroceryItem = groceryItemService.findById(groceryItem.getId());
        assertNotNull(existingGroceryItem);
        assertEquals(existingGroceryItem.getId(), groceryItem.getId());
        assertEquals(existingGroceryItem.getName(), groceryItem.getName());
        assertEquals(existingGroceryItem.getQuantity(), groceryItem.getQuantity());
        assertEquals(existingGroceryItem.getCategory(), groceryItem.getCategory());
    }

    @Test
    @Order(4)
    @DisplayName("Update A Grocery Item")
    public void testUpdate() {
        groceryItem.setQuantity(groceryItem.getQuantity() + 10);
        GroceryItem updatedGroceryItem = groceryItemService.updateGroceryItem(groceryItem, groceryItem.getId());
        assertNotNull(updatedGroceryItem);
        assertEquals(updatedGroceryItem.getId(), groceryItem.getId());
        assertEquals(updatedGroceryItem.getName(), groceryItem.getName());
        assertEquals(updatedGroceryItem.getQuantity(), groceryItem.getQuantity());
        assertEquals(updatedGroceryItem.getCategory(), groceryItem.getCategory());
    }

    @Test
    @Order(5)
    @DisplayName("Delete A Grocery Item")
    public void testDelete() {
        String id = "Whole Wheat";
        groceryItemService.deleteGroceryItem(id);
        assertThrows(NotFoundException.class, () -> groceryItemService.findById(id));
    }


}
