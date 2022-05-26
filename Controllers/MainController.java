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
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Inventory;
import Model.Part;
import Model.Product;

/**
 * Main Controller
 * 
 * Displays tables for Parts and Products and allows for searching and adding/editing for both
 *
 * @author Matt Goldstine
 */
public class MainController implements Initializable {

    /**
     * Part the user has selected to edit
     */
    private static Part partToEdit;

    /**
     * Product the user has selected to edit
     */
    private static Product productToEdit;

    /**
     * 
     */
    @FXML
    private TextField partSearch;

    /**
     * Table view for the parts.
     */
    @FXML
    private TableView<Part> partTable;

    /**
     * The ID column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partColId;

    /**
     * The part name column for the parts table.
     */
    @FXML
    private TableColumn<Part, String> partColName;

    /**
     * The inventory column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partColInventory;

    /**
     * The price column for parts table.
     */
    @FXML
    private TableColumn<Part, Double> partColPrice;

    /**
     * Table text field for the product search.
     */
    @FXML
    private TextField productSearch;

    /**
     * Table view for the products.
     */
    @FXML
    private TableView<Product> productTable;

    /**
     * The ID column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productColId;

    /**
     * The name column for the product table.
     */
    @FXML
    private TableColumn<Product, String> productColName;

    /**
     * The inventory column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productColInventory;

    /**
     * The price column for the product table.
     */
    @FXML
    private TableColumn<Product, Double> productColPrice;

    /**
     * Gets the part object selected by the user in the part table.
     *
     * @return A part object, null if no part selected.
     */
    public static Part getPartToEdit() {
        return partToEdit;
    }

    /**
     * Gets the product object selected by the user in the product table.
     *
     * @return A product object, null if no product selected.
     */
    public static Product getProductToEdit() {
        return productToEdit;
    }

    /**
     * Exits Program
     *
     * @param event Exit button event
     */
    @FXML
    void exitButtonAction(ActionEvent event) {

        System.exit(0);
    }

    /**
     * Loads the AddPartController.
     *
     * @param event Add button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void partAddAction(ActionEvent event) throws IOException {

        loadPage("addPart", event);
    }

    /**
     * Deletes the part selected by the user in the part table.
     *
     * The method displays an error message if no part is selected and a confirmation
     * dialog before deleting the selected part.
     *
     * @param event Part delete button action.
     */
    @FXML
    void partDeleteAction(ActionEvent event) {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alerts.error(Alerts.Errors.PART_NOT_SELECTED);
        } else {

            Alert dialog = Alerts.confirm(Alerts.Confirm.CONFIRM_DELETE_PART);
            Optional<ButtonType> answer = dialog.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**
     * Loads the EditPartController.
     *
     * The method displays an error message if no part is selected.
     *
     * @param event Part Edit button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void partEditAction(ActionEvent event) throws IOException {

        partToEdit = partTable.getSelectionModel().getSelectedItem();

        if (partToEdit == null) {
            Alerts.error(Alerts.Errors.PART_NOT_SELECTED);
        } else {
            loadPage("EditPart", event);
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
        String searchString = partSearch.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTable.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alerts.error(Alerts.Errors.PART_NOT_FOUND);
        }
    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (partSearch.getText().isEmpty()) {
            partTable.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Loads AddProductController.
     *
     * @param event Add product button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productAddAction(ActionEvent event) throws IOException {
        loadPage("AddProduct", event);
    }

    /**
     * Deletes the product selected by the user in the product table.
     *
     * The method displays an error message if no product is selected and a confirmation
     * dialog before deleting the selected product. Also prevents user from deleting
     * a product with one or more associated parts.
     *
     * @param event Product delete button action.
     */
    @FXML
    void productDeleteAction(ActionEvent event) {

        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alerts.error(Alerts.Errors.PRODUCT_NOT_SELECTED);
        } else {

            Alert dialog = Alerts.confirm(Alerts.Confirm.CONFIRM_DELETE_PRODUCT);
            Optional<ButtonType> answer = dialog.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    Alerts.error(Alerts.Errors.PART_IN_USE);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

    /**
     * Loads the EditProductController.
     *
     * The method displays an error message if no product is selected.
     *
     * @param event Product Edit button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productEditAction(ActionEvent event) throws IOException {

        productToEdit = productTable.getSelectionModel().getSelectedItem();

        if (productToEdit == null) {
            Alerts.error(Alerts.Errors.PRODUCT_NOT_SELECTED);
        } else {
            loadPage("EditProduct", event);
        }
    }

    /**
     * Initiates a search based on value in products search text field and refreshes the products
     * table view with search results.
     *
     * Products can be searched for by ID or name.
     *
     * @param event Part search button action.
     */
    @FXML
    void productSearchBtnAction(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = productSearch.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productTable.setItems(productsFound);

        if (productsFound.size() == 0) {
            Alerts.error(Alerts.Errors.PRODUCT_NOT_FOUND);
        }
    }

    /**
     * Refreshes product table view to show all products when products search text field is empty.
     *
     * @param event Products search text field key pressed.
     */
    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

        if (productSearch.getText().isEmpty()) {
            productTable.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * 
     * @param String Page name without file extension
     * @param event Action Event for obtain current Window
     * @throws IOException
     */
    public static void loadPage(String page, ActionEvent event)throws IOException{
        page = "../View/" + page + ".fxml";
        Parent parent = FXMLLoader.load(MainController.class.getResource(page));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes controller and populates table views.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Part Table
        partTable.setItems(Inventory.getAllParts());
        partColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partColInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Product Table
        productTable.setItems(Inventory.getAllProducts());
        productColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productColInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}