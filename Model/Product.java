package Model;
/**
 * Product information with associated Parts
 * 
 * @author Matt Goldstine
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name,double price,int stock,int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.min = min;
        this.max = max;
    }

    /**
     * Sets ID
     * @param id Product ID
     */
    public void setID(int id){
        this.id = id;
    }

    /**
     * Gets ID
     * @return int:Product ID
     */
    public int getId(){
        return this.id;
    }

    /**
     * Sets Name
     * @param name Name of Product
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Gets Name
     * @return String:Name of Product
     */
    public String getName(){
        return name;
    }

    /**
     * Sets Price
     * @param price Price of product
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Gets Price
     * @return double: Price of product
     */
    public double getPrice(){
        return price;
    }

    /**
     * Sets Stock for Product
     * @param stock Stock of Product
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Get stock of Product
     * @return integer of currect stock
     */
    public int getStock(){
        return stock;
    }
    
    /**
     * Set Min for Product stock
     * @param min Minimum stock value
     */
    public void setMin(int min){
        this.min = min;
    }

    /**
     * Get Min of Product stock
     * @return integer of current minimum
     */
    public int getMin(){
        return min;
    }
    
    /**
     * Set Max of Product stock
     * @param max Max value to be set
     */
    public void setMax(int max){
        this.max = max;
    }

    /**
     * Get Max of Product stock
     * @return Integer of current Max value
     */
    public int getMax(){
        return max;
    }

    /**
     * Add Part to AssociatedParts Array
     * @param part Part to be added to array
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Remove Part to AssociatedParts Array, Check if part exists
     * @param selectedAssociatedPart Part to be removed
     * @return bool, false if no part is available
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if(associatedParts.contains(selectedAssociatedPart)){
            associatedParts.remove(selectedAssociatedPart);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }


}
