package com.lukebu.workmt.context;

public class ClientContext {

    private int userId;

    private static ClientContext clientContext = new ClientContext();

    public static ClientContext getInstance() {
        return clientContext;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public void prepareClientContext(int userId) {
        getInstance().setUserId(userId);
    }
}
