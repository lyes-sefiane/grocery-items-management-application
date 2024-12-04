package io.github.lyes_sefiane.grocery_items_management.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.dto
 * Class : GroceryItemRecord.java
 * User : Lyes Sefiane
 * Created : 2023-09-24 2:19 PM
 */
public record GroceryItemRecord(
        @NotBlank(message = "Item name should not be null or empty !") String name,
        @PositiveOrZero(message = "Item quantity should be positive")
        @Min(value = 0, message = "Item quantity Min value should be equal to zero.") int quantity,
        @NotBlank(message = "Item category not be null or empty !") String category) {
}
