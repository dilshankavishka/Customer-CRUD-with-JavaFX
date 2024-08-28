package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCustomerFormController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<?> comboTitle;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
