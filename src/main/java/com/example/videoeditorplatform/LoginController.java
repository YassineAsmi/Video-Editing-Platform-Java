package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController {
@FXML
    private TextField UserLogin;
@FXML
    private PasswordField passLog;
@FXML
    private Button logButton;
@FXML
    private Text msg;
    public void HandleBtnLogin(ActionEvent actionEvent) {
        if (UserLogin.getText().isBlank() ==false && passLog.getText().isBlank()==false)
        {
        ValidateLogin();

        }
        else{
            msg.setText("Please enter username and password");
        }
     //   Parent root = FXMLLoader.load(getClass().getResource(HomeTController));
    }
    public void ValidateLogin(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "Select count (1) From users where username = '"+UserLogin.getText()+"' And password= '"+passLog.getText()+"';'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    msg.setText("You Have successfully logged in!!");
                }else{
                    msg.setText("Login Failed");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}