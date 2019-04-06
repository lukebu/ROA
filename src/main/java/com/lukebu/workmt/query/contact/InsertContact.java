package com.lukebu.workmt.query.contact;
import com.lukebu.workmt.context.ClientContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InsertContact {

    public String prepareQuery(String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String email, String contactType) {

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO WMT_CONTACTS (CNT_ID, CNT_USR_ID, CNT_NAME, CNT_SURNAME, CNT_PHONE_NUMBER, CNT_DESK_NUMBER, CNT_EMAIL, CNT_TYPE) VALUES (NULL,");
        sb.append("'");
        sb.append(ClientContext.getInstance().getUserId());
        sb.append("', '");
        sb.append(contactName);
        sb.append("', '");
        sb.append(contactSurname);
        sb.append("', '");
        sb.append(contactMobilePhone);
        sb.append("', '");
        sb.append(contactDesktopPhone);
        sb.append("', '");
        sb.append(email);
        sb.append("', '");
        sb.append(contactType);
        sb.append("')");

        result = sb.toString();

        return result;
    }
}

