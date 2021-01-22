package iDAO;

import entities.Items;
import entities.Users;

import java.sql.SQLException;
import java.util.Vector;


public interface IitemsDAO {
    void addItem(Items item)  throws SQLException;
    void deleteItem(Items item)  throws SQLException;
    void modifyItem(Items item)  throws SQLException;
    Vector <Items> displayItems()  throws SQLException;



}
