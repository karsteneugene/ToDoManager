package main.controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Navigation;
import main.model.ToDoData;
import main.model.ToDoItem;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class TasksController implements Initializable {

    // Injecting FXML to connect some controls with the controller
    @FXML
    private TableView<ToDoItem> tvTasks;

    @FXML
    private TableColumn<ToDoItem, String> taskTitleColumn;

    @FXML
    private TableColumn<ToDoItem, LocalDate> dueDateColumn;

    @FXML
    private TableColumn<ToDoItem, Boolean> importantColumn;

    private final Navigation navigation = new Navigation();     // New instance for Navigation to access the methods to change tabs

    // Declaring an Image to be used for a boolean column
    private final Image imageTrue = new Image("main/images/check_icon.png");

    // Method to initialize a certain functionality inside the scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the "taskTitle" variables inside the Tasks column
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM");    // Declare a formatter for Date

        // Lambda expression to change the format of the Date inside the Due Date column using the formatter
        dueDateColumn.setCellFactory(column -> new TableCell<ToDoItem, LocalDate>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(formatter.format(date));
                }
            }
        });

        // Set the "dueDate" variables inside the Due Date column
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<ToDoItem, LocalDate>("dueDate"));

        // Lambda expression to change the boolean texts to be images or null
        importantColumn.setCellFactory(column -> new TableCell<ToDoItem, Boolean>() {
            private final ImageView imageView = new ImageView();

            {
                // Sets the image to be 20 by 20
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                setGraphic(imageView);
            }

            // Method to change the text "true" to a tick icon and "false" to nothing
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                if (empty || item == null) {
                    imageView.setImage(null);
                } else {
                    imageView.setImage(item ? imageTrue : null);
                }
            }
        });

        // Set the "important" variables inside the Important column
        importantColumn.setCellValueFactory(new PropertyValueFactory<ToDoItem, Boolean>("important"));
        tvTasks.setItems(ToDoData.getInstance().getToDoItems());    // Gets the items from the list from ToDoData class
    }

    // Method to delete a task when the delete button is clicked
    public void deleteTask() {
        ToDoData.getInstance().deleteTask(tvTasks.getSelectionModel().getSelectedItem());
    }

    // Go to Home view
    public void toHome(ActionEvent event) throws IOException {
        navigation.toHome(event);
    }

    // Go to Task Creation view
    public void toTaskCreation(ActionEvent event) throws IOException {
        navigation.toTaskCreation(event);
    }
}
