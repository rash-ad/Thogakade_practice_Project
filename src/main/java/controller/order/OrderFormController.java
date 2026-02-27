package controller.order;

import TM.CartTM;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CustomerService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class OrderFormController implements Initializable {

    CustomerService customerService= ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);


    @FXML
    private TextField txtQtyOnHand;
    @FXML
    private TableView tblCartTM;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDescription;
    @FXML
    private TextField txtOrderId;
    @FXML
    private ComboBox cmbCustomerIds;
    @FXML
    private ComboBox<?> cmbItemIds;
    ArrayList<CartTM>cartTMArrayList=new ArrayList<>();
    @FXML
//    void btnAddToCartToAction(ActionEvent event){
//        cartTMArrayList.add(
//                new CartTM(
//                        cmbItemIds.getValue().toString(),
//                        txtOrderId.getText(),
//                        lblDescription.getText(),
//
//
//        ));
//
//        tblCartTM.setItems(FXCollections.observableArrayList(cartTMArrayList));
//
//    }







   private void localDateAndTime(){
       Date date=new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    lblDate.setText(sdf.format(date));
       Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
           LocalTime now = LocalTime.now();
           lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
       }),
                new KeyFrame(Duration.seconds(1))
       );

timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();
   }
   private  void loadCustomerIds()  {
       List<String> allCustomerIDs = customerService.getAllCustomerIDs();
       cmbCustomerIds.setItems(FXCollections.observableArrayList(allCustomerIDs));

   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       loadCustomerIds();
        localDateAndTime();
        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable  ,oldvalue,newValue)->
                System.out.println(newValue)
        );



    }
}
