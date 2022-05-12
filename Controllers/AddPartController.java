package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Alerts;
import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;

/**
 * Controller class for Add Part Page.
 *
 * @author Matt Goldstine
 */
public class AddPartController implements Initializable {


    
    /**
     * Part Type Toggle Group
     */
    @FXML
    private ToggleGroup tgPartType;
    
    /**
     * In-House radio button.
     */
    @FXML
    private RadioButton inHouseRadioButton;
    
    /**
     * Outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Part ID text field.
     */
    @FXML
    private TextField idInput;

    /**
     * Part name text field.
     */
    @FXML
    private TextField nameInput;

    /**
     * Part stock text field.
     */
    @FXML
    private TextField stockInput;

    /**
     * Part price text field.
     */
    @FXML
    private TextField priceInput;

    /**
     * Part maximum stock text field.
     */
    @FXML
    private TextField maxInput;

    
    /**
     * Part minimum stock text field.
     */
    @FXML
    private TextField minInput;
    
    /**
     * The Machine ID/Company Name Label for part.
     */
    @FXML
    private Label machineCompLabel;
    
    /**
     * The Machine ID/Company Name Input for the part.
     */
    @FXML
    private TextField machineCompInput;
    
    /**
     * Confirmation dialog before loading Main Page.
     *
     * @param event Cancel button action.
     * @throws IOException FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    /**
     * Update machine ID/company name label to "Machine ID".
     *
     * @param event In-house raido button action.
     */
    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {

        machineCompLabel.setText("Machine ID");
    }

    /**
     * Update machine ID/company name label to "Company Name".
     *
     * @param event Outsourced radio button.
     */
    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {

        machineCompLabel.setText("Company Name");
    }

    /**
     * Add new part to inventory and load MainController.
     *
     * Validates Inputs before saving, display error on fail.
     *
     * @param event Save button action.
     * @throws IOException FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = nameInput.getText();
            Double price = Double.parseDouble(priceInput.getText());
            int stock = Integer.parseInt(stockInput.getText());
            int min = Integer.parseInt(minInput.getText());
            int max = Integer.parseInt(maxInput.getText());
            int machineId;
            String compName;
            boolean partAddSuccessful = false;

            if (name.isEmpty()) {
                Alerts.display(Alerts.Errors.INVALID_NAME);
            } else {
                if (minValid(min, max) && stockValid(min, max, stock)) {

                    if (inHouseRadioButton.isSelected()) {
                        try {
                            machineId = Integer.parseInt(machineCompInput.getText());
                            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                            newInHousePart.setId(Inventory.createPartID());
                            Inventory.addPart(newInHousePart);
                            partAddSuccessful = true;
                        } catch (Exception e) {
                            Alerts.display(Alerts.Errors.PART_ADD_FAIL);;
                        }
                    }

                    if (outsourcedRadioButton.isSelected()) {
                        compName = machineCompInput.getText();
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, compName);
                        newOutsourcedPart.setId(Inventory.createPartID());
                        Inventory.addPart(newOutsourcedPart);
                        partAddSuccessful = true;
                    }

                    if (partAddSuccessful) {
                        returnToMainScreen(event);
                    }
                }
            }
        } catch(Exception e) {
            Alerts.display(Alerts.Errors.PART_ADD_FAIL);
        }
    }

    /**
     * Loads MainScreenController.
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Validates that min is greater than 0 and less than max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            Alerts.display(Alerts.Errors.INVALID_MIN);
        }

        return isValid;
    }

    /**
     * Validates that stock is equal too or within min and max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The stock for the part.
     * @return Boolean Is Stock Valid
     */
    private boolean stockValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            Alerts.display(Alerts.Errors.INVALID_STOCK);
        }

        return isValid;
    }

    

    /**
     * Initializes controller and sets in-house radio button to true.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inHouseRadioButton.setSelected(true);
    }
}