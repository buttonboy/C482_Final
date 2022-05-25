package Controllers;

import Model.Inventory;

public class Validate {

   /**
     * Validates that min is greater than 0 and less than max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */
    public static boolean minimum(int min, int max) {
        if (min <= 0 || min >= max) {
            Alerts.error(Alerts.Errors.INVALID_MIN);
            return false;
        }
        return true;
    }

     /**
     * Validates that stock is equal too or within min and max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The stock for the part.
     * @return Boolean Is Stock Valid
     */
    public static boolean stock(int min, int max, int stock) {
        if (stock < min || stock > max) {
            Alerts.error(Alerts.Errors.INVALID_STOCK);
            return false;
        }
        return true;
    }

    public static boolean name(String name){
        if(name.isEmpty() || name.isBlank()){
            Alerts.error(Alerts.Errors.INVALID_NAME);
            return false;
        }
        return true;
    }

    public static boolean productID(int id){
        if(Inventory.lookupProduct(id) != null){
            Alerts.error(Alerts.Errors.INVALID_ID);
            return false;
        }
        return true;
    }
    
}
