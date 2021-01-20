package dAO;
import connection.Datasource;
import entities.Users;
import iDAO.IuserDAO;

import javax.swing.*;
import java.sql.*;
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
    //find user by name
    public  static final   String QFINDUSER = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_NAME + " = ?;";
    public  static final   String QFINDUSERBYID = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_ID + " = '";

    //insert the user
    public static final String QINSERT = "Insert into " + TABLE_USERS + "(" + COLUMN_USERS_NAME + " ," +
            COLUMN_USERS_PASSWORD + " ," + COLUMN_USERS_ROLE + " )  VALUES( ' ? ', ' ? ', ' ? ');";

    public static final String QSELELCTALL = "SELECT * from " + TABLE_USERS + ",";
    private PreparedStatement PSfinduser;
    private PreparedStatement PSinsertuser;

    //Creating an instance of the connection;

    /**
     * this is the Class constructor
     *it has inside a mrthod to create an instance() of the connection and it holds the prepared Statments
     */
    public UserDAO() {
        try{
            conn = Datasource.getInstance();
            PSfinduser = conn.prepareStatement(QFINDUSER);
            PSinsertuser = conn.prepareStatement(QINSERT);

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Problem with the Connection instance or the Prepare Statment");
        }
    }



    /**
     * This method search the users table for a user with the @id if it founds - it returns it
     * if not - it returns a user
     * @param id
     * @return User Object
     */
    public Users getUserByID(int id ) {
        Users u = new Users();
        StringBuilder stb = new StringBuilder(QFINDUSERBYID);
        stb.append(id);
        stb.append("';");
        try {


            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(stb.toString());
            if (rs.next()) {
                u.setName(rs.getString(COLUMN_USERS_NAME));
                u.setPassword(rs.getString(COLUMN_USERS_PASSWORD));
                u.setRole(rs.getString(COLUMN_USERS_ROLE));
            }
            else {
                JOptionPane.showMessageDialog(null, "This ID hasn't been found");
            }

        } catch (SQLException e) {


        }
        return u;
    }

    @Override
    public void modifyUser(Users user) throws SQLException {

    }

    @Override
    public void deleteUser(Users user) throws SQLException {

    }

    @Override
    public int addUser(Users user)  {
        try{
            PSfinduser.setString(1, user.getName());
            ResultSet rs = PSfinduser.executeQuery();
            //if it exist. give me his _id
            if (rs.next()) { return rs.getInt(1);}
            else {
                //if not. add the user. 1: prepaere the query
                PSinsertuser.setString(1, user.getName());
                PSinsertuser.setString(2, user.getPassword());
                PSinsertuser.setString(3, user.getRole());
                //excute and verify that it has been done
                int affectedRow = PSinsertuser.executeUpdate();
                if (affectedRow != 1)
                    throw new SQLException("A user hasn't been added");

            }

        } catch (Exception e) {
            System.out.println("The user name and password were not added " + e.getMessage());
            e.printStackTrace();
        }
        return user.getId();
     }

    @Override
    public void afficherUsers(Users user) throws SQLException {
        Vector<Users> usersVector = new Vector<>();
        ResultSet res=  null;
        res = stm.executeQuery(QSELELCTALL);//select all
        while (res.next()){
            Users userObj = new Users();
            userObj.setId(res.getInt(INDEX_USERS_ID));
            userObj.setName(res.getString(INDEX_USERS_NAME));
            userObj.setPassword(res.getString(INDEX_USERS_PASSWORD));
            userObj.setRole(res.getString(INDEX_USERS_ROLE));
            usersVector.add(userObj);

        }

    }


        public  static void main(String [] args){

        UserDAO user = new UserDAO();

        Users x = user.getUserByID(2);
            System.out.println(x.getName());
            System.out.println(x.getPassword());

        user.getUserByID(3) ;
        Users user2 = new Users("Pini", "2222", "Admin");




        }


 }



