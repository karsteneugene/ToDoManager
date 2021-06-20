package main.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import main.Navigation;
import main.model.ToDoData;
import main.model.ToDoItem;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class TaskCreationController implements Initializable {

    // Injecting FXML to connect some controls with the controller
    @FXML
    private TextField tfTask;
    @FXML
    private DatePicker dpDueDate;
    @FXML
    private CheckBox cbImportant;
    @FXML
    private Label lblMessage;

    private final Navigation navigation = new Navigation();     // New instance for Navigation to access the methods to change tabs

    // Method to initialize a certain functionality inside the scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Lambda expression to prevent users from picking a due date before the current day ( because it does not make sense )
        dpDueDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    // Go to Home view
    public void toHome(ActionEvent event) throws IOException {
        navigation.toHome(event);
    }

    // Go to Tasks view
    public void toTasks(ActionEvent event) throws IOException {
        navigation.toTasks(event);
    }

    // A method which gets called everytime the user presses the "Add" button
    public void addTask() {

        // Checks if the text field or date picker is empty
        if (tfTask.getText() == null || tfTask.getText().trim().isEmpty() || dpDueDate.getValue() == null) {
            lblMessage.setText("Please fill in the Task and Due Date field!");      // If it is, warn the user
        } else {
            String taskTitle = tfTask.getText().trim();     // Gets the title of the Task from the text field
            LocalDate dueDate = dpDueDate.getValue();       // Gets the due date value from the date picker
            boolean important = cbImportant.isSelected();   // Gets the true or false of whether or not task is important by checking the checkbox

            ToDoItem toDoItem = new ToDoItem(taskTitle, dueDate, important);    // Gets the new ToDoItem by using the constructor
            ToDoData.getInstance().addToDoItem(toDoItem);       // Adds the new task to the ToDoItems list

            // Clears all the input fields
            tfTask.clear();
            dpDueDate.getEditor().clear();
            cbImportant.setSelected(false);

            lblMessage.setText("Task successfully added!");     // If everything is filled, displays success message

            // Timer to remove success message after 3.5 seconds
            new Timeline(new KeyFrame(
                    Duration.millis(3500),
                    event -> lblMessage.setText("")
            )).play();
        }
    }
}
