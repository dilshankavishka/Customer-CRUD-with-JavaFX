package controller;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Customer;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private Button btnADD;

    @FXML
    private TextField lblAddress;

    @FXML
    private DatePicker lblDOB;

    @FXML
    private TextField lblID;

    @FXML
    private TextField lblName;

    @FXML
    private TextField lblNumber;

    @FXML
    private ComboBox<String> lblTitle;

    @FXML
    void addCustomer(ActionEvent event) {
       // Connection customerList =DBConnection.getInstance().getConnection();

       // Customer customer=  new Customer();
       // customerList.add(customer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("MR");
        titles.add("Ms");
        titles.add("Mrs");

        lblTitle.setItems(titles);
    }
}
