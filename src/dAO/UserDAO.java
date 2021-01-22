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
    public String tempName = "Update";
    public String tempPass = "Jonpassffword";
    public String tempRole = "0";
    //My requets
    //find user by name or bu ID
    public static final String QFINDUSERByName = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_NAME + " = ?;";
    public static final String QFINDUSERBYID = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_ID + " = '";
    //insert the user - add()
    public static final String QINSERT = "Insert into " + TABLE_USERS + "(" + COLUMN_USERS_NAME + " ," +
            COLUMN_USERS_PASSWORD + " ," + COLUMN_USERS_ROLE + " )  VALUES(   ?  ,   ?  ,   ?  );";
    // select everything - afficher()
    public static final String QSELELCTALL = "SELECT * from " + TABLE_USERS + ";";
    //Delete user
    public static final String QDELETE = "DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_NAME + " = ";


    public final String QUPDATE = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USERS_NAME +" = ? , " +
           COLUMN_USERS_PASSWORD + " = ? , " + COLUMN_USERS_ROLE + " = ?  "
            +"WHERE " + COLUMN_USERS_NAME + " =  ? ";
    static Statement stm;
    static Connection conn;
    private PreparedStatement PSfinduser;
    private PreparedStatement PSinsertuser;
    private PreparedStatement PSupdateUser;


    /**
     * this is the Class constructor
     * it has inside a mrthod to create an instance() of the connection and it holds the prepared Statments
     */
    public UserDAO() {

        conn = Datasource.getInstance();

        try {
//    public static final String QFINDUSERByName = "SELECT * from " + TABLE_USERS + " where " + COLUMN_USERS_NAME + " = ?;";
            PSfinduser = conn.prepareStatement(QFINDUSERByName);
            PSinsertuser = conn.prepareStatement(QINSERT);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problem with the Prepared Statement");
        }
    }


    public void close() {
        try {
            if (conn != null)
                conn.close();
            if (PSinsertuser != null)
                PSinsertuser.close();
            if (PSfinduser != null)
                PSfinduser.close();


        } catch (SQLException e) {
            System.out.println("probleme with closing the connection");
        }
    }

    /**
     *
     * @param name
     * @return 1 if it  exist and -1 is it doesnt exist
     */
    public int searchUserByName(String name) {

        //change only here
        int returnInt = 0;
        try {
            PSfinduser.setString(1, name);
            ResultSet rs = PSfinduser.executeQuery();
            if (rs.next()) {
                System.out.println("The user is already exists");
                returnInt = -1;

            }
            else returnInt = 1;
        } catch (SQLException e) {
        System.out.println("SQL probleme"); }
        return returnInt;
    }


    /**
     * This method delete a given user if he is found in the DB
     *
     * @param user
     */
    @Override
    public void deleteUser(Users user)   {
        // Verifying that the Admin is sure
        int n = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete " + user.getName() + " ?",
                "Delete",
                JOptionPane.YES_NO_OPTION);// no -> 1 yes -> 0
        if (n==1) System.exit(1);

        //building the Query
        StringBuilder sb = new StringBuilder(QDELETE);
        sb.append( "'" +user.getName() + "';");
        System.out.println(sb);

        try {

            Statement stm = conn.createStatement();
            int sucd = stm.executeUpdate(sb.toString()) ;

            if (sucd == 1) JOptionPane.showMessageDialog(null, "The user has been deleted");
            else JOptionPane.showMessageDialog(null, "The user has not been found");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "There's a bug on you!", "SQL", JOptionPane.ERROR_MESSAGE);
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
        stb.append(id + "';");

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
    public void addUser(Users user){
        try {
            PSfinduser.setString(1, user.getName());
            ResultSet rs = PSfinduser.executeQuery();
            System.out.println(PSinsertuser.toString());
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "The user is already exists" );
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

        } catch (SQLException e) {
            System.out.println("The user name and password were not added " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * a method that parcour the user Table and return a vector that include inside all the users
     *
     * @return Vector<Users>
     */
    @Override
    public Vector<Users> afficherUsers() {
        Vector<Users> usersVector = new Vector<>();

        try (Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(QSELELCTALL)) {



            while (res.next()) {

                Users userObj = new Users();
                userObj.setId(res.getInt(INDEX_USERS_ID));
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

    /**
     * This method modify the user to a predefined values tempName tempPass tempRole
     * @param user
     */
    @Override
    public void modifyUser(Users user)  {

        int exist = searchUserByName(tempName);
        //check if the user exist:
        //if it doesnt exist its 1 if its exist its (-1)
        try {

//            public final String QUPDATE = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USERS_NAME +" = ? , " +
//                    COLUMN_USERS_PASSWORD + " = ? , " + COLUMN_USERS_ROLE + " = ?  "
//                    +"WHERE " + COLUMN_USERS_NAME + " =  ? ";
            PSupdateUser = conn.prepareStatement(QUPDATE);
            PSupdateUser.setString(1, tempName);
            PSupdateUser.setString(2, tempPass);
            PSupdateUser.setString(3, tempRole);
            PSupdateUser.setString(4, user.getName());
             if (exist != -1) {
                int result = PSupdateUser.executeUpdate();
                 //I want to check that it has been done
                if (result == 1) JOptionPane.showMessageDialog(null, "The user has been updated");


            } else {
                JOptionPane.showMessageDialog(null, "The user has  not been updated. it exists");
            }

        } catch (SQLException e) {
            System.out.println("Hi a probleme with updating");
        }
    }

    public static void main(String[] args)  {

        UserDAO start = new UserDAO();

        Users user1 = new Users("Avi", "beaugoss", "1");
        Users user2 = new Users("Pini", "Hkjklldf", "0");
        Users user3 = new Users("Oren", "1221545", "1");
        Users user4 = new Users("Itzahk", "545fgf45", "0");
        Users user5 = new Users("Yakov", "gf54545", "1");
        Users user6 = new Users("Benyamin", "5454gfg5", "0");
        Users user7 = new Users("Asher", "5sdsq4545", "1");
        Users user8 = new Users("Levi", "54545gfg", "0");

        start.modifyUser(user3);
       // System.out.println(start.searchUserByName("Asher"));
       // System.out.println(start.searchUserByName("g"));


 /*start.addUser(user6);
      start.addUser(user2);
       start.addUser(user1);
          start.addUser(user3);
        start.addUser(user4);
          start.addUser(user5);
          start.addUser(user7);
          start.addUser(user6);
         start.addUser(user8);*/
//    start.deleteUser(user4);
//    start.deleteUser(user4);
   // start.deleteUser(user6);



 Vector<Users> myvector = start.afficherUsers();
     for (Users i : myvector) {
        System.out.println(i.getName() + "," + i.getRole() + " " + String.valueOf(i.getId()));

       }


    }


}



