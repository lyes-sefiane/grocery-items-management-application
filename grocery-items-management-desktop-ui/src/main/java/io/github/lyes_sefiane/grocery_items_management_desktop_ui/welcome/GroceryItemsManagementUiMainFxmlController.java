package io.github.lyes_sefiane.grocery_items_management_desktop_ui.welcome;

import com.jfoenix.controls.JFXTreeView;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.alert.AlertMessage;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.config.FxmlLoader;
import io.github.lyes_sefiane.grocery_items_management_desktop_ui.entities.enums.TreeItemEnum;
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
import java.util.Arrays;
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
        List<TreeItem<String>>  treeItems = Arrays.stream(TreeItemEnum.values())
                .filter(TreeItemEnum::isVisible)
                .map(treeItemEnum -> new TreeItem<>(treeItemEnum.getValue(),//
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(treeItemEnum.getIconEnum().getLocation())),//
                                treeItemEnum.getIconEnum().getWidth(),//
                                treeItemEnum.getIconEnum().getHeight(),//
                                treeItemEnum.getIconEnum().isPreserveRatio(),//
                                treeItemEnum.getIconEnum().isSmooth()))))
                .toList();
        root.getChildren().addAll(treeItems);
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
