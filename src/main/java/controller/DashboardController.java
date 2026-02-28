package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import config.AppModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private AnchorPane dashRoot;
    @FXML
    private JFXButton btnCustomerForm;

    private Injector injector;
    @FXML
    private JFXButton btnItemFom;
    @FXML
    private JFXButton btnOrderForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injector = Guice.createInjector(new AppModule());
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
         try {
            URL resource = this.getClass().getResource("/view/customer_form.fxml");
             assert resource!=null;
             FXMLLoader fxmlLoader = new FXMLLoader(resource);
             fxmlLoader.setControllerFactory(injector::getInstance);
             Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

         } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnItemFormOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/item_Form.fxml");
            assert resource!=null;

            Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnOrderFormOnAction(ActionEvent event) throws IOException {

            URL resource = this.getClass().getResource("/view/Order_Form.fxml");
            assert resource!=null;

        Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);


    }

}
