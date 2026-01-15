package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ItemDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController  implements Initializable {

    @FXML
    private JFXButton btnAddtem;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReload;
    @FXML
    private TableView<Item> tableProduct;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQuantity;
    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
    loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    ArrayList<Item> itemArrayList=new ArrayList<>();


    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        try {
            Connection connection = ItemDBConnection.getInstance().getConnection();
            System.out.println(connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from products");
            while(resultSet.next()){
                itemArrayList.add(
                        new Item(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getInt(4),
                resultSet.getString(5)
                        )
                );

            }

            ObservableList<Item> observableList = FXCollections.observableArrayList(itemArrayList);
           tableProduct.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        cmbCategory.setItems(
//                FXCollections.observableArrayList(
//                        Arrays.asList("Electronics","Furniture","Stationery ","Kitchen","Bags","Fashion","Accessories","Home Appliances")
//
//                )
//        );
        loadTable();
    }
}
