package iDAO;


import entities.Users;

import java.sql.SQLException;

public interface IuserDAO {

        void modifyUser(Users user ) throws SQLException;
     void deleteUser(Users user ) throws SQLException;
     int addUser(Users user ) throws SQLException;
     void afficherUsers(Users user ) throws SQLException;

}
