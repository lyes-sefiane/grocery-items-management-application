package io.github.lyes_sefiane.groceryitems.repository;

import io.github.lyes_sefiane.groceryitems.domain.entity.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.repository
 * Class : GroceryItemRepository.java
 * User : Lyes Sefiane
 * Created : 2023-09-24 1:47 PM
 */
@Repository
public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

}
