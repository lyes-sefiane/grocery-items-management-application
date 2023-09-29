package com.lyess.groceryitems.domain.dto;

import com.lyess.groceryitems.controller.GroceryItemRestController;
import com.lyess.groceryitems.domain.entity.GroceryItem;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.domain.dto
 * Class : GroceryItemResource.java
 * User : Lyes Sefiane
 * Created : 2023-09-27 10:18 AM
 */
public class GroceryItemResource extends RepresentationModel<GroceryItemResource> {

    private final GroceryItem groceryItem;

    public GroceryItemResource(GroceryItem groceryItem) {
        this.groceryItem = groceryItem;
        this.addLink(groceryItem);
    }

    /**
     * Add Hateoas Links
     */
    private void addLink(GroceryItem groceryItem) {
        this.add(linkTo(methodOn(GroceryItemRestController.class)
                .findGroceryItem(groceryItem.getId()))
                .withSelfRel());
    }

    /**
     * Get Grocery Item
     *
     * @return groceryItem
     */
    public GroceryItem getGroceryItem() {
        return groceryItem;
    }

}
