package com.company.datastorage;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Connection connection;
    private String host = "localhost";
    private String user = "netflixuser";
    private String databaseName = "NetflixStatistix";

    @Test
    public void getInstance() {

        Database database = null;

        if (database == null) {
            try {
                database = new Database();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        assertNotNull(database);

    }

    @Test
    public void query() {

        String query = "SELECT * FROM Film";

        String connectionUrl = "jdbc:sqlserver://"+this.host+";user="+this.user+";databaseName="+this.databaseName+";integratedSecurity=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try {
            statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet resultSet = statement.executeQuery(query);
            assertTrue(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryDDL() {

        String query = "UPDATE Film SET Film.Titel='test' WHERE Film.Titel = 'test';";

        String connectionUrl = "jdbc:sqlserver://"+this.host+";user="+this.user+";databaseName="+this.databaseName+";integratedSecurity=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try {
            statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int i = statement.executeUpdate(query);
            System.out.print(i);
            assertTrue(i == 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}