package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Model.Inventory;
import Model.Part;
import Model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that provides control logic for the add product screen of the application.
 *
 * @author Matt Goldstine
 */
public class AddProductController implements Initializable {

    /**
     * A list containing parts associated with the product.
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * The associated parts table view.
     */
    @FXML
    private TableView<Part> assocPartTableView;

    /**
     * The part ID column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartIdColumn;

    /**
     * The part name column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, String> assocPartNameColumn;

    /**
     * The inventory level column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartInventoryColumn;

    /**
     * The price column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> assocPartPriceColumn;

    /**
     * The all parts table view.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * The part ID column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * The name column for the all parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * The inventory level column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     * The price column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * The part search text field.
     */
    @FXML
    private TextField partSearchInput;

    /**
     * The product ID text field.
     */
    @FXML
    private TextField idInput;

    /**
     * The product name text field.
     */
    @FXML
    private TextField nameInput;

    /**
     * The product inventory level text field.
     */
    @FXML
    private TextField inventoryInput;

    /**
     * The product price text field.
     */
    @FXML
    private TextField priceInput;

    /**
     * The product maximum level text field.
     */
    @FXML
    private TextField maxInput;

    /**
     * The product minimum level text field.
     */
    @FXML
    private TextField minInput;

    /**
     * Adds part object selected in the all parts table to the associated parts table.
     *
     * Displays error message if no part is selected.
     *
     * @param event Add button action.
     */
    @FXML
    void addButtonAction(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alerts.error(Alerts.Errors.PART_NOT_SELECTED);
        } else {
            assocParts.add(selectedPart);
            assocPartTableView.setItems(assocParts);
        }
    }

    /**
     * Displays confirmation dialog and loads MainScreenController.
     *
     * @param event Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainPage(event);
        }
    }

    /**
     * Initiates a search based on value in parts search text field and refreshes the parts
     * table view with search results.
     *
     * Parts can be searched for by ID or name.
     *
     * @param event Part search button action.
     */
    @FXML
    void partSearchBtnAction(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearchInput.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alerts.error(Alerts.Errors.PRODUCT_ADD_FAIL);
        }
    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchKeyPressed(KeyEvent event) {

        if (partSearchInput.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Displays confirmation dialog and removes selected part from associated parts table.
     *
     * Displays error message if no part is selected.
     *
     * @param event Remove button action.
     */
    @FXML
    void removeButtonAction(ActionEvent event) {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alerts.error(Alerts.Errors.PART_NOT_SELECTED);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                assocPartTableView.setItems(assocParts);
            }
        }
    }

    /**
     * Adds new product to inventory and loads MainScreenController.
     *
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     *
     * @param event Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(idInput.getText());;
            String name = nameInput.getText();
            Double price = Double.parseDouble(priceInput.getText());
            int stock = Integer.parseInt(inventoryInput.getText());
            int min = Integer.parseInt(minInput.getText());
            int max = Integer.parseInt(maxInput.getText());

            if(Validate.productID(id)){
                
            }

            if (Validate.name(name)) {
                if (Validate.minimum(min, max) && Validate.stock(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setID(Inventory.createProductID());
                    Inventory.addProduct(newProduct);
                    returnToMainPage(event);
                }
            }
        } catch (Exception e){
            Alerts.error(Alerts.Errors.PRODUCT_ADD_FAIL);
        }
    }

    /**
     * Loads Main Page
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void returnToMainPage(ActionEvent event) throws IOException {

        MainController.loadPage("Main", event);
    }


    /**
     * Initializes controller and populates table views.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        assocPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}