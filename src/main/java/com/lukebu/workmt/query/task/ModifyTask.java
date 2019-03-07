package com.lukebu.workmt.query.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ModifyTask {

    public String prepareQuery(int taskId, String taskName, String taskDescription, LocalDate taskDueDate) {

        DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
        LocalTime localTime = LocalTime.NOON;
        LocalDateTime localDateTime = LocalDateTime.of(taskDueDate, localTime);

        String from = readFormatter.format(localDateTime);

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE WMT_TASKS SET TSK_NAME = ");
        sb.append("'");
        sb.append(taskName);
        sb.append("',TSK_DESCRIPTION = '");
        sb.append(taskDescription);
        sb.append("', TSK_DUE_DATE = '");
        sb.append(from);
        sb.append("'WHERE TSK_ID = '");
        sb.append(taskId);
        sb.append("'");

        result = sb.toString();

        return result;
    }

}
