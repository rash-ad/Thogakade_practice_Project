package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.CustomerTM;
import java.sql.*;
import java.util.ArrayList;

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

    private void loadTable() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        ColSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        Col
        ColProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
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
                                resultSet.getDate(6),
                                resultSet.getDouble(4),
                                resultSet.getString(5),
                                resultSet.getString(7),
                                resultSet.getString(9)


                        )
                );
                // System.out.println(resultSet.getString(1));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
