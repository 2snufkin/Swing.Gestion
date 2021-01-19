package iDAO;

import entities.Items;
import java.sql.SQLException;


public interface ItemsDAO {
    boolean modifyUser(Items user ) throws SQLException;
    boolean deleteUser(Items user ) throws SQLException;
    boolean addUser(Items user ) throws SQLException;

}
