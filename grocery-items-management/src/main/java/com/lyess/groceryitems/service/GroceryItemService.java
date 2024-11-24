package com.lyess.groceryitems.service;

import com.lyess.groceryitems.domain.entity.GroceryItem;
import com.lyess.groceryitems.repository.GroceryItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.service
 * Class : GroceryItemService.java
 * User : Lyes Sefiane
 * Created : 2023-09-24 1:46 PM
 */
@Service
public class GroceryItemService implements IGroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    @Autowired
    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    /**
     * Fnd All Grocery Items
     *
     * @return List Of GroceryItem
     * @see GroceryItem
     */
    @Cacheable(cacheNames = "groceryItems")
    @Override
    public List<GroceryItem> findAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    /**
     * Find Grocery Item By Id
     *
     * @return GroceryItem
     * @see GroceryItem
     */
    @Cacheable(cacheNames = "groceryItem", key = "#id")
    @Override
    public GroceryItem findGroceryItem(String id) {
        return findById(id);
    }

    /**
     * Save Grocery Item
     *
     * @param groceryItem : Item to save
     * @return groceryItem
     * @see GroceryItem
     */
    @Caching(put = @CachePut(cacheNames = "groceryItem", key = "#groceryItem.id"),
            evict = @CacheEvict(cacheNames = "groceryItems", allEntries = true))
    @Override
    public GroceryItem saveGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    /**
     * Update a Specific Grocery Item.
     *
     * @param groceryItem : Entity Model
     * @param id          : Grocery Item Identifier.
     * @return GroceryItem
     * @see GroceryItem
     */
    @Caching(put = @CachePut(cacheNames = "groceryItem", key = "#id"),
            evict = @CacheEvict(cacheNames = "groceryItems", allEntries = true))
    @Override
    public GroceryItem updateGroceryItem(GroceryItem groceryItem, String id) {
        GroceryItem existingGroceryItem = findById(id);
        BeanUtils.copyProperties(groceryItem, existingGroceryItem, "id");
        return saveGroceryItem(existingGroceryItem);
    }

    /**
     * Delete a Specific Grocery Item
     *
     * @param id : Grocery Item Identifier
     */
    @Caching(evict = { @CacheEvict(cacheNames = "groceryItems", allEntries = true),
            @CacheEvict(cacheNames = "groceryItem", key = "#id")})
    @Override
    public void deleteGroceryItem(String id) {
        groceryItemRepository.deleteById(id);
    }

    /**
     * Find By Id Common Code Line
     *
     * @param id : Grocery Item Identifier
     * @return GroceryItem
     * @see GroceryItem
     */
    public GroceryItem findById(String id) {
        return groceryItemRepository.findById(id).orElseThrow(io.github.lyes_sefiane.exception_response.NotFoundException::new);
    }
}
