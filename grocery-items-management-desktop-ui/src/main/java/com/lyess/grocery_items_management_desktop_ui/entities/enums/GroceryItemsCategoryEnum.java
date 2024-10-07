package com.lyess.grocery_items_management_desktop_ui.entities.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-10-02
 */
public enum GroceryItemsCategoryEnum {
    SNACKS("snacks"),
    MILLETS("millets"),
    SPICES("spices"),
    UNKNOWN("unknown");

    private static final Map<String, GroceryItemsCategoryEnum> mapOfEnumsByValue = Arrays.stream(GroceryItemsCategoryEnum.values()).collect(Collectors.toMap(GroceryItemsCategoryEnum::getValue, entry -> entry));

    private static final Logger logger = LoggerFactory.getLogger(GroceryItemsCategoryEnum.class);

    private final String value;

    GroceryItemsCategoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GroceryItemsCategoryEnum getEnumByValue(String value) {

        if (value == null) {
            logger.trace("In getEnumByValue : the value is null !");
            throw new IllegalArgumentException("In getEnumByValue : the value is null !");
        }

        final GroceryItemsCategoryEnum result = mapOfEnumsByValue.get(value);

        if (result == null) {
            logger.trace("In getEnumByValue : the result is null !");
            throw new IllegalArgumentException("In getEnumByValue : the Enum of " + value + " is null !");
        }
        return result;
    }
}
