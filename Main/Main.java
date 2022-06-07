package Main;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Inventory Management Application
 * 
 * A Future Enhancement would be to add stock management to adding parts to products. 
 * a product may only a few parts from what is available. Currently it uses all of them.
 * 
 * I had issues with the stock not being updated through the constructor. Used Product.setStock() to resolve the issue.
 *
 * @author Matt Goldstine
 * Javadocs: {@docRoot}/javadoc
 *
 */
public class Main extends Application {

    /**
     * Start Main Page.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main method for the application.
     *
     * Create default sample data and load main page.
     * 
     * @param args
     */
    public static void main(String[] args) {

        //Add Parts
        InHouse ProjectorBulb = new InHouse(Inventory.createPartID(), "Wemax Nova Bulb", 20, 10, 10, 1000, 4);
        Inventory.addPart(ProjectorBulb);

        //Add Products
        Product Projector = new Product(Inventory.createProductID(), "Wemax Nova", 2000, 10, 2, 20);
        Inventory.addProduct(Projector);

        launch(args);
    }
}
