package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerForm implements Initializable {

    @FXML
    private Button btnReload;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dobSelector;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtPostalCode;
    @FXML
    private TableView<Customer> tableView;

    @FXML
    private JFXComboBox<String> txtTitle;

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnAdd(ActionEvent event) {
        Customer customer = new Customer(
                txtID.getText(),
                txtTitle.getValue().toString(),
                txtName.getText(),
                txtAddress.getText(),
                dobSelector.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtCity.getText(),txtProvince.getText(),
                txtPostalCode.getText()
        );

        try {
            String SQL ="INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,customer.getId());
            psTm.setObject(2,customer.getTitle());
            psTm.setObject(3,customer.getName());
            psTm.setDate  (4,Date.valueOf(customer.getDob()));
            psTm.setDouble(5,customer.getSalary());
            psTm.setObject(6,customer.getAddress());
            psTm.setObject(7,customer.getCity());
            psTm.setObject(8,customer.getProvince());
            psTm.setObject(9,customer.getPostalCode());

            boolean b = psTm.executeUpdate()>0;
            System.out.println(b);

            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added!").show();
                loadTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(customer);
    }

    @FXML
    void btnDelete(ActionEvent event) {
        String SQL = "DELETE FROM customer WHERE CustID='" + txtID.getText() + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            boolean isDeleted = connection.createStatement().executeUpdate(SQL)>0;
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted !!!").show();
            }
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setTextToValues(Customer newValue) {
        txtID.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr");
        titles.add("Miss");
        titles.add("Ms");
        txtTitle.setItems(titles);
        tableView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }
    private void loadTable()  {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        tableView.setItems(customerObservableList);
// -----------------------------------------------------------------------------------
        try {
            String SQL = "SELECT * FROM customer";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("CustTitle")+resultSet.getString("CustName"));
                Customer customer =new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("postalCode")
                );
                customerObservableList.add(customer);
                System.out.println(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
