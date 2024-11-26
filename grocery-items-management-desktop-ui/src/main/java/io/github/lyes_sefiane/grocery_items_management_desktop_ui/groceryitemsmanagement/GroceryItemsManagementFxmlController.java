package io.github.lyes_sefiane.grocery_items_management_desktop_ui.groceryitemsmanagement;

import io.github.lyes_sefiane.grocery_items_management_common.domain.GroceryItemResource;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.alert.AlertMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.util.function.Consumer;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-10-02
 */
abstract sealed class GroceryItemsManagementFxmlController permits CreateOrUpdateGroceryItemFxmlController, FindAllGroceryItemsFxmlController {

    private final Logger logger = LoggerFactory.getLogger(GroceryItemsManagementFxmlController.class);

    protected final ObservableList<GroceryItemResource> observableList = FXCollections.observableArrayList();

    @Value("${alert.register.message}")
    private String alertRegisterMessage;

    @Value("${alert.update.message}")
    private String alertUpdateMessage;

    @Value("${alert.delete.message}")
    private String alertDeleteMessage;

    protected GroceryItemsManagementFxmlController() {}

    /**
     * Grocery Item Resource Consumer
     */
    protected final Consumer<GroceryItemResource> groceryItemResourceConsumer = groceryItemResource -> {
        logger.info("Consuming Grocery Item Resource : {}", groceryItemResource);
        Platform.runLater(() -> observableList.add(groceryItemResource));
    };

    /**
     * Error Handling Consumer
     */
    protected final Consumer<Throwable> doOnErrorConsumer = throwable -> {
        logger.info("Exception Occurred While Retrieving Data : {}", throwable.getMessage());
        Platform.runLater(() -> new AlertMessage(Alert.AlertType.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), throwable.getMessage()));
    };

    /**
     * Display a Informative Alert Message following the item registration
     */
    protected final Consumer<GroceryItemResource> doOnCreateSuccessConsumer = groceryItemResource -> {
        logger.info("Successfully Registered : {}", groceryItemResource);
        Platform.runLater(() -> new AlertMessage(Alert.AlertType.INFORMATION, HttpStatus.CREATED.toString(), alertRegisterMessage + groceryItemResource.getGroceryItem().getId()));
    };

    /**
     * Display a Informative Alert Message following the item update
     */
    protected final Consumer<GroceryItemResource> doOnUpdateSuccessConsumer = groceryItemResource -> {
        logger.info("Successfully Updated : {}", groceryItemResource);
        Platform.runLater(() -> new AlertMessage(Alert.AlertType.INFORMATION, HttpStatus.OK.toString(), alertUpdateMessage + groceryItemResource.getGroceryItem().getId()));
    };

    /**
     * Display a Informative Alert Message following the item deletion
     */
    protected final Consumer<Void> doOnDeleteSuccessConsumer = aVoid -> {
        logger.info("Successfully Deleted");
        Platform.runLater(() -> new AlertMessage(Alert.AlertType.INFORMATION, HttpStatus.OK.toString(), alertDeleteMessage));
    };


}
