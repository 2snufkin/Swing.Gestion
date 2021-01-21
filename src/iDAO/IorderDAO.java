package iDAO;
// only display
import entities.Orders;

import java.sql.SQLException;
import java.util.Vector;

public interface IorderDAO  {

   Vector<Orders> displayOrders() throws  SQLException;

 }
