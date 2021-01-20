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
    static Statement stm;
    static Connection conn;

    //My requets
    //find user by name or bu ID
    public static final String QFINDUSER = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_NAME + " = ?;";
    public static final String QFINDUSERBYID = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_ID + " = '";

    //insert the user - add()
    public static final String QINSERT = "Insert into " + TABLE_USERS + "(" + COLUMN_USERS_NAME + " ," +
            COLUMN_USERS_PASSWORD + " ," + COLUMN_USERS_ROLE + " )  VALUES(   ?  ,   ?  ,   ?  );";
    // select everything - afficher()
    public static final String QSELELCTALL = "SELECT * from " + TABLE_USERS + ";";

    //Delete user
    public static final String QDELETE = "DELETE * from " + TABLE_USERS + " WHERE " + COLUMN_USERS_ID +" = " ;




    private PreparedStatement PSfinduser;
    private PreparedStatement PSinsertuser;



    /**
     * this is the Class constructor
     * it has inside a mrthod to create an instance() of the connection and it holds the prepared Statments
     */
    public UserDAO() {
        try {
            conn = Datasource.getInstance();
            PSfinduser = conn.prepareStatement(QFINDUSER);
            PSinsertuser = conn.prepareStatement(QINSERT);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problem with the Connection instance or the Prepare Statment");
        }
    }

    /**
     * This method search the users table for a user with the @id if it founds - it returns it
     * if not - it returns a user
     *
     * @param id
     * @return User Object
     */
    public Users getUserByID(int id) {
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
            } else {
                JOptionPane.showMessageDialog(null, "This ID hasn't been found");
            }

        } catch (SQLException e) {


        }
        return u;
    }

    /**
     * add user to the database. if it is already exist. return its id
     * if it doesn't exist, add it and return its ID
     *
     * @param user
     * @return
     */
    @Override
    public int addUser(Users user) {
        try {
            PSfinduser.setString(1, user.getName());
            ResultSet rs = PSfinduser.executeQuery();
            //if it exist. give me his _id

            System.out.println(PSinsertuser.toString());
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "The user is already exists");
                return rs.getInt(1);
            } else {
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
    /**
     * a method that parcour the user Table and return a vector that include inside all the users
     * @return Vector<Users>
     */
    @Override
    public Vector<Users> afficherUsers() {
        Vector<Users> usersVector = new Vector<>();

        try (Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(QSELELCTALL)) {


            while (res.next()) {

                Users userObj = new Users();
                userObj.setName(res.getString(INDEX_USERS_NAME));
                userObj.setPassword(res.getString(INDEX_USERS_PASSWORD));
                userObj.setRole(res.getString(INDEX_USERS_ROLE));
                usersVector.add(userObj);
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "problem with display users");
        }
        return usersVector;
    }

    @Override
    public void modifyUser(Users user) throws SQLException {

    }

    @Override
    public void deleteUser(Users user)   {
        JOptionPane.showMessageDialog(null, "Are you sure you want to delete " + user.getName() + "?");
        StringBuilder sb = new StringBuilder(QDELETE);
        sb.append(user.getId());
        sb.append(";");
        System.out.println(sb);

        try {

             Statement stm = conn.createStatement();
             int sucd = stm.executeUpdate(sb.toString()) ;

            if (sucd == 1) JOptionPane.showMessageDialog(null, "The user has been deleted");
            else JOptionPane.showMessageDialog(null, "The user has not been deleted");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "There's a bug on you!", "SQL", JOptionPane.ERROR_MESSAGE);
        }


    }

//    public void deleteUser(int a)   {
//        JOptionPane.showMessageDialog(null, "Are you sure you want to delete " + user.getName() + "?");
//        StringBuilder sb = new StringBuilder(QDELETE);
//        sb.append(user.getId());
//        sb.append(";");
//        System.out.println(sb);
//
//        try {
//
//            Statement stm = conn.createStatement();
//            int sucd = stm.executeUpdate(sb.toString()) ;
//
//            if (sucd == 1) JOptionPane.showMessageDialog(null, "The user has been deleted");
//            else JOptionPane.showMessageDialog(null, "The user has not been deleted");
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(null, "There's a bug on you!", "SQL", JOptionPane.ERROR_MESSAGE);
//        }
//
//
//    }




    public static void main(String[] args) throws SQLException {

        UserDAO start = new UserDAO();

//        Users x = user.getUserByID(2);
//            System.out.println(x.getName());
//            System.out.println(x.getPassword());
//
//        user.getUserByID(3) ;
        Users user2 = new Users("Maygfgi", "beaugoss", "Admin");
        int idadduder = start.addUser(user2);
        System.out.println(idadduder);
        start.afficherUsers();
        Vector<Users> myvector = start.afficherUsers();
        for (Users i: myvector){
            System.out.println(i.getName()+  "," +  i.getRole());

        }
       // start.deleteUser(user2);
    }



}



