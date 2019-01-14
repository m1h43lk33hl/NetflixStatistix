package com.company;

import java.sql.*;


public class Database {

    // Singleton class object
    private static Database database;

    // DB Settings
    private String host = "localhost";
    private String user = "netflixuser";
    private String databaseName = "NetflixStatistix";

    // Connection object
    private Connection connection;

    // Constructor set to private because it is a singleton class
    private Database() throws SQLException, ClassNotFoundException {

        String connectionUrl = "jdbc:sqlserver://"+this.host+";user="+this.user+";databaseName="+this.databaseName+";integratedSecurity=true;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.connection = DriverManager.getConnection(connectionUrl);
    }


    public static synchronized Database getInstance() throws SQLException, ClassNotFoundException {
        if (database == null) {
            database = new Database();
        }

        return database;
    }

    public ResultSet query(String query) throws SQLException {

        Statement statement = this.connection.createStatement();

        // Execute query
        ResultSet resultSet = statement.executeQuery(query);

        return resultSet;
    }

}
