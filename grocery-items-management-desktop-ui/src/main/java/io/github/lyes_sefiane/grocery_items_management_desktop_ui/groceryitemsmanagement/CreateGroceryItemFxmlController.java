package io.github.lyes_sefiane.grocery_items_management_desktop_ui.groceryitemsmanagement;

import io.github.lyes_sefiane.grocery_items_management_common.domain.dto.GroceryItemRecord;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.entities.enums.GroceryItemsCategoryEnum;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.service.IGroceryItemsManagementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-10-02
 */
@Component
public non-sealed class CreateGroceryItemFxmlController extends CreateOrUpdateGroceryItemFxmlController  {

    private final Logger logger = LoggerFactory.getLogger(CreateGroceryItemFxmlController.class);

    @FXML
    private TextField nameTextField;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private ComboBox<GroceryItemsCategoryEnum> categoryCombobox;

    private final IGroceryItemsManagementService groceryItemsManagementService;


    @Autowired
    public CreateGroceryItemFxmlController(IGroceryItemsManagementService groceryItemsManagementService) {
        this.groceryItemsManagementService = groceryItemsManagementService;
    }

    @FXML
    public void initialize () {
        initSpinner(quantitySpinner, 0, 100, 1);
        initComoBox(categoryCombobox, GroceryItemsCategoryEnum.values());
    }

    /**
     * Register On Action
     *
     * @param ignoredEvent: Not used so far.
     */
    @FXML
    private void registerOnAction(ActionEvent ignoredEvent) {

        GroceryItemRecord groceryItemRecord = buildGroceryItemRecord(nameTextField.getText(),
                quantitySpinner.getValue(),
                categoryCombobox.getSelectionModel().getSelectedItem().getValue());

        logger.info("Item To Register : {}", groceryItemRecord);

        groceryItemsManagementService
                .insertOne(groceryItemRecord)
                .doOnSuccess(doOnCreateSuccessConsumer)
                .subscribe(groceryItemResourceConsumer, doOnErrorConsumer);
    }

}
