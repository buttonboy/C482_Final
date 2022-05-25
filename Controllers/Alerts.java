package Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

public class Alerts {

    public static enum Errors {
        PART_ADD_FAIL,INVALID_MACHINEID,INVALID_MIN,INVALID_NAME,INVALID_STOCK, PRODUCT_ADD_FAIL, PART_NOT_FOUND, PART_NOT_SELECTED, INVALID_ID

    }

    public static enum Confirm {
        RETURN_TO_MAIN
    }

    //private Errors getErrorType

    /**
     * Displays alert messages.
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
                dialog.setTitle("Error");
                dialog.setHeaderText("ID already Exists");
                dialog.setContentText("This ID is already used by another Item.");
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
        }
    }

    public static Alert confirm(Confirm dialogType){

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);

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
