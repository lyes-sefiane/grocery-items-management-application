package com.lyess.grocery_items_management_desktop_ui.welcome;

import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-09-06
 */
@Component
public class GroceryItemsManagementUiMainFxmlController {

    Logger logger = LoggerFactory.getLogger(GroceryItemsManagementUiMainFxmlController.class);

    @FXML
    private TreeView<String> operationsTreeView;

    public GroceryItemsManagementUiMainFxmlController() {
        logger.info("In GroceryItemsManagementUiMainFxmlController");
    }

    @FXML
    public void initialize () {
        logger.info("In initialize");
    }



}
