package com.lyess.groceryitems.controller;

import com.lyess.groceryitems.domain.dto.GroceryItemRecord;
import com.lyess.groceryitems.domain.dto.GroceryItemResource;
import com.lyess.groceryitems.domain.entity.GroceryItem;
import com.lyess.groceryitems.service.IGroceryItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.controller
 * Class : GroceryItemRestController.java
 * User : Lyes Sefiane
 * Created : 2023-09-24 1:14 PM
 */
@RestController
@RequestMapping("/v1/items")
@Validated
public class GroceryItemRestController {

    private final IGroceryItemService groceryItemService;

    private final Converter<GroceryItemRecord, GroceryItem> customConverter;

    @Autowired
    public GroceryItemRestController(IGroceryItemService groceryItemService, Converter<GroceryItemRecord, GroceryItem> customConverter) {
        this.groceryItemService = groceryItemService;
        this.customConverter = customConverter;
    }

    /**
     * Find All Grocery Items In The Database.
     *
     * @return List of Grocery Item.
     * @see GroceryItemResource
     */
    @GetMapping
    public List<GroceryItemResource> findAllGroceryItems() {
        return groceryItemService.findAllGroceryItems().stream().map(GroceryItemResource::new).collect(Collectors.toList());
    }

    /**
     * Find a Specific Grocery Item In The Database.
     *
     * @param id : String
     * @return Grocery Item Resource Found In The Database or Throw an Exception.
     * @see GroceryItem
     * @see GroceryItemResource
     */
    @RequestMapping("/{id}")
    public GroceryItemResource findGroceryItem(@PathVariable @NotBlank(message = "Grocery Item Should Not Be Blank !") String id) {
        GroceryItem groceryItem = groceryItemService.findGroceryItem(id);
        return new GroceryItemResource(groceryItem);
    }

    /**
     * Save Grocery Item Into The Database.
     *
     * @param groceryItemRecord : Data Transfer Object.
     * @return Grocery Item Resource Saved In The Database
     * @see GroceryItemRecord
     * @see GroceryItemResource
     */
    @PostMapping
    public GroceryItemResource saveGroceryItem(@RequestBody @Valid GroceryItemRecord groceryItemRecord) {
        GroceryItem groceryItem = groceryItemService.saveGroceryItem(customConverter.convert(groceryItemRecord));
        return new GroceryItemResource(groceryItem);
    }

    /**
     * Update An Existing Grocery Item In The Database.
     *
     * @param groceryItemRecord : Data Transfer Object
     * @param id                : Grocery Item Identifier
     * @return Updated Grocery Item Resource
     * @see GroceryItemResource
     * @see GroceryItemRecord
     */
    @PutMapping("/{id}")
    public GroceryItemResource updateGroceryItem(@RequestBody @Valid GroceryItemRecord groceryItemRecord, //
                                                 @PathVariable @NotBlank(message = "Grocery Id should Not Be Blank!") String id) {
        GroceryItem groceryItem = groceryItemService.updateGroceryItem(customConverter.convert(groceryItemRecord), id);
        return new GroceryItemResource(groceryItem);
    }

    /**
     * Delete A Specific Grocery Item In The Database.
     *
     * @param id : Grocery Item Identifier.
     */
    @DeleteMapping("/{id}")
    public void deleteGroceryItem(@PathVariable @NotBlank(message = "Grocery Id should Not Be Blank!") String id) {
        groceryItemService.deleteGroceryItem(id);
    }


}
