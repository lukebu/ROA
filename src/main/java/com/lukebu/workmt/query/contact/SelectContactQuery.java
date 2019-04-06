package com.lukebu.workmt.query.contact;

import com.lukebu.workmt.context.ClientContext;

import java.time.LocalDateTime;

public class SelectContactQuery {
    private String taskName;
    private String taskDescription;
    private LocalDateTime dueDate;

    public String prepareQuery() {

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM WMT_CONTACTS WHERE CNT_USR_ID =");
        sb.append("'");
        sb.append(ClientContext.getInstance().getUserId());
        sb.append("'");

        result = sb.toString();
        return result;
    }
}
