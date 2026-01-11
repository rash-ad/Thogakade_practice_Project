package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.CustomerTM;
import java.sql.*;
import java.util.ArrayList;

public class CustomerFormController {

    @FXML
    public TableColumn<?, ?> colDob;

    public TableView tableCustomer;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colPostalCode;



    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colSalary;
    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnReload;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddCustomer(ActionEvent event) {

    }

    @FXML
    void btnReload(ActionEvent event) {
        loadTable();
    }

    private void loadTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        ArrayList<CustomerTM> customerArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "rashpro");
            System.out.println("Connection " + connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");

            System.out.println(resultSet);
            while (resultSet.next()) {
                customerArrayList.add(
                        new CustomerTM(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(6),
                                resultSet.getDate(4),
                                resultSet.getDouble(5),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9)


                        )
                );
                tableCustomer.setItems(FXCollections.observableList(customerArrayList));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
