package com.lukebu.workmt.query;

public class LoginQuery {

    private String userLogin;
    private String userPassword;

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String prepareQuery() {
        String result;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM WMT_USERS WHERE USR_LOGIN =");
        sb.append("'");
        sb.append(userLogin);
        sb.append("'");
        sb.append("AND USR_PASSWORD =");
        sb.append("'");
        sb.append(userPassword);
        sb.append("'");

        result = sb.toString();
        return result;
    }

}
