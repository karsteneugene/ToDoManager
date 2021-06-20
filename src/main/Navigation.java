package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// A class to make methods to go to different scenes ( Home, Task Creation, and Tasks )
// This class prevents long code duplicates inside the controller classes

public class Navigation {

    // Method to change to the Home scene
    public void toHome(ActionEvent event) throws IOException {
        URL url = new File("src/main/fxml/Home.fxml").toURI().toURL();      // Gets the Home view URL
        Parent root = FXMLLoader.load(url);                                          // Loads the Home view
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();     // Sets the new stage

        // Sets the new scene with the Home view
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();   // Show the new display
    }

    // Method to change to the Task Creation scene
    public void toTaskCreation(ActionEvent event) throws IOException {
        URL url = new File("src/main/fxml/TaskCreation.fxml").toURI().toURL();  // Gets the Task Creation view URL
        Parent root = FXMLLoader.load(url);                                              // Loads the Task Creation view
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();         // Sets the new stage

        // Sets the new scene with the Task Creation view
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();   // Show the new display
    }

    // Method to change to the Tasks scene
    public void toTasks(ActionEvent event) throws IOException {
        URL url = new File("src/main/fxml/Tasks.fxml").toURI().toURL();     // Gets the Tasks view URL
        Parent root = FXMLLoader.load(url);                                          // Loads the Tasks view
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();     // Sets the new stage

        // Sets the new scene with the Tasks view
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();   // Show the new display
    }

}
