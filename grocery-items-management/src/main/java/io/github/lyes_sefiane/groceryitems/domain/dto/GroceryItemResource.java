package io.github.lyes_sefiane.groceryitems.domain.dto;

import io.github.lyes_sefiane.groceryitems.domain.entity.GroceryItem;
import org.springframework.hateoas.Link;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.domain.dto
 * Class : GroceryItemResource.java
 * User : Lyes Sefiane
 * Created : 2023-09-27 10:18 AM
 */
public record GroceryItemResource(GroceryItem groceryItem, Link link) {}
