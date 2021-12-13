package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

NotifController notifController;
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;

    public void HandleBtnLogin(ActionEvent actionEvent) throws IOException {
        if (UserLogin.getText().isBlank() ==false && passLog.getText().isBlank()==false)
        {

        ValidateLogin();

        //    FXMLLoader loader = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
          /*  try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                Scene scene = new Scene(root);


            }catch (Exception e){
                System.out.println("mamchetch o93od zezwa");
            }*/
            //   HomeTController homeT = loader.getController();
      //      homeT.WelcomeMSG(UserLogin.getText());

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
        String verifyLogin = "Select * From users where username = ? and password = ? ;";
        try {
            pst = conn.prepareStatement(verifyLogin);
            pst.setString(1, UserLogin.getText());
            pst.setString(2, passLog.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                boolean privl = rs.getBoolean("privieliges");
                if (privl == true) {
                    msg.setText("You Have successfully logged in!!");
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
                    rootpane.getChildren().setAll(pane);
                    notifController.DoNotification();
                } else {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
                    rootpane.getChildren().setAll(pane);
                }
            }
            else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Login Failed ");
                a.show();
                }


        } catch (SQLException throwables) {
            msg.setText("ERROR");
        }
    }

}