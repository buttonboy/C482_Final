package Model;

/**
 * Class for Outsources parts
 */
public class Outsourced extends Part{
    
    /**
     * Name of Company
     */
    private String companyName;

    /**
     * Constructor of Outsourced Part
     * @param id ID of Part
     * @param name Name of Part
     * @param price Price of Part
     * @param stock Current Stock of Part
     * @param min Minimum Stock Value
     * @param max Maximum Stock Value
     * @param companyName Name of Company
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Set Name of Company
     * @param companyName
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     * Get Name of Company
     * @return
     */
    public String getCompanyName(){
        return this.companyName;
    }
}
