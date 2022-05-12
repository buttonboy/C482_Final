package Model;

/**
 * In House part class
 * 
 * @author Matt Goldstine
 */
public class InHouse extends Part {
    
    /**
     * Machine ID for In House part
     */
    private int machineId;

    /**
     * Constructor of In House object
     * @param id Part ID
     * @param name Part Name
     * @param price Price of Part
     * @param stock Amount in Stock
     * @param min Minium Stock Amount
     * @param max Maximum Stock Amount
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super( id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Set Machined ID
     * @param machineId int for Machine ID 
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     * Get Machine ID
     * @return int of Machine ID
     */
    public int getMachineId(){
        return this.machineId;
    }
}
