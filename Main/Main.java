package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Inventory.InHouse;
import Inventory.Inventory;
import Inventory.Outsourced;
import Inventory.Product;

/**
 * Inventory Management Application
 *
 * @author Matt Goldstine
 *
 */
public class Main extends Application {

    /**
     * Start Main Scene.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Pages/main.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * The main method creates sample data and launches the application.
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



       /*  //partId = Inventory.getNewPartId();
        int id = 0;
        InHouse tvScreen = new InHouse(id,"TV Screen", 300.00, 5, 1, 20,
                101);
        //partId = Inventory.getNewPartId();
        id = 1;
        InHouse tvShell = new InHouse(id,"TV Shell", 100.00, 5, 1, 20,
                101);
        //partId = Inventory.getNewPartId();
        id = 2;
        InHouse powerCord = new InHouse(id,"Power Cord", 2.99, 5, 1, 20,
                101);
        //partId = Inventory.getNewPartId();
        id = 3;
        Outsourced remote = new Outsourced(id, "Remote Control",29.99, 50, 30,
                100, "Panasonic");
        Inventory.addPart(tvScreen);
        Inventory.addPart(tvShell);
        Inventory.addPart(powerCord);
        Inventory.addPart(remote);

        //Add sample product
        //int productId = Inventory.getNewProductId();
        id = 0;
        Product television = new Product(id, "LCD Television", 499.99, 20, 20,
                100);
        television.addAssociatedPart(tvScreen);
        television.addAssociatedPart(tvShell);
        television.addAssociatedPart(powerCord);
        television.addAssociatedPart(remote);
        Inventory.addProduct(television); */

        //System.out.println("Application starting");
        launch(args);
    }
}