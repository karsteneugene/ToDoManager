package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.ToDoData;

import java.io.IOException;

public class Main extends Application {

    // Loads the desired window
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Loads the Home view
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Home.fxml"));

        primaryStage.setTitle("Time Management");           // Sets the title of the window

        // Sets the Home view as the first scene
        Scene scene = new Scene(root, 854, 480);

        // Sets the window's scene
        primaryStage.setScene(scene);

        // Prevents user from resizing window ( because of UI reasons )
        primaryStage.setResizable(false);

        // Displays the window
        primaryStage.show();
    }

    // Launches the program
    public static void main(String[] args) {
        launch(args);
    }

    // Loads the tasks that were saved in the txt file when the program launches
    @Override
    public void init() throws IOException {
        ToDoData.getInstance().loadTasks();
    }

    // Store the tasks that were inputted by the user into a txt file when the program closes
    @Override
    public void stop() throws IOException {
        ToDoData.getInstance().storeTasks();
    }
}
