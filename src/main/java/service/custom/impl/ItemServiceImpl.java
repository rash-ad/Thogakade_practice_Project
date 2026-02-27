package service.custom.impl;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Item;
import service.custom.ItemService;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    @Override
    public boolean addItem(Item item) {
        PreparedStatement psTm;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println("Connection " + connection);
            psTm = connection.prepareStatement("Insert into item values (?,?,?,?,?,?,?,?,?) ");
            psTm.setString(1, item.getItemCode());
            psTm.setString(2, item.getDescription());

            psTm.setString(4, item.getPackSize());
            psTm.setDouble(3, item.getUnitPrice());
            psTm.setInt(5, item.getStock());

            return psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(Item item) {
        return false;
    }


    @Override
    public List<Item> getAllItems() throws SQLException {
        return List.of();
    }

    @Override
    public List<String> getItemCodes() throws SQLException {
        return List.of();
    }

    @Override
    public boolean deleteItem(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("delete from Products where id=? ");
            psTm.setString(1,id);

            if(psTm.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Product Deleted SuccessFully").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Product Not Deleted").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Item searchItemById(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("select * from item  where id=?");
            psTm.setString(1,id);

            ResultSet resultSet = psTm.executeQuery();
            resultSet.next();
            Item item = new Item();
                    resultSet.getString(1);
                    resultSet.getString(2);
                    resultSet.getDouble(3);
                    resultSet.getInt(4);
                    resultSet.getString(5);


            return item;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Item searchById(String id) {
        return null;
    }

    @Override
    public List<Item> getAll() {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROm item");

            ArrayList<Item> itemArrayList = new ArrayList<>();

            while (resultSet.next()){
                Item itemTM = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );
                itemArrayList.add(itemTM);
            }

            return  itemArrayList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}





