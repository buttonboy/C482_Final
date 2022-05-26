package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains Part and Product information
 * 
 * @author Matt Goldstine
 */
public class Inventory {
    
    /**
     * List of All Parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    /**
     * List of All Products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add Part to All Part list
     * @param newPart Part to be added
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    } 

    /**
     * All Product to All Product list
     * @param newProduct Product to be added
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * Find part by ID
     * @param partId ID to search
     * @return Part
     */
    public static Part lookupPart(int partId){
        Part pReturn = null;
        
        for(Part p: allParts){
            if(p.getId() == partId) pReturn = p;
        }
        
        return pReturn;
    }

    /**
     * Find product by ID
     * @param productId ID to be searched
     * @return Product 
     */
    public static Product lookupProduct(int productId){
        Product pReturn = null;
        
        for(Product p : allProducts){
            if(p.getId() == productId) pReturn = p;
        }
        
        return pReturn; 
    }

    /**
     * Find Part by Name
     * @param partName Name to be Searched
     * @return Part
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> oReturn = FXCollections.observableArrayList();

        for(Part p : allParts){
            if(p.getName().equalsIgnoreCase(partName)) oReturn.add(p);
        }

        return oReturn;
    }

    /**
     * Find Product by Name
     * @param productName Name to be searched
     * @return Product
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> oReturn = FXCollections.observableArrayList();

        for(Product p : allProducts){
            if(p.getName().equalsIgnoreCase(productName)) oReturn.add(p);
        }

        return oReturn;
    }

    /**
     * Replace Part in All Part list by Index
     * @param index index of Part to update
     * @param selectedPart Part to be inserted
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * Replace Product in All Product list by index
     * @param index Index of Product to update
     * @param newProduct Product to be inserted
     */
    public static void updateProduct(int index,Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     * Delete Part if exists 
     * @param selectedPart Part to Delete
     * @return True if Successful, False on Fail
     */
    public static boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Product if exists 
     * @param selectedProduct Product to Delete
     * @return True if Successful, False on Fail
     */
    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return List of Parts
     * @return ObservableList of Parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Return List of Products
     * @return ObservableList of Products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * Generate new ID for Part
     * @return
     */
    public static int createPartID(){
        int id = allParts.size();
        while(lookupPart(id) != null){
            ++id;
        }

        return id;
    }

    /**
     * Generate new ID for Product
     * @return
     */
    public static int createProductID(){
        int id = allProducts.size();
        while(lookupProduct(id) != null){
            ++id;
        }

        return id;
    }

}
