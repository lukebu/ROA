package com.lukebu.workmt.query;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InsertNewTask {

    private Task task;

    public String prepareQuery(String taskName, String taskDescription, LocalDate taskDueDate) {

        DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
        LocalTime localTime = LocalTime.NOON;
        LocalDateTime localDateTime = LocalDateTime.of(taskDueDate, localTime);

        String from = readFormatter.format(localDateTime);

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO WMT_TASKS (TSK_ID, TSK_USR_ID, TSK_NAME, TSK_DESCRIPTION, TSK_DUE_DATE) VALUES (NULL,");
        sb.append("'");
        sb.append(ClientContext.getInstance().getUserId());
        sb.append("', '");
        sb.append(taskName);
        sb.append("', '");
        sb.append(taskDescription);
        sb.append("', '");
        sb.append(from);
        sb.append("')");

        result = sb.toString();

        return result;
    }
}

