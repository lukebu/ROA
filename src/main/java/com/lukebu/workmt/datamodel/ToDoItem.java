package com.lukebu.workmt.datamodel;
import java.time.LocalDateTime;

public class ToDoItem {

    private String taskName;
    private String taskDescription;
    private LocalDateTime dueDate;

    public ToDoItem(String taskName, String taskDescription, LocalDateTime dueDate) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return taskName.toString();
    }
}
