package iDAO;

//add - display - delete - modify
import entities.Users;

import java.sql.SQLException;
import java.util.Vector;

public interface IuserDAO {

     void modifyUser(Users user) throws SQLException;

     void deleteUser(Users user) throws SQLException;

     void addUser(Users user) throws SQLException;

     Vector<Users> afficherUsers() throws SQLException;
}
