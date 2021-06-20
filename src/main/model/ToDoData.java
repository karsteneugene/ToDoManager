package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Iterator;
import java.util.Locale;

// A data model for ToDoData ( which does some processing to ToDoItems )

public class ToDoData {

    private static ToDoData instance = new ToDoData();  // Declare a new instance for ToDoData class
    private static String fileName = "Tasks.txt";       // Declare a file name for storing Tasks

    // Declare a list for ToDoItem, an observable list allows listeners to track changes
    private ObservableList<ToDoItem> toDoItems;

    // Declare a formatter for Dates so that the date can be properly read from the txt file
    private DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd-MMM-yyyy")
            .toFormatter(Locale.ENGLISH);

    // Constructor to change the format of the Date
    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    }

    // Getter for the ToDoData instance to be able to access the methods from other classes
    public static ToDoData getInstance() {
        return instance;
    }

    // Getter for the list
    public ObservableList<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    // Method to get the number of tasks inside the list
    public int getTaskAmount() { return toDoItems.size(); }

    // Method to add a task to the list
    public void addToDoItem(ToDoItem item) {
        toDoItems.add(item);
    }

    // Method to load tasks from the text file
    public void loadTasks() throws IOException {
        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);                        // Gets the path of the text file
        BufferedReader br = Files.newBufferedReader(path);      // Open the text file using a Buffered Reader

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split(",");     // Splits each column using commas

                String taskTitle = itemPieces[0];           // Task title for the first column
                String dueDateString = itemPieces[1];       // Due date for the second column
                String importantString = itemPieces[2];     // A true or false of whether it is important for third column

                LocalDate dueDate = LocalDate.parse(dueDateString, formatter);      // Reads the date using the formatter
                boolean important = Boolean.parseBoolean(importantString);          // Reads the boolean value for important
                ToDoItem toDoItem = new ToDoItem(taskTitle, dueDate, important);    // Makes a new ToDoItem using constructor
                toDoItems.add(toDoItem);    // Loads the item into the program
            }
        } finally {
            if(br != null) {
                br.close();     // Once there is no more lines, close the reader
            }
        }
    }

    // Method to store tasks into the text file
    public void storeTasks() throws IOException {
        Path path = Paths.get(fileName);                        // Gets the path of the text file
        BufferedWriter bw = Files.newBufferedWriter(path);      // Open the text file using a Buffered Writer

        String input;

        try {
            Iterator<ToDoItem> iterator = toDoItems.iterator();     // Declare an iterator for the ToDoItems list

            // While loop to get the tasks from the list
            while (iterator.hasNext()) {
                ToDoItem toDoItem = iterator.next();
                bw.write(String.format("%s,%s,%s",                   // Writes into the text file by separating each column with commas
                        toDoItem.getTaskTitle(),
                        toDoItem.getDueDate().format(formatter),
                        toDoItem.isImportant()));
                bw.newLine();       // Write each item on a new line
            }
        } finally {
            if (bw != null) {
                bw.close();         // Once there is no more lines, close the writer
            }
        }
    }

    // Method to delete a task from the ToDoItems list
    public void deleteTask(ToDoItem toDoItem) {
        toDoItems.remove(toDoItem);
    }
}
