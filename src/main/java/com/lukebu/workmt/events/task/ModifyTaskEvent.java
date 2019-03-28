package com.lukebu.workmt.events.task;

import com.lukebu.workmt.events.Event;
import com.lukebu.workmt.tasks.Task;

public class ModifyTaskEvent extends Event {
    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    public Task returnTask() {
        return this.task;
    }
}
