package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import Model.Inventory;
import Model.Part;
import Model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Main.Main;

/**
 * Controller for Edit Product Page
 *
 * @author Matt Goldstine
 */
public class EditProductController implements Initializable {

     /**
     * Product that was Selected
     */
    private Product selectedProduct;
    
    /**
     * List of parts associated with product.
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * List of parts associated not with product.
     */
    private ObservableList<Part> unusedParts = FXCollections.observableArrayList();

    /**
     * Table of parts associated with product.
     */
    @FXML
    private TableView<Part> assocPartTableView;

    /**
     * ID column for associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartIdColumn;

    /**
     * Name column for associated parts table.
     */
    @FXML
    private TableColumn<Part, String> assocPartNameColumn;

    /**
     * Stock column for associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartStockColumn;

    /**
     * Price column for associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> assocPartPriceColumn;

    /**
     * Parts table view.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * ID column for parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * Name column for parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * Stock column for parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partStockColumn;

    /**
     * Price column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * Part search input.
     */
    @FXML
    private TextField partSearchInput;

    /**
     * ID Input.
     */
    @FXML
    private TextField idInput;

    /**
     * Name Input.
     */
    @FXML
    private TextField nameInput;

    /**
     * Stock Input.
     */
    @FXML
    private TextField stockInput;

    /**
     * Price Input.
     */
    @FXML
    private TextField priceInput;

    /**
     * Max Stock Input
     */
    @FXML
    private TextField maxInput;

    /**
     * Min Stock Input
     */
    @FXML
    private TextField minInput;

    /**
     * Add part object selected in the Parts Table to the Associated Parts Table.
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
            unusedParts.remove(selectedPart);
            assocParts.add(selectedPart);
            assocPartTableView.setItems(assocParts);
        }
    }

    /**
     * Confirmation dialog before loading Main Page.
     *
     * @param event Cancel button action.
     * @throws IOException FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        
        Alert dialog = Alerts.confirm(Alerts.Confirm.RETURN_TO_MAIN);
        Optional<ButtonType> answer = dialog.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
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

        ObservableList<Part> allParts = unusedParts;
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearchInput.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

    /*  if (partsFound.size() == 0) {
            Alerts.error(Alerts.Errors.PRODUCT_ADD_FAIL);
        } */
    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchKeyPressed(KeyEvent event) {

        if (partSearchInput.getText().isEmpty()) {
            partTableView.setItems(unusedParts);
            determineUnusedParts();
        }
    }

    /**
     * Has User Confirm before Removing Part
     *
     * If no part is selected display message
     *
     * @param event Remove button action.
     */
    @FXML
    void removeButtonAction(ActionEvent event) {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alerts.error(Alerts.Errors.PART_NOT_SELECTED);
        } else {

            Alert dialog = Alerts.confirm(Alerts.Confirm.CONFIRM_PART_REMOVE);
            Optional<ButtonType> answer = dialog.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                if(!partTableView.getItems().contains(selectedPart)){
                    partTableView.getItems().add(selectedPart);
                }
                assocPartTableView.setItems(assocParts);
            }
        }
    }

    /**
     * Adds new product and loads MainController.
     *
     * Validate Inputs and display error messages
     * invalid values.
     *
     * @param event Save button Event.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = selectedProduct.getId();
            String name = nameInput.getText();
            Double price = Double.parseDouble(priceInput.getText());
            int stock = Integer.parseInt(stockInput.getText());
            int min = Integer.parseInt(minInput.getText());
            int max = Integer.parseInt(maxInput.getText());

            if (Validate.name(name)) {
                if (Validate.minimum(min, max) && Validate.stock(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }
                    newProduct.setStock(stock);

                    Inventory.addProduct(newProduct);
                    Inventory.deleteProduct(selectedProduct);
                    //reassociateUnusedParts();
                    returnToMainPage(event);
                }
            }
        } catch (Exception e){
            Alerts.error(Alerts.Errors.PRODUCT_ADD_FAIL);
        }
    }

    private void determineUnusedParts(){
        
        for(Part p: unusedParts){
            if(assocParts.contains(p)){
                partTableView.getItems().remove(p);
            }
        }
    }
    
    private void determineUnusedPartOLD(){
        unusedParts = Inventory.getAllParts();
        
        for(Part p: unusedParts){
            if(assocParts.contains(p)){
                unusedParts.remove(p);
            }
        }
    }

    private void reassociateUnusedParts(){
        for(Part p: assocParts){
            if(!unusedParts.contains(p)){
                unusedParts.add(p);
            }
        }
    }

    /**
     * Loads Main Page
     *
     * @param event Event from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void returnToMainPage(ActionEvent event) throws IOException {

        MainController.loadPage("Main", event);
    }


    /**
     * Initializes controller and generates table data
     *
     * @param location The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedProduct = MainController.getProductToEdit();
        unusedParts = Inventory.getAllParts();
        assocParts = selectedProduct.getAllAssociatedParts();
        partTableView.setItems(unusedParts);
        assocPartTableView.setItems(assocParts);
        
        determineUnusedParts();
        
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        

        assocPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        
        idInput.setText(String.valueOf(selectedProduct.getId()));
        nameInput.setText(selectedProduct.getName());
        stockInput.setText(String.valueOf(selectedProduct.getStock()));
        priceInput.setText(String.valueOf(selectedProduct.getPrice()));
        maxInput.setText(String.valueOf(selectedProduct.getMax()));
        minInput.setText(String.valueOf(selectedProduct.getMin()));
    }
}