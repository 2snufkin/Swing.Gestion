package dAO;
import connection.Datasource;
import entities.Users;
import iDAO.IuserDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class UserDAO implements IuserDAO {
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_ID = "_id";
    public static final String COLUMN_USERS_NAME = "user";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_ROLE = "role";
    public static final int INDEX_USERS_ID = 1;
    public static final int INDEX_USERS_NAME = 2;
    public static final int INDEX_USERS_PASSWORD = 3;
    public static final int INDEX_USERS_ROLE = 4;
    public static Users u;
    static Statement stm;
    static Connection conn ;

    //My requets
    public  static final   String QFINDUSER = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_NAME + " = '" + u.getUser() + "';";
    public static final String QINSERT = "Insert into " + TABLE_USERS + "(" + COLUMN_USERS_NAME + " ," +
            COLUMN_USERS_PASSWORD + " ," + COLUMN_USERS_ROLE + " )  VALUES( '" +  u.getUser() + "', '" + u.getPassword() +
            "', '" + u.getRole() + "' );";
    public static final String QSELELCTALL = "SELECT * from " + TABLE_USERS + ",";

    //Creating an instance of the connection;


    public UserDAO() {
     conn = Datasource.getInstance();
    }

    @Override
    public void modifyUser(Users user) throws SQLException {

    }

    public boolean deleteUser(Users user) {

        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(QFINDUSER);
            if (rs.next()) {
                stm.execute("delete from " + TABLE_USERS + " where user = '" + user + "'");
                return true;

            } else {
                System.out.println("No such a user was found");
                return false;
            }

        } catch (Exception e) {
            System.out.println("The user name and password were not added " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteUser(Users user) throws SQLException {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(QFINDUSER);
            if (rs.next()) {
                rs.deleteRow();
               // stm.executeUpdate("delete from " + TABLE_USERS + " where user = '" + user + "'");
                return true;

            } else {
                System.out.println("No such a user was found");
                return false;
            }

        } catch (Exception e) {
            System.out.println("The user name and password were not added " + e.getMessage());
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void addUser(Users user)   {
       try {
           stm = conn.createStatement();
           stm.executeUpdate(QINSERT);
       }
       catch (SQLException e){
           System.out.println("problem with using the user");
       }

    }

    @Override
    public void afficherUsers(Users user) throws SQLException {
        Vector<Users> usersVector = new Vector<>();
        ResultSet res=  null;
        res = stm.executeQuery(QSELELCTALL);//select all
        while (res.next()){
            Users userObj = new Users();
            userObj.setId(res.getInt(INDEX_USERS_ID));
            userObj.setUser(res.getString(INDEX_USERS_NAME));
            userObj.setPassword(res.getString(INDEX_USERS_PASSWORD));
            userObj.setRole(res.getString(INDEX_USERS_ROLE));
            usersVector.add(userObj);

        }

    }
}
