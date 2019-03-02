package com.lukebu.workmt.query;

import com.lukebu.workmt.context.ClientContext;

import java.time.LocalDateTime;

public class SelectTasksQuery {
    private String taskName;
    private String taskDescription;
    private LocalDateTime dueDate;

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String prepareQuery() {

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM WMT_TASKS WHERE TSK_USR_ID =");
        sb.append("'");
        sb.append(ClientContext.getInstance().getUserId());
        sb.append("'");

        result = sb.toString();
        return result;
    }
}
