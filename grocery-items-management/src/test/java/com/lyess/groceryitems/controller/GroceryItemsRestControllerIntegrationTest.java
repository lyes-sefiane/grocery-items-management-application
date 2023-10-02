package com.lyess.groceryitems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyess.groceryitems.domain.dto.GroceryItemRecord;
import com.lyess.groceryitems.domain.entity.GroceryItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.controller
 * Class : GroceryItemsRestControllerIntegrationTest.java
 * User : Lyes Sefiane
 * Created : 2023-10-01 1:55 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class GroceryItemsRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private GroceryItem groceryItem;

    @BeforeEach
    void setUp() {
        groceryItem = new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks");
    }

    @Test
    @Order(1)
    @DisplayName("Save a Grocery Item")
    @Rollback(value = false)
    public void testSave() throws Exception {
        mockMvc.perform(post("/v1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new GroceryItemRecord(groceryItem.getName(), groceryItem.getQuantity(), groceryItem.getCategory()))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groceryItem.id").value(groceryItem.getId()))
                .andExpect(jsonPath("$.groceryItem.name").value(groceryItem.getName()))
                .andExpect(jsonPath("$.groceryItem.quantity").value(groceryItem.getQuantity()))
                .andExpect(jsonPath("$.groceryItem.category").value(groceryItem.getCategory()));
    }

    @Test
    @Order(2)
    @DisplayName("Find All Grocery Items")
    public void testFindAll() throws Exception {
        // Given
        // When
        // Then
        mockMvc.perform(get("/v1/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }

    @Test
    @Order(3)
    @DisplayName("Find A Grocery Item")
    public void testFindOne() throws Exception {
        // Given
        String id = groceryItem.getId();
        // When
        // Then
        mockMvc.perform(get("/v1/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groceryItem.id").value(groceryItem.getId()))
                .andExpect(jsonPath("$.groceryItem.name").value(groceryItem.getName()))
                .andExpect(jsonPath("$.groceryItem.quantity").value(groceryItem.getQuantity()))
                .andExpect(jsonPath("$.groceryItem.category").value(groceryItem.getCategory()));
    }

    @Test
    @Order(4)
    @DisplayName("Update A Grocery Item")
    public void testUpdate() throws Exception {
        // Given
        String id = groceryItem.getId();
        groceryItem.setCategory(groceryItem.getCategory() + 10);
        // When
        // Then
        mockMvc.perform(put("/v1/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new GroceryItemRecord(groceryItem.getName(), groceryItem.getQuantity(), groceryItem.getCategory()))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groceryItem.id").value(groceryItem.getId()))
                .andExpect(jsonPath("$.groceryItem.name").value(groceryItem.getName()))
                .andExpect(jsonPath("$.groceryItem.quantity").value(groceryItem.getQuantity()))
                .andExpect(jsonPath("$.groceryItem.category").value(groceryItem.getCategory()));
    }

    @Test
    @Order(5)
    @DisplayName("Delete A Grocery Item")
    public void testDelete() throws Exception {
        // given
        String id = groceryItem.getId();
        // when
        // then
        mockMvc.perform(delete("/v1/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
