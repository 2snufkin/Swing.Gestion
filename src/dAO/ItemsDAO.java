package dAO;

import entities.Items;
import connection.Datasource;
import entities.Orders;
import entities.Users;
import iDAO.IitemsDAO;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;


public class ItemsDAO  implements IitemsDAO {

    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEMS_ID = "_id";
    public static final String COLUMN_ITEMS_NAME = "name";
    public static final String COLUMN_ITEMS_PRICE = "price";
    public static final int INDEX_ITEMS_ID = 1;
    public static final int INDEX_ITEMS_NAME = 2;
    public static final int INDEX_ITEMS_PRICE = 3;
    static Connection conn;
    //Insert Item
    public static final String QINSERTItem = "Insert into " + TABLE_ITEMS + "(" + COLUMN_ITEMS_NAME + " ," +
            COLUMN_ITEMS_PRICE + " )  VALUES(   ?  ,   ?  ,   ?  );";
    //Find by Item Name
    public static final String QFINITEMByName = "SELECT * from " + TABLE_ITEMS + " where " + COLUMN_ITEMS_NAME + " = ?;";
    //Delete Item
    public static final String QDELETE = "DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMS_NAME + " = ";
    //Update
    public final String QUPDATEITEM = "UPDATE " + TABLE_ITEMS + " SET " + COLUMN_ITEMS_NAME +" = ? , " +
            COLUMN_ITEMS_PRICE + " = ? "
            +"WHERE " + COLUMN_ITEMS_NAME + " =  ? ";
    //afficher
    public static final String QSELELCTALL = "SELECT * from " + TABLE_ITEMS + ";";

    PreparedStatement  PSfindItem;
    PreparedStatement  PSinsertItem;
    PreparedStatement  PSupdateItem;

    public String tempName;
    public Double tempPrice;

    public ItemsDAO() {
        conn = Datasource.getInstance();
        try{
            PSfindItem = conn.prepareStatement(QFINITEMByName);
            PSinsertItem = conn.prepareStatement(QINSERTItem);
            PSupdateItem = conn.prepareStatement(QUPDATEITEM);

        } catch(SQLException e){

        }
    }

    /**
     * Add Object Item to the DB
     * @param item
     */
    @Override
    public void addItem(Items item)   {
        try {

            PSfindItem.setString(1, item.getName());
            ResultSet rs = PSfindItem.executeQuery();
             if (rs.next()) {
                JOptionPane.showMessageDialog(null, "The item is already exists" );
            } else {
                //if not. add the user. 1: prepaere the query
                PSinsertItem.setString(1, item.getName());
                PSinsertItem.setDouble(2, item.getPrice());

                //excute and verify that it has been done
                int affectedRow = PSinsertItem.executeUpdate();
                if (affectedRow != 1)
                    throw new SQLException("An item hasn't been added");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * Delete an Item Object of the DB
     * @param item
     */
    @Override
    public void deleteItem(Items item)  {
        // Verifying that the Admin is sure
        int n = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete " + item.getName() + " ?",
                "Delete",
                JOptionPane.YES_NO_OPTION);// no -> 1 yes -> 0
        if (n==1) System.exit(1);

        //building the Query
        StringBuilder sb = new StringBuilder(QDELETE);
        sb.append("'");
        sb.append(item.getName());
        sb.append("';");

        try {
            Statement stm = conn.createStatement();
            int sucd = stm.executeUpdate(sb.toString()) ;
            if (sucd == 1) JOptionPane.showMessageDialog(null, "The Item has been deleted");
            else JOptionPane.showMessageDialog(null, "The Item has not been found");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "There's a SQL bug on you!", "SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Modify an Item in your DB
     * @param item
     */
    @Override
    public void modifyItem(Items item)   {
        String nameItem = item.getName();
        int exist = searchItemByName(nameItem);
        //check if the item exist:
        //if it doesnt exist its 1 if its exist its (-1)
        try {

            PSupdateItem.setString(1, tempName);
            PSupdateItem.setDouble(2, tempPrice);
            PSupdateItem.setString(3,nameItem);

            if (exist != -1) {
                int result = PSupdateItem.executeUpdate();
                //I want to check that it has been done
                if (result == 1) JOptionPane.showMessageDialog(null, "The user has been updated");


            } else {
                JOptionPane.showMessageDialog(null, "The user has  not been updated. it exists");
            }

        } catch (SQLException e) {
            System.out.println("Hi a probleme with updating");
        }
    }

    /**
     * Preform the select All requet and return a vector the fill with Item's name and Item's Price
     * @param
     * @return Vector<Items> no ID included
     */
    @Override
    public  Vector<Items> displayItems()  {
        Vector<Items> displayitems = new Vector<>();

        try (Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(QSELELCTALL)) {


            while (res.next()) {
                Items orderObj = new Items();
                orderObj.setName(res.getString(INDEX_ITEMS_NAME));
                orderObj.setPrice(res.getDouble(INDEX_ITEMS_PRICE));

                displayitems.add(orderObj);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "problem with display users");
        }
        return displayitems;
    }

    /**
     * Search Item by Artist
      * @param name
     * @return 1 if it  exist and -1 is it doesnt exist
     */
    public int searchItemByName(String name) {

        //change only here
        int returnInt = 0;
        try {
            PSfindItem.setString(1, name);
            ResultSet rs = PSfindItem.executeQuery();
            if (rs.next()) {
                System.out.println("The item is already exists");
                returnInt = -1;

            }
            else returnInt = 1;
        } catch (SQLException e) {
            System.out.println("SQL probleme"); }
        return returnInt;
    }

    public static void main(String[] args)  {
        ItemsDAO start = new ItemsDAO();
        Vector<Items> myvector = start.displayItems();
        for (Items i : myvector) {
            System.out.println(i.getName() + "," + i.getPrice() + " " + String.valueOf(i.getId()));

        }
    }
}

 