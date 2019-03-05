package com.lukebu.workmt.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connector {

    private Connection c = null;
    private Statement stmt = null;

    public void createConnectionToDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:file:C:\\Prywatne\\db\\wmt.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully!!!");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public ResultSet insertQueryStatement(final String insertQuery) {
        ResultSet rs = null;
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery(insertQuery);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
            return rs;
    }

    public int insertUpdateStatement(final String updateQuery) {
        int result = 0;

        try {
            stmt = c.createStatement();
            result = stmt.executeUpdate(updateQuery);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }


    public void closeConnectionWithCommit() {
        try {
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Connection closed");

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
