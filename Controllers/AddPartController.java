package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;

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
        
        Alert dialog = Alerts.confirm(Alerts.Confirm.RETURN_TO_MAIN);
        Optional<ButtonType> answer = dialog.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            returnToMainPage(event);
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
            int id = (idInput.getLength() > 0)? Integer.parseInt(idInput.getText()): Inventory.createPartID();
            String name = nameInput.getText();
            Double price = Double.parseDouble(priceInput.getText());
            int stock = Integer.parseInt(stockInput.getText());
            int min = Integer.parseInt(minInput.getText());
            int max = Integer.parseInt(maxInput.getText());
            int machineId;
            String compName;
            boolean partAddSuccessful = false;

            //If Entered ID is already used generate new one.
            id = (Validate.partID(id))? id : Inventory.createPartID();

            if (Validate.name(name)) {
                if (Validate.minimum(min, max) && Validate.stock(min, max, stock)) {

                    if (inHouseRadioButton.isSelected()) {
                        try {
                            machineId = Integer.parseInt(machineCompInput.getText());
                            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                            Inventory.addPart(newInHousePart);
                            partAddSuccessful = true;
                        } catch (Exception e) {
                            Alerts.error(Alerts.Errors.PART_ADD_FAIL);;
                        }
                    }

                    if (outsourcedRadioButton.isSelected()) {
                        compName = machineCompInput.getText();
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, compName);
                        Inventory.addPart(newOutsourcedPart);
                        partAddSuccessful = true;
                    }

                    if (partAddSuccessful) {
                        returnToMainPage(event);
                    }
                }
            }
        } catch(Exception e) {
            Alerts.error(Alerts.Errors.PART_ADD_FAIL);
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