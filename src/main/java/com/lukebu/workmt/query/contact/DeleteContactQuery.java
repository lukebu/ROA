package com.lukebu.workmt.query.contact;

public class DeleteContactQuery {

    public String prepareQuery(int contactId) {

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM WMT_CONTACTS WHERE CNT_ID =");
        sb.append("'");
        sb.append(contactId);
        sb.append("'");

        result = sb.toString();

        return result;
    }
}
