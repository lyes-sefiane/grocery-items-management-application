package io.github.lyes_sefiane.grocery_items_management_desktop_ui.groceryitemsmanagement;

import io.github.lyes_sefiane.grocery_items_management_common.domain.dto.GroceryItemRecord;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.entities.enums.GroceryItemsCategoryEnum;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.util.Arrays;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-10-05
 */
abstract sealed class CreateOrUpdateGroceryItemFxmlController extends GroceryItemsManagementFxmlController permits CreateGroceryItemFxmlController, UpdateGroceryItemFxmlController {

    /**
     * Default Constructor
     */
    protected CreateOrUpdateGroceryItemFxmlController() {}

    /**
     * Populate Category ComboBox.
     *
     * @see GroceryItemsCategoryEnum
     */
    protected void initComoBox(ComboBox<GroceryItemsCategoryEnum> comboBox, GroceryItemsCategoryEnum[] values) {
        comboBox.setItems(FXCollections.observableList(Arrays.stream(values)
                .filter(groceryItemsCategoryEnum -> groceryItemsCategoryEnum != GroceryItemsCategoryEnum.UNKNOWN)
                .toList()));
    }

    /**
     * Spinner Initialization. Min: 0, Max: 100, Init: 1
     *
     * @param spinner: Javafx element to display the spinner information
     * @param minValue: spinner min value
     * @param maxValue: spinner max value
     * @param initValue: spinner init value
     */
    protected void initSpinner(Spinner<Integer> spinner, int minValue, int maxValue, int initValue) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue, initValue));
    }

    /**
     * Build Grocery Item Record Payload
     *
     * @param name: Item name
     * @param quantity: Item quantity
     * @param category: Item category
     *
     * @return groceryItemRecord
     */
    protected GroceryItemRecord buildGroceryItemRecord(String name, Integer quantity, String category) {
        return GroceryItemRecord.newBuilder()
                .setName(name)
                .setQuantity(quantity)
                .setCategory(category)
                .build();
    }
}
