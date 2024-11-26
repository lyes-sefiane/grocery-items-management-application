package io.github.lyes_sefiane.grocery_items_management_desktop_ui.entities.enums;

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

    HOME("Home", "/fxml/welcome/welcome.fxml", IconEnum.HOME, true),
    GROCERY_ITEMS_MANAGEMENT("Grocery Items", "/fxml/groceryitemsmanagement/findgroceryItems.fxml", IconEnum.ITEMS, true),
    FIND_GROCERY_ITEMS("Find Grocery Items", "/fxml/groceryitemsmanagement/findgroceryItems.fxml", IconEnum.UNSUPPORTED, false),
    ADD_GROCERY_ITEM("Add Grocery Item", "/fxml/groceryitemsmanagement/creategroceryItem.fxml",  IconEnum.UNSUPPORTED, false),
    UPDATE_GROCERY_ITEM("Update Grocery Item", "/fxml/groceryitemsmanagement/updategroceryItem.fxml", IconEnum.UNSUPPORTED, false),
    SWAGGER_UI("Swagger", "/fxml/swagger/swagger.fxml", IconEnum.SWAGGER, true),
    ZIPKIN_UI("Zipkin", "/fxml/zipkin/zipkin.fxml", IconEnum.ZIPKIN, true),
    GITHUB_DOCUMENTATION("Documentation", "/fxml/github/github.fxml", IconEnum.GITHUB, true),
    LICENSE_ITEM("License", "/fxml/license/license.fxml", IconEnum.LICENSE, true),
    ABOUT_ME_ITEM("About Me", "/fxml/about/about.fxml", IconEnum.ABOUT_ME, true);


    private final String value;

    private final String fxml;

    private final IconEnum iconEnum;

    private final boolean isVisible;

    private static final Logger log = LoggerFactory.getLogger(TreeItemEnum.class);

    private static final Map<String, TreeItemEnum> mapOfEnumsByValue = Arrays.stream(TreeItemEnum.values()).collect(Collectors.toMap(TreeItemEnum::getValue, entry -> entry));

    TreeItemEnum(String value, String fxml, IconEnum iconEnum, boolean isVisible) {
        this.value = value;
        this.fxml = fxml;
        this.iconEnum = iconEnum;
        this.isVisible = isVisible;
    }

    public String getValue() {
        return value;
    }

    public String getFxml() {
        return fxml;
    }

    public IconEnum getIconEnum() {
        return iconEnum;
    }

    public boolean isVisible() {
        return isVisible;
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
