package main.controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Navigation;
import main.model.ToDoData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    // Connecting the label for the digit in the home view by injecting FXML
    @FXML
    private Label lblTaskAmount;

    private final Navigation navigation = new Navigation();     // New instance for Navigation to access the methods to change tabs

    // Method to initialize a certain functionality inside the scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Get the amount of tasks by accessing the ToDoData class and using getTaskAmount() and display it on the label
        lblTaskAmount.setText(String.valueOf(ToDoData.getInstance().getTaskAmount()));
    }

    // Go to Task Creation view
    public void toTaskCreation(ActionEvent event) throws IOException {
        navigation.toTaskCreation(event);
    }

    // Go to Tasks view
    public void toTasks(ActionEvent event) throws IOException {
        navigation.toTasks(event);
    }
}
