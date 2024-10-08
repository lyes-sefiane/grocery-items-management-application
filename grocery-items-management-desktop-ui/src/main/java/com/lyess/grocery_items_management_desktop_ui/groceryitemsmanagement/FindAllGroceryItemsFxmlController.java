package com.lyess.grocery_items_management_desktop_ui.groceryitemsmanagement;


import com.lyess.grocery_items_management_common.domain.GroceryItemResource;
import com.lyess.grocery_items_management_desktop_ui.entities.CustomTableCell;
import com.lyess.grocery_items_management_desktop_ui.entities.enums.TableCellEnum;
import com.lyess.grocery_items_management_desktop_ui.entities.enums.TreeItemEnum;
import com.lyess.grocery_items_management_desktop_ui.service.GroceryItemsManagementService;
import com.lyess.grocery_items_management_desktop_ui.welcome.GroceryItemsManagementUiMainFxmlController;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-09-09
 */
@Component
public non-sealed class FindAllGroceryItemsFxmlController extends GroceryItemsManagementFxmlController {

    private final Logger logger = LoggerFactory.getLogger(FindAllGroceryItemsFxmlController.class);

    @FXML
    private TableView<GroceryItemResource> groceryItemsTableView;

    @FXML
    private TableColumn<GroceryItemResource, String> id;

    @FXML
    private TableColumn<GroceryItemResource, String> name;

    @FXML
    private TableColumn<GroceryItemResource, Number> qte;

    @FXML
    private TableColumn<GroceryItemResource, String>  category;

    @FXML
    private TableColumn<GroceryItemResource, Boolean>  edit;

    @FXML
    private TableColumn<GroceryItemResource, Boolean>  delete;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField filterTextField;

    private final GroceryItemsManagementService groceryItemsManagementService;

    private final ApplicationContext applicationContext;

    /**
     * See : <a href="https://blog.devops.dev/spring-cloud-gateway-oauth2-security-with-keycloak-jwt-tokens-and-securing-it-with-https-ssl-2166d8009531">...</a>
     * See : <a href="https://andifalk.gitbook.io/spring-cloud-gateway-workshop/hands-on-labs/lab3#step-1-extend-the-gateway-to-act-as-oauth-resource-server">...</a>
     */
    @Autowired
    public FindAllGroceryItemsFxmlController(GroceryItemsManagementService groceryItemsManagementService, ApplicationContext applicationContext) {
        this.groceryItemsManagementService = groceryItemsManagementService;
        this.applicationContext = applicationContext;
    }

    /**
     * FXML Initialization
     */
    @FXML
    public void initialize () {
        logger.info("In Initialize...");
        setCellValueFactoryTableView();
        initTableView();
        initFilteredTableRows();
        subscribeToGroceryItemResources();
    }


    /**
     * Set Cell Value Factory Table View
     */
    private void setCellValueFactoryTableView() {
        logger.info("In Set Cell Factory...");
        id.setCellValueFactory(groceryItem -> new SimpleStringProperty(groceryItem.getValue().getGroceryItem().getId().toString()));
        name.setCellValueFactory(groceryItem -> new SimpleStringProperty(groceryItem.getValue().getGroceryItem().getName().toString()));
        qte.setCellValueFactory(groceryItem -> new SimpleIntegerProperty(groceryItem.getValue().getGroceryItem().getQuantity()));
        category.setCellValueFactory(groceryItem -> new SimpleStringProperty(groceryItem.getValue().getGroceryItem().getCategory().toString()));
        edit.setCellFactory(groceryItemResourceEditTableColumn -> {
            CustomTableCell currentButtonCell = new CustomTableCell(TableCellEnum.EDIT);
            currentButtonCell.getButton().setOnAction(actionEvent -> editOnAction(currentButtonCell));
            return currentButtonCell;
        });
        delete.setCellFactory(groceryItemResourceEditTableColumn -> {
            CustomTableCell currentButtonCell = new CustomTableCell(TableCellEnum.DELETE);
            currentButtonCell.getButton().setOnAction(actionEvent -> deleteOnAction(currentButtonCell));
            return currentButtonCell;
        });
    }

    /**
     * Edit On Action
     *
     * @param customTableCell: TableCell<GroceryItemResource, Boolean>
     */
    private void editOnAction(CustomTableCell customTableCell) {
        GroceryItemResource groceryItemResource = getTableRowValue(customTableCell);
        logger.info("Editing Item : {}", groceryItemResource);
        applicationContext.getBean(GroceryItemsManagementUiMainFxmlController.class).loadAndDisplayUiBy(TreeItemEnum.UPDATE_GROCERY_ITEM);
        applicationContext.getBean(UpdateGroceryItemFxmlController.class).initialize(groceryItemResource);
    }

    /**
     * Delete On Action
     *
     * @param customTableCell: TableCell<GroceryItemResource, Boolean>
     */
    private void deleteOnAction(CustomTableCell customTableCell) {
        GroceryItemResource groceryItemResource = getTableRowValue(customTableCell);
        logger.info("Deleting Item : {}", groceryItemResource);
        groceryItemsManagementService
                .findOneAndDelete(groceryItemResource.getGroceryItem().getId().toString())
                .doOnSuccess(doOnDeleteSuccessConsumer)
                .doOnError(doOnErrorConsumer)
                .doFinally(signaltype -> doRefreshTableView(groceryItemsTableView, groceryItemResource))
                .subscribe();
    }

    /**
     * Insert On Action
     *
     * @param ignoredEvent: On Click
     */
    @FXML
    private void insertOnAction(ActionEvent ignoredEvent) {
        logger.info("Adding New Item...");
        applicationContext.getBean(GroceryItemsManagementUiMainFxmlController.class).loadAndDisplayUiBy(TreeItemEnum.ADD_GROCERY_ITEM);
    }

    /**
     * Refresh Table View after Row Deletion
     *
     * @param groceryItemsTableView: Displayed TableView
     * @param groceryItemResource: Table Row Data
     */
    private void doRefreshTableView(TableView<GroceryItemResource> groceryItemsTableView, GroceryItemResource groceryItemResource) {
        logger.info("Refreshing Table View ...");
        observableList.remove(groceryItemResource);
        groceryItemsTableView.refresh();
    }

    /**
     * Get Table View Row Value ~ GroceryItemResource
     *
     * @param customTableCell: TableCell<GroceryItemResource, Boolean>
     *
     * @return GroceryItemResource
     */
    private GroceryItemResource getTableRowValue(CustomTableCell customTableCell) {
        TableRow<GroceryItemResource> tableRow = customTableCell.getTableRow();
        return tableRow.getTableView().getItems().get(tableRow.getIndex());
    }


    /**
     * Init Table View
     */
    private void initTableView() {
        logger.info("In Init Table View...");
        observableList.clear();
        setTableViewItems(observableList);
    }

    /**
     * Initialize Table Rows Filter
     */
    private void initFilteredTableRows() {
        FilteredList<GroceryItemResource> filteredObservableList = new FilteredList<>(observableList);
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredObservableList.setPredicate(groceryItemResourcePredicate(newValue)));
        setTableViewItems(filteredObservableList);
    }

    /**
     * Grocery Item Resource Predicate
     *
     * @param text : Text Written In The Text Field
     *
     * @return Grocery Item Resource That Evaluates A Boolean Condition.
     */
    private Predicate<GroceryItemResource> groceryItemResourcePredicate(String text) {
        return groceryItemResource ->
                groceryItemResource.getGroceryItem().getId().toString().toLowerCase().contains(text.toLowerCase()) ||
                        groceryItemResource.getGroceryItem().getName().toString().toLowerCase().contains(text.toLowerCase()) ||
                        groceryItemResource.getGroceryItem().getCategory().toString().toLowerCase().contains(text.toLowerCase());
    }

    /**
     * Set Grocery Items In The Table View
     *
     * @param items : List of Grocery Item Resources
     */
    private void setTableViewItems(ObservableList<GroceryItemResource> items) {
        groceryItemsTableView.setItems(items);
    }

    /**
     * Subscribe To Grocery Item Resources
     */
    private void subscribeToGroceryItemResources() {
        logger.trace("In Subscribe To Grocery Item Resources ... ");
        groceryItemsManagementService//
                .findAll()//
                .doOnComplete(() -> Platform.runLater(() -> progressBar.setProgress(1.0)))
                .subscribe(groceryItemResourceConsumer, doOnErrorConsumer);
    }
}
