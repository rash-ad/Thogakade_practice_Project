package controller.customer;

import TM.CustomerTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.ServiceFactory;
import service.custom.impl.CustomerServiceImpl;
import util.ServiceType;


import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable  {
    @FXML
    private ComboBox<?> cmbCustomerIds;

    @FXML
    private ComboBox<?> cmbItemIds;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUnitPrice;
    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReload;

    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXComboBox cmbTitle;

    @FXML
    private TableColumn colAddress;
    @FXML
    private TableView tableCustomers;
    @FXML
    private TableColumn colCity;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPostalCode;

    @FXML
    private TableColumn colProvince;

    @FXML
    private TableColumn colSalary;

    @FXML
    private DatePicker dateDob;

    @FXML
    private TableView tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;
    @FXML
    private TableView<?> tableItem;


    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;
    @FXML
    private JFXButton btnExport;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    void btnExportCustomerOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/Report/CustomerForm.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"CustomerForm.pdf");
            JasperViewer.viewReport(jasperPrint,false);




        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Export Customer form Button clicked....");
    }


    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        String id = txtId.getText();
        String title = cmbTitle.getValue().toString();
        String name = txtName.getText();
        LocalDate dob = dateDob.getValue();
        Double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        Customer customer = new Customer(id, name,title, dob, salary,address, city, province, postalCode);
        System.out.println(customer);

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println("Connection " + connection);
            PreparedStatement psTm = connection.prepareStatement("Insert into customer values (?,?,?,?,?,?,?,?,?) ");
            psTm.setString(1,customer.getId());
            psTm.setString(2,customer.getTitle());
            psTm.setString(3,customer.getName());
            psTm.setObject(4,customer.getDob());
            psTm.setDouble(5,customer.getSalary());
            psTm.setString(6,customer.getAddress());
            psTm.setString(7,customer.getCity());
            psTm.setString(8,customer.getProvince());
            psTm.setString(9,customer.getPostalCode());


            if (new CustomerServiceImpl().addCustomer(customer)) {
                    new Alert(Alert.AlertType.INFORMATION,"Customer Added").show();
                    loadTable();
            }
            else{
    new Alert(Alert.AlertType.ERROR,"Customer Not Added").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {
        loadTable();
    }

    public void loadTable(){


            List<Customer> all  = new CustomerServiceImpl().getAll();
            ArrayList<CustomerTM> customerTMArrayList = new ArrayList<>();

            all.forEach(customer -> {
                customerTMArrayList.add(new CustomerTM(
                        customer.getId(),
                        customer.getTitle(),
                        customer.getName(),
                        customer.getDob(),
                        customer.getSalary(),
                        customer.getAddress(),
                        customer.getCity(),
                        customer.getProvince(),
                        customer.getPostalCode()
                ));
            });

            tblCustomers.setItems(FXCollections.observableArrayList(customerTMArrayList));



    }





    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement("DELETE FROM customer WHERE CustID=?");
            psTm.setString(1,txtId.getText());

            if(psTm.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        loadTable();

        cmbTitle.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Mr","Miss","Ms")
                )
        );

    }

     private void setTextToValues(Customer customer){
         txtId.setText(customer.getId());
         cmbTitle.setValue(customer.getTitle());
         txtName.setText(customer.getName());
         dateDob.setValue(customer.getDob());
         txtSalary.setText(customer.getSalary().toString());
         txtAddress.setText(customer.getAddress());
         txtCity.setText(customer.getCity());
         txtProvince.setText(customer.getProvince());
         txtPostalCode.setText(customer.getPostalCode());




    }
}
