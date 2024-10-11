package com.lyess.grocery_items_management_desktop_ui.entities.enums;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-09-07
 */
public enum TreeItemEnum {

    HOME("Home", "/fxml/welcome/welcome.fxml", "/images/home-icon.png"),
    GROCERY_ITEMS_MANAGEMENT("Grocery Items", "/fxml/groceryitemsmanagement/findgroceryItems.fxml", "/images/items-icon.png"),
    FIND_GROCERY_ITEMS("Find Grocery Items", "/fxml/groceryitemsmanagement/findgroceryItems.fxml", StringUtils.EMPTY),
    ADD_GROCERY_ITEM("Add Grocery Item", "/fxml/groceryitemsmanagement/creategroceryItem.fxml",  StringUtils.EMPTY),
    UPDATE_GROCERY_ITEM("Update Grocery Item", "/fxml/groceryitemsmanagement/updategroceryItem.fxml",  StringUtils.EMPTY),
    SWAGGER_UI("Swagger UI", "/fxml/swagger/swagger.fxml", "/images/swagger-icon.png"),
    LICENSE_ITEM("License", "/fxml/license/license.fxml", "/images/license-icon.png"),
    ABOUT_ME_ITEM("About Me", "/fxml/about/about.fxml", "/images/about-me-icon.png");


    private final String value;

    private final String fxml;

    private final String icon;

    private static final Logger log = LoggerFactory.getLogger(TreeItemEnum.class);

    private static final Map<String, TreeItemEnum> mapOfEnumsByValue = Arrays.stream(TreeItemEnum.values()).collect(Collectors.toMap(TreeItemEnum::getValue, entry -> entry));

    TreeItemEnum(String value, String fxml, String icon) {
        this.value = value;
        this.fxml = fxml;
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public String getFxml() {
        return fxml;
    }

    public String getIcon() {
        return icon;
    }

    public static TreeItemEnum getEnumByValue(String value) {

        if (value == null) {
            log.trace("In getEnumByValue : the value is null !");
            throw new IllegalArgumentException("In getEnumByValue : the value is null !");
        }

        final TreeItemEnum result = mapOfEnumsByValue.get(value);

        if (result == null) {
            log.trace("In getEnumByValue : the result is null !");
            throw new IllegalArgumentException("In getEnumByValue : the Enum of " + value + " is null !");
        }
        return result;
    }
}
