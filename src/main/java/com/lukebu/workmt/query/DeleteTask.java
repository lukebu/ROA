package com.lukebu.workmt.query;

public class DeleteTask {

    public String prepareQuery(int taskId) {

        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM WMT_TASKS WHERE TSK_ID =");
        sb.append("'");
        sb.append(taskId);
        sb.append("'");

        result = sb.toString();

        return result;
    }
}
