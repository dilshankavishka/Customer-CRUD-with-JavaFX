package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.*;

public class SearchCustomerFormController {

    @FXML
    private TextField addresstxtArea;

    @FXML
    private Button btnsearch;

    @FXML
    private TextField dobtxtArea;

    @FXML
    private TextField idtxtArea;

    @FXML
    private TextField nametxtArea;

    @FXML
    private TextField numbertxtArea;

    @FXML
    private TextField searchtxtarea;

    @FXML
    private TextField titletxtArea;

    @FXML
    void btnSearch(ActionEvent event) {


        try {
            String SQL = "SELECT * FROM customer";
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345");
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
                System.out.println(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
