package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

import java.sql.*;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> ColAddress;

    @FXML
    private TableColumn<?, ?> ColDOB;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColProvince;

    @FXML
    private TableColumn<?, ?> ColSalary;

    @FXML
    private DatePicker DateDOB;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnREload;

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
    private JFXTextField txtrSalary;

    @FXML
    void btnAddCustomer(ActionEvent event) {

    }

    @FXML
    void btnReload(ActionEvent event) {
        loadTable();
    }
    private void loadTable(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade","root","rashpro");
            System.out.println("Connection "+connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            System.out.println(resultSet);
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
