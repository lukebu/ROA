package com.lukebu.workmt.query.contact;

public class ModifyContact {

    public String prepareQuery(int contactId, String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String email, String contactType) {

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE WMT_CONTACTS SET CNT_NAME = ");
        sb.append("'");
        sb.append(contactName);
        sb.append("',CNT_SURNAME = '");
        sb.append(contactSurname);
        sb.append("', CNT_PHONE_NUMBER = '");
        sb.append(contactMobilePhone);
        sb.append("', CNT_DESK_NUMBER = '");
        sb.append(contactDesktopPhone);
        sb.append("', CNT_EMAIL = '");
        sb.append(email);
        sb.append("', CNT_TYPE = '");
        sb.append(contactType);
        sb.append("'WHERE CNT_ID = '");
        sb.append(contactId);
        sb.append("'");

        result = sb.toString();

        return result;
    }

}
