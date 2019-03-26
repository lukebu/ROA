package com.lukebu.workmt.events.task;

import com.lukebu.workmt.events.Event;
import com.lukebu.workmt.tasks.Task;

public class ModifyTaskEvent extends Event {

    private static ModifyTaskEvent instance = new ModifyTaskEvent();

    public static ModifyTaskEvent getInstance() {
        return instance;
    }
    private Task task;

    public void setTaskToModify(Task t) {
        instance.task = t;
    }

    public Task getTask() {
        return instance.task;
    }
}
