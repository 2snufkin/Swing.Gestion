

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timbuchalka on 9/12/16.
 */
public class Datasource {

    public static final String DB_NAME = "Gestion.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\database\\" + DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_ID = "_id";
    public static final String COLUMN_USERS_NAME = "user";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_ROLE = "role";
    public static final int INDEX_USERS_ID = 1;
    public static final int INDEX_USERS_NAME = 2;
    public static final int INDEX_USERS_PASSWORD = 3;
    public static final int INDEX_USERS_ROLE = 4;

    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEMS_ID = "_id";
    public static final String COLUMN_ITEMS_NAME = "name";
    public static final String COLUMN_ITEMS_PRICE = "price";
    public static final int INDEX_ITEMS_ID = 1;
    public static final int INDEX_ITEMS_NAME = 2;
    public static final int INDEX_ITEMS_PRICE = 3;

    public static final String TABLE_ORDERS = "songs";
    public static final String COLUMN_ORDERS_ID = "_id";
    public static final String COLUMN_ORDERS_PRICE = "price";
    public static final String COLUMN_ORDERS_DATE = "date";
 
    public static final int INDEX_ORDERS_ID = 1;
    public static final int INDEX_ORDERS_PRICE = 2;
    public static final int INDEX_ORDERS_DATE = 3;

    public static final String TABLE_DETAILS = "details";
    public static final String COLUMN_DETAILS_ID = "_id";
    public static final String COLUMN_DETAILS_ID_ITEM = "_idItem";
    public static final String COLUMN_DETAILS_QUANTITY = "quantity";
    public static final String COLUMN_DETAILS_ID_COMMANDE= "_idCommande";
    public static final int INDEX_DETAILS_ID = 1;
    public static final int INDEX_DETAILS_NAME = 2;
    public static final int INDEX_DETAILS_PASSWORD = 3;
    public static final int INDEX_DETAILS_ROLE = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String ADMIN = "Administrator";
    public static final String INT = "INTEGER NOT NULL";
    public static final String TEXT = "TEXT NOT NULL";

    public static final String LIMIT = "Limit";






    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public void CreateUsers (){
        String stmCreate = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("+COLUMN_USERS_ID +" " +INT+ ", " +
        COLUMN_USERS_NAME + " " + TEXT + ", " + COLUMN_USERS_PASSWORD + " " +TEXT + ", " + COLUMN_USERS_ROLE + " " +
        TEXT + ", " +"PRIMARY KEY (" +COLUMN_USERS_ID + "));";
       // System.out.println(stmCreate);
        try(Statement stm = conn.createStatement()) {
            stm.executeUpdate(stmCreate);
           }
        catch (SQLException e ){
            System.out.println("probleme with creating the users table");
        }


    }

    public void  add(String user, String pass,String role ){
        String query = "Insert into " + TABLE_USERS + "VALUES( '" + user +"', '" + pass +"', '" + role +"' );";
       // try( Statement stm = conn.createStatement();){

        System.out.println(query);
             }
//        catch(SQLException e){
//            System.out.println("Problem with adding a user" + e.getMessage());
//        }



    public static void main (String [] args){
        Datasource data = new Datasource();
        data.open();
     //  data.CreateUsers();
       data.add("pini", "pini",ADMIN);
      }

}
















