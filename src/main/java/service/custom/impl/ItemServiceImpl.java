package service.custom.impl;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;
import service.custom.ItemService;

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
            psTm.setString(1, item.getId());
            psTm.setString(2, item.getName());
            psTm.setDouble(3, item.getPrice());
            psTm.setInt(4, item.getQuantity());
            psTm.setString(5, item.getCategory());

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
    public Item searchById(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("select * from item  where id=?");
            psTm.setString(1,id);

            ResultSet resultSet = psTm.executeQuery();
            resultSet.next();
            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            return item;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Item> getAll() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * From item");

            ArrayList<Item> itemArrayList = new ArrayList<>();

            while (resultSet.next()){
                Item itemTM = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        resultSet.getString(5)

                );
                itemArrayList.add(itemTM);
            }

            return  itemArrayList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }

