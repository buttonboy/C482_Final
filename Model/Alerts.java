package Model;

import javafx.scene.control.Alert;

public class Alerts {

    public static enum Errors {
        PART_ADD_FAIL,INVALID_MACHINEID,INVALID_MIN,INVALID_NAME,INVALID_STOCK

    }

    //private Errors getErrorType

    /**
     * Displays alert messages.
     *
     * @param errorMSg Alert To Display.
     */
    public static void display(Errors errorType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (errorType) {
            case PART_ADD_FAIL:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case INVALID_MACHINEID:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers.");
                alert.showAndWait();
                break;
            case INVALID_MIN:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case INVALID_STOCK:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Stock");
                alert.setContentText("Stock value must be a number equal to or within the Min and Max.");
                alert.showAndWait();
                break;
            case INVALID_NAME:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Name");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }
    
}
