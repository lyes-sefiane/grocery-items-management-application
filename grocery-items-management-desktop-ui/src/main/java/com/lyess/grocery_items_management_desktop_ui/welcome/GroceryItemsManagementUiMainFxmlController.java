package com.lyess.grocery_items_management_desktop_ui.welcome;

import com.jfoenix.controls.JFXTreeView;
import com.lyess.grocery_items_management_desktop_ui.alert.AlertMessage;
import com.lyess.grocery_items_management_desktop_ui.config.FxmlLoader;
import com.lyess.grocery_items_management_desktop_ui.entities.enums.TreeItemEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    private final FxmlLoader fxmlLoader;

    public GroceryItemsManagementUiMainFxmlController(FxmlLoader fxmlLoader) {
        logger.info("In GroceryItemsManagementUiMainFxmlController");
        this.fxmlLoader = fxmlLoader;
    }

    @FXML
    public void initialize () {
        logger.info("In initialize");
        initTreeView();
        loadAndDisplayUiBy(TreeItemEnum.HOME);
    }

    /**
     * TreeView Initialization
     */
    private void initTreeView() {
        TreeItem<String> root = new TreeItem<>();
        TreeItem<String> home = new TreeItem<>(TreeItemEnum.HOME.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.HOME.getIcon())), 32, 32, false, false)));
        TreeItem<String> groceryItems = new TreeItem<>(TreeItemEnum.GROCERY_ITEMS_MANAGEMENT.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.GROCERY_ITEMS_MANAGEMENT.getIcon())), 28, 28, false, false)));
        TreeItem<String> swaggerUi = new TreeItem<>(TreeItemEnum.SWAGGER_UI.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.SWAGGER_UI.getIcon())), 32, 32, false, false)));
        TreeItem<String> zipkinUi = new TreeItem<>(TreeItemEnum.ZIPKIN_UI.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.ZIPKIN_UI.getIcon())), 32, 32, false, false)));
        TreeItem<String> githubDocumentation = new TreeItem<>(TreeItemEnum.GITHUB_DOCUMENTATION.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.GITHUB_DOCUMENTATION.getIcon())), 32, 32, false, false)));
        TreeItem<String> license = new TreeItem<>(TreeItemEnum.LICENSE_ITEM.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.LICENSE_ITEM.getIcon())), 32, 32, false, false)));
        TreeItem<String> aboutMe = new TreeItem<>(TreeItemEnum.ABOUT_ME_ITEM.getValue(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(TreeItemEnum.ABOUT_ME_ITEM.getIcon())), 32, 32, false, false)));
        root.getChildren().addAll(List.of(home, groceryItems, swaggerUi, zipkinUi, githubDocumentation, license, aboutMe));
        groceryItemsManagementTreeView.setRoot(root);
        groceryItemsManagementTreeView.setVisible(true);
    }

    /**
     * Tree Item on Acton
     *
     * @param ignoredEvent : Mouse Event On Click
     */
    @FXML
    private void groceryItemsManagementTreeViewOnClick(MouseEvent ignoredEvent) {
        TreeItem<String> selectedItem = groceryItemsManagementTreeView.getSelectionModel().getSelectedItem();
        TreeItemEnum selectedTreeItemEnum = TreeItemEnum.getEnumByValue(selectedItem.getValue());
        logger.info("Selected Tree Item : {}", selectedTreeItemEnum.getValue());
        loadAndDisplayUiBy(selectedTreeItemEnum);
    }

    /**
     * Quit On Action
     *
     * @param ignoredEvent : Action Event On Action
     */
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
            subFxmlContainerVBox.getChildren().addAll(fxmlLoader.load(getClass().getResource(treeItemEnum.getFxml())).getChildrenUnmodifiable());
        } catch (IOException e) {
            Platform.runLater(() -> new AlertMessage(Alert.AlertType.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));
        }
    }
}
