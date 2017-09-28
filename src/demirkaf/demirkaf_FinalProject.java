package demirkaf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Name: F. Fulya Demirkan
Assignment: Final Project
Program: System Analyst

This program is a PetBook application which can be used by veterinarians to 
register pets and see information about those pets.
 */
/**
 * This program is a PetBook application which can be used by veterinarians to
 * register pets and see information about those pets.
 *
 * @author Fulya Demirkan
 */
public class demirkaf_FinalProject extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFinalProject.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("PetBook");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
