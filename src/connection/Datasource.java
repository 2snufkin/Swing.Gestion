package connection;

import entities.Users;

import javax.swing.*;
import java.sql.*;

/**
 * Created by timbuchalka on 9/12/16.
 */
public class Datasource {

    public static final String DB_NAME = "Gestion.db";
    private static Connection conn;
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\database\\" + DB_NAME;

    public static final int INDEX_USERS_ID = 1;
    public static final int INDEX_USERS_NAME = 2;
    public static final int INDEX_USERS_PASSWORD = 3;
    public static final int INDEX_USERS_ROLE = 4;

    public static final int INDEX_ORDERS_ID = 1;
    public static final int INDEX_ORDERS_PRICE = 2;
    public static final int INDEX_ORDERS_DATE = 3;



    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String ADMIN = "Administrator";
    public static final String INT = "INTEGER NOT NULL";
    public static final String TEXT = "TEXT NOT NULL";
    public static final String REAL = "REAL NOT NULL";

    public static final String UNIQUE = "UNIQUE";

    public static final String LIMIT = "Limit";

    private static boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }
    public static Connection getInstance(){
        if (conn == null) open();
        return conn;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

        public static void main (String[] args) throws SQLException {
            Datasource data = new Datasource();
            data.open();



        }

    }























