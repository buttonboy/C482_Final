package Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

/**
 * Class used for display Errors and Confirmation Dialogs
 * 
 * @author Matt Goldstine
 */
public class Alerts {

    /**
     * Enum Containing Error Messages
     */
    public static enum Errors {
        PART_ADD_FAIL,INVALID_MACHINEID,INVALID_MIN,INVALID_NAME,INVALID_STOCK, 
        PRODUCT_ADD_FAIL, PART_NOT_FOUND, PART_NOT_SELECTED, INVALID_ID, PRODUCT_NOT_FOUND, PRODUCT_NOT_SELECTED, PART_IN_USE

    }

    /**
     * Enum Containing Confirmation Messages
     */
    public static enum Confirm {
        RETURN_TO_MAIN, CONFIRM_PART_REMOVE, CONFIRM_DELETE_PRODUCT, CONFIRM_DELETE_PART
    }

    /**
     * 
     * Displays Error Alert Dialogs
     *
     * @param errorMSg Alert To Display.
     */
    public static void error(Errors errorType) {

        Alert dialog = new Alert(Alert.AlertType.ERROR);

        switch (errorType) {
            case PART_ADD_FAIL:
                dialog.setTitle("Error");
                dialog.setHeaderText("Error Adding Part");
                dialog.setContentText("Form contains blank fields or invalid values.");
                dialog.showAndWait();
                break;
            case INVALID_MACHINEID:
                dialog.setTitle("Error");
                dialog.setHeaderText("Invalid value for Machine ID");
                dialog.setContentText("Machine ID may only contain numbers.");
                dialog.showAndWait();
                break;
            case INVALID_MIN:
                dialog.setTitle("Error");
                dialog.setHeaderText("Invalid value for Min");
                dialog.setContentText("Min must be a number greater than 0 and less than Max.");
                dialog.showAndWait();
                break;
            case INVALID_STOCK:
                dialog.setTitle("Error");
                dialog.setHeaderText("Invalid value for Stock");
                dialog.setContentText("Stock value must be a number equal to or within the Min and Max.");
                dialog.showAndWait();
                break;
            case INVALID_NAME:
                dialog.setTitle("Error");
                dialog.setHeaderText("Invalid value for Name");
                dialog.setContentText("Name cannot be empty.");
                dialog.showAndWait();
                break;
            case INVALID_ID:
                dialog.setTitle("Information");
                dialog.setHeaderText("ID already Exists");
                dialog.setContentText("This ID is already in use. A new ID will be generated");
                dialog.showAndWait();
                break;
            case PRODUCT_ADD_FAIL:
                dialog.setTitle("Error");
                dialog.setHeaderText("Error Adding Product");
                dialog.setContentText("Form contains blank fields or invalid values.");
                dialog.showAndWait();
                break;
            case PART_NOT_FOUND:
                dialog.setTitle("Information");
                dialog.setHeaderText("Part Does Not Exist");
                dialog.showAndWait();
                break;
            case PART_NOT_SELECTED:
                dialog.setTitle("Error");
                dialog.setHeaderText("No Part Selected");
                dialog.showAndWait();
                break;
            case PRODUCT_NOT_FOUND:
                dialog.setTitle("Information");
                dialog.setHeaderText("Product Not Found");
                dialog.showAndWait();
                break;
            case PRODUCT_NOT_SELECTED:
                dialog.setTitle("Error");
                dialog.setHeaderText("Product Not Selected");
                dialog.showAndWait();
                break;
            case PART_IN_USE:
                dialog.setTitle("Error");
                dialog.setHeaderText("Parts Is Being Used");
                dialog.setContentText("All parts must be removed from products before the can be removed.");
                dialog.showAndWait();
                break;
        }
    }

    /**
     * Generates Confirmation Dialog
     * @param Enum for Type of Dialog
     * @return Dialog to be used for returning answer
     */
    public static Alert confirm(Confirm dialogType){

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);

        switch (dialogType){
            case RETURN_TO_MAIN:
                dialog.setTitle("Alert");
                dialog.setContentText("Return to main page without saving?");
                break;
            case CONFIRM_PART_REMOVE:
                dialog.setTitle("Alert");
                dialog.setContentText("Remove Selected Part from Associated Parts?");
                break;
            case CONFIRM_DELETE_PRODUCT:
                dialog.setTitle("Alert");
                dialog.setContentText("Are you sure you want to delete this Product?");
                break;
            case CONFIRM_DELETE_PART:
                dialog.setTitle("Alert");
                dialog.setContentText("Are you sure you want to delete this Part?");
                break;
        }

        return dialog;


    }

    /**
     * Unused Choice Dialog type
     */
    public static ChoiceDialog<String> choice(String[] Choices, Confirm dialogType){

        ChoiceDialog dialog = new ChoiceDialog<String>();
        ObservableList<String> list = dialog.getItems();

        for (String choice : Choices) {
            list.add(choice);
        }

        switch (dialogType){
            case RETURN_TO_MAIN:
                dialog.setTitle("Alert");
                dialog.setContentText("Return to main page without saving?");
                break;
            default:
                dialog.setTitle("Alert");
                dialog.setContentText("Make a Choice");
        }

        return dialog;


    }
    
}
