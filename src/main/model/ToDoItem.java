package main.model;

import java.time.LocalDate;

// A data model for ToDoItem

public class ToDoItem {

    // Declare the appropriate variables for a ToDoItem
    private LocalDate dueDate;
    private String taskTitle;
    private boolean important;

    // Constructor for a ToDoItem
    public ToDoItem(String taskTitle, LocalDate dueDate, boolean important) {
        this.taskTitle = taskTitle;
        this.dueDate = dueDate;
        this.important = important;
    }

    // Setters and getters for all the variables
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
