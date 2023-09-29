package com.lyess.groceryitems.service;

import com.lyess.groceryitems.domain.entity.GroceryItem;

import java.util.List;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.service
 * Class : IGroceryItemService.java
 * User : Lyes Sefiane
 * Created : 2023-09-24 1:51 PM
 */
public interface IGroceryItemService {

    List<GroceryItem> findAllGroceryItems();

    GroceryItem findGroceryItem(String id);

    GroceryItem saveGroceryItem(GroceryItem groceryItem);

    GroceryItem updateGroceryItem(GroceryItem groceryItem, String id);

    void deleteGroceryItem(String id);


}
