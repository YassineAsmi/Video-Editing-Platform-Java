package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController {
    public static Object username;
    @FXML
    private TextField UserLogin;
@FXML
    private PasswordField passLog;
@FXML
    private Button logButton;
@FXML
    private Text msg;
@FXML
    private AnchorPane rootpane;
@FXML
    private Button CancelBTN;

Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;

    public void HandleBtnLogin(ActionEvent actionEvent) throws IOException {
        if (UserLogin.getText().isBlank() ==false && passLog.getText().isBlank()==false)
        {
        ValidateLogin();
        //    FXMLLoader loader = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
            //   HomeTController homeT = loader.getController();
      //      homeT.WelcomeMSG(UserLogin.getText());
           rootpane.getChildren().setAll(pane);
        }
        else{
            msg.setText("Please enter username and password");
        }
    }

    public void cancelbtn(ActionEvent actionEvent) {
        Stage stage = (Stage) CancelBTN.getScene().getWindow();
        stage.close();
    }

    public void ValidateLogin() throws IOException {
      conn = DataBaseConnection.ConnectDB();
        //String verifyLogin = "Select * From users where username = ? and password = ?  ;";
        String v = "Select * from users WHERE username = ? and password = ?;";
        try {
            pst = conn.prepareStatement(v);
            pst.setString(1, UserLogin.getText());
            pst.setString(2, passLog.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                msg.setText("You Have successfully logged in!!");

            } else {
                msg.setText("Login Failed");
            }
        } catch (SQLException throwables) {
            msg.setText("ERROR");
        }
    }


}