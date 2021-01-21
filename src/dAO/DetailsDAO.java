package dAO;

 import entities.Details;
 import entities.Items;
 import iDAO.IdetailsDAO;
 import javax.swing.*;
 import java.sql.*;


public class DetailsDAO implements IdetailsDAO {

 public static final String TABLE_DETAILS = "details";
 public static final String COLUMN_DETAILS_ID = "_id";
 public static final String COLUMN_DETAILS_ID_ITEM = "_idItem";
 public static final String COLUMN_DETAILS_QUANTITY = "quantity";
 public static final String COLUMN_DETAILS_ID_COMMANDE = "_idOrder";
 public static final int INDEX_DETAILS_ID = 1;
 public static final int INDEX_DETAILS_NAME = 2;
 public static final int INDEX_DETAILS_PASSWORD = 3;
 public static final int INDEX_DETAILS_ROLE = 4;
 static Connection conn;
 public static final String QDELETE = "DELETE FROM " + TABLE_DETAILS + " WHERE " + COLUMN_DETAILS_ID_ITEM + " = ";

 PreparedStatement  PSfindDetails;
 public static final String QINSERTItem = "Insert into " + TABLE_DETAILS + "(" + COLUMN_DETAILS_ID_ITEM + " ," +
         COLUMN_DETAILS_QUANTITY + " ," +COLUMN_DETAILS_ID_COMMANDE  +   " ) VALUES(   ?  ,   ?  ,   ?  );";
  PreparedStatement PSinsertDetail;



 @Override
 public void commit(Details details) throws SQLException {
  try {
   PSinsertDetail = conn.prepareStatement(QINSERTItem);
   System.out.println(QINSERTItem);
    PSinsertDetail.setInt(1, details.getIdItem());
    PSinsertDetail.setInt(2, details.getQuantity());
    PSinsertDetail.setInt(3, details.getIdOrder());

    //excute and verify that it has been done
    int affectedRow = PSinsertDetail.executeUpdate();
    if (affectedRow != 1) JOptionPane.showMessageDialog(null, "An item hasn't been added");

  } catch (SQLException e) {
   System.out.println(e.getMessage());
   e.printStackTrace();
  }
 }





 }

