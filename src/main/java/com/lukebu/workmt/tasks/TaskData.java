package com.lukebu.workmt.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskData {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    private static TaskData instance = new TaskData();

    public static TaskData getInstance() {
        return instance;
    }

    public ObservableList<Task> getTaskList() {
        return tasks;
    }

    public void addTaskToList(Task task) {
        instance.tasks.add(task);
    }

    public void removeFromTaskList(int index) {
        instance.tasks.remove(tasks.get(index));
    }

    public void modifyTaskList( int index, Task task) {
        instance.tasks.set(index,task);
    }

    public Task getTaskByIndex (int index) {
        return instance.tasks.get(index);
    }

    public void refreshTaskList(ObservableList<Task> tasks) {
        instance.tasks = tasks;
    }
}
