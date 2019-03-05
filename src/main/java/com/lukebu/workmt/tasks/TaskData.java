package com.lukebu.workmt.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskData {

    private List<Task> tasks = new ArrayList<>();

    private static TaskData instance;

    public static TaskData getInstance() {
        return instance;
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public void addTaskToList(Task task) {
        instance.tasks.add(task);
    }
}
