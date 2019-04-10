package com.company.datastorage;

import java.sql.*;

/**
 * Class Database is a singleton class that handles the database implementation
 */
public class Database {

    // Singleton class object
    private static Database database;

    // DB Settings
    private String host = "localhost";
    private String user = "netflixuser";
    private String databaseName = "NetflixStatistix";

    // Connection object
    private Connection connection;

    /**
     * Class constructor for Database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private Database() throws SQLException, ClassNotFoundException {

        String connectionUrl = "jdbc:sqlserver://"+this.host+";user="+this.user+";databaseName="+this.databaseName+";integratedSecurity=true;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.connection = DriverManager.getConnection(connectionUrl);
    }

    /**
     * Returns an instantiated database connection
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static synchronized Database getInstance() throws SQLException, ClassNotFoundException {
        if (database == null) {
            database = new Database();
        }

        return database;
    }

    /**
     * Returns a resultset for a particular query
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException {

        Statement statement = this.connection.createStatement();

        // Execute query
        ResultSet resultSet = statement.executeQuery(query);

        return resultSet;
    }

    /**
     * Executes a DDL operation so it returns nothing
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public void queryDDL(String query) throws SQLException {

        Statement statement = this.connection.createStatement();

        // Execute query
        statement.executeUpdate(query);
    }


}
