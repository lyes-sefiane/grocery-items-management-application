package io.github.lyes_sefiane.grocery_items_management_desktop_ui.groceryitemsmanagement;

import io.github.lyes_sefiane.grocery_items_management_common.domain.GroceryItemResource;
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
 * @since 2024-10-05
 */
@Component
public non-sealed class UpdateGroceryItemFxmlController extends CreateOrUpdateGroceryItemFxmlController {

    private final Logger logger = LoggerFactory.getLogger(UpdateGroceryItemFxmlController.class);

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private ComboBox<GroceryItemsCategoryEnum> categoryCombobox;

    private final IGroceryItemsManagementService groceryItemsManagementService;

    @Autowired
    public UpdateGroceryItemFxmlController(IGroceryItemsManagementService groceryItemsManagementService) {
        this.groceryItemsManagementService = groceryItemsManagementService;
    }

    @FXML
    public void initialize (GroceryItemResource groceryItemResource) {
        logger.info("Item to edit {}", groceryItemResource.getGroceryItem());
        initSpinner(quantitySpinner, 0, 100, 1);
        initComoBox(categoryCombobox, GroceryItemsCategoryEnum.values());
        initEditForm(groceryItemResource);
    }

    /**
     * Edit Form Initialization
     *
     * @param groceryItemResource: Entity to edit
     */
    private void initEditForm(GroceryItemResource groceryItemResource) {
        idTextField.setText(groceryItemResource.getGroceryItem().getId().toString());
        nameTextField.setText(groceryItemResource.getGroceryItem().getName().toString());
        quantitySpinner.getValueFactory().setValue(groceryItemResource.getGroceryItem().getQuantity());
        categoryCombobox.getSelectionModel().select(GroceryItemsCategoryEnum.getEnumByValue(groceryItemResource.getGroceryItem().getCategory().toString()));
    }

    @FXML
    private void saveOnAction(ActionEvent ignoredEvent) {

        GroceryItemRecord groceryItemRecord = buildGroceryItemRecord(nameTextField.getText(),
                quantitySpinner.getValue(),
                categoryCombobox.getSelectionModel().getSelectedItem().getValue());

        logger.info("Item To Update : {}, {}", idTextField.getText(), groceryItemRecord);

        groceryItemsManagementService
                .findOneAndUpdate(idTextField.getText(), groceryItemRecord)
                .doOnSuccess(doOnUpdateSuccessConsumer)
                .doOnError(doOnErrorConsumer)
                .subscribe();
    }
}
