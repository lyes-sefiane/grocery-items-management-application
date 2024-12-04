package io.github.lyes_sefiane.grocery_items_management.exception;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.exception
 * Class : GroceryItemNotFoundException.java
 * User : Lyes Sefiane
 * Created : 2023-09-27 1:24 PM
 */
public class GroceryItemNotFoundException extends RuntimeException {

    public GroceryItemNotFoundException() {
        //
    }

    /**
     * Grocery Item Not Found Exception
     *
     * @param message : Exception Custom Message
     */
    public GroceryItemNotFoundException(String message) {
        super(message);
    }
}
