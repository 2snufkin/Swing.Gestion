package dao;


import entities.Users;

import java.sql.SQLException;

public interface IuserDAO {

     boolean modifyUser(Users user ) throws SQLException;
     boolean deleteUser(Users user ) throws SQLException;
     boolean addUser(Users user ) throws SQLException;

}
