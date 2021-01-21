package iDAO;

import entities.Details;
import entities.Orders;
import entities.Users;

import java.sql.SQLException;
import java.util.Vector;

public interface IdetailsDAO {
    void commit(Details detail) throws SQLException;


}

