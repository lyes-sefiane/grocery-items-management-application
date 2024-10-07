package com.lyess.grocery_items_management_desktop_ui.welcome;

import com.jfoenix.controls.JFXTreeView;
import com.lyess.grocery_items_management_desktop_ui.alert.AlertMessage;
import com.lyess.grocery_items_management_desktop_ui.entities.enums.TreeItemEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;




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
    private JFXTreeView<String> groceryItemsManagementTreeView;

    @FXML
    private VBox subFxmlContainerVBox;

    private final ApplicationContext applicationContext;

    public GroceryItemsManagementUiMainFxmlController(ApplicationContext applicationContext) {
        logger.info("In GroceryItemsManagementUiMainFxmlController");
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize () {
        logger.info("In initialize");
        initTreeView();
        loadAndDisplayUiBy(TreeItemEnum.HOME);
    }

    /**
     *
     */
    private void initTreeView() {
        TreeItem<String> root = new TreeItem<>();
        TreeItem<String> home = new TreeItem<>(TreeItemEnum.HOME.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.HOME.getIcon())), 30, 30, false, false)));
        TreeItem<String> groceryItems = new TreeItem<>(TreeItemEnum.GROCERY_ITEMS_MANAGEMENT.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.GROCERY_ITEMS_MANAGEMENT.getIcon())), 30, 30, false, false)));
        TreeItem<String> license = new TreeItem<>(TreeItemEnum.LICENSE_ITEM.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.LICENSE_ITEM.getIcon())), 30, 30, false, false)));
        TreeItem<String> aboutMe = new TreeItem<>(TreeItemEnum.ABOUT_ME_ITEM.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.ABOUT_ME_ITEM.getIcon())), 30, 30, false, false)));
        root.getChildren().addAll(List.of(home, groceryItems, license, aboutMe));
        groceryItemsManagementTreeView.setRoot(root);
        groceryItemsManagementTreeView.setVisible(true);
    }

    @FXML
    private void groceryItemsManagementTreeViewOnClick(MouseEvent ignoredEvent) {
        TreeItem<String> selectedItem = groceryItemsManagementTreeView.getSelectionModel().getSelectedItem();
        TreeItemEnum selectedTreeItemEnum = TreeItemEnum.getEnumByValue(selectedItem.getValue());
        logger.info("Selected Tree Item : {}", selectedTreeItemEnum.getValue());
        loadAndDisplayUiBy(selectedTreeItemEnum);
    }

    @FXML
    private void quitOnAction(ActionEvent ignoredEvent) {
        logger.info("Quit ....");
        Platform.exit();
    }


    /**
     * Load And Display Sub FXML
     *
     * @param treeItemEnum: Selected Tree Item's Enum Related
     */
    public void loadAndDisplayUiBy(TreeItemEnum treeItemEnum) {
        try {
            subFxmlContainerVBox.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(treeItemEnum.getFxml()));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlLoader.load();
            subFxmlContainerVBox.getChildren().addAll(parent.getChildrenUnmodifiable());
        } catch (IOException e) {
            Platform.runLater(() -> new AlertMessage(Alert.AlertType.ERROR, "Exception", "Message : " + e.getMessage()));
        }


    }
}
