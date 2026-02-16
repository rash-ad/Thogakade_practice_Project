package controller.item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;
import model.Item;
import service.custom.impl.ItemServiceImpl;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController  implements Initializable {

    @FXML
    private JFXButton btnAddtem;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<Item> tableItem;


    @FXML
    private JFXButton btnReload;
    @FXML
    private TableView<Item> tableProduct;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXComboBox cmbCategory;

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
    void btnAddItemOnAction(ActionEvent event) throws SQLException {



    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("delete from Products where id=? ");
            psTm.setString(1,txtId.getText());

            if(psTm.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Product Deleted").show();
                loadTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Product Not Deleted").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {
    loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException{



    }

    private void setTextValue(Item item) {
        txtId.setText(item.getItemCode());
        txtName.setText(item.getDescription());
        txtPrice.setText(item.getPackSize().toString());
        txtQuantity.setText(item.getUnitPrice().toString());
        cmbCategory.setValue(item.getStock());
    }

    ArrayList<Item> itemArrayList=new ArrayList<>();


    private void loadTable() throws SQLException {
        ItemServiceImpl itemService = new ItemServiceImpl();
        List<Item> all = itemService.getAll();

        ArrayList<Item> itemTMArrayList = new ArrayList<>();
        all.forEach(item -> {
            itemArrayList.add(new Item(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getStock()

            ));
        });
        tableItem.setItems(FXCollections.observableArrayList(itemTMArrayList));

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCategory.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Electronics","Furniture","Stationery ","Kitchen","Bags","Fashion","Accessories","Home Appliances")

                )
        );
        loadTable();

    }
    void setTextValues(Item item){
        txtId.setText(item.getItemCode());
        txtName.setText(item.getDescription());
        txtPrice.setText(item.getPackSize().toString());
        txtQuantity.setText(item.getUnitPrice().toString());
        cmbCategory.setValue(item.getStock());
    }
}
