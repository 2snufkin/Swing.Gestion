package dao;

import entities.Items;

import java.sql.SQLException;

public interface IdetailsDAO {

    public boolean addItem(Items items) throws SQLException;

    public boolean deleteItem(Items item)throws SQLException;

    public boolean modifyItem(Items item)throws SQLException;


    }

