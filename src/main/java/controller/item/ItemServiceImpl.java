package controller.item;

import controller.customer.CustomerService;
import db.DBConnection;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class ItemServiceImpl  implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {


        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("delete from Products where id=? ");
            psTm.setString(1,id);

//            if(psTm.executeUpdate()>0){
//                new Alert(Alert.AlertType.INFORMATION,"Product Deleted").show();
//            }
//            else{
//                new Alert(Alert.AlertType.ERROR,"Product Not Deleted").show();
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Customer searchCustomerById(String id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {

        return List.of();
    }
}
