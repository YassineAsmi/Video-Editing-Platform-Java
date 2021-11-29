package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField userReg;
    @FXML
    private TextField passReg;
    @FXML
    private TextField emailReg;
    @FXML
            private  AnchorPane rootpaneReg;
    @FXML
    public Text confirmationMsg ;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    public void SignUpBTN(ActionEvent actionEvent) throws SQLException, IOException {
        conn = DataBaseConnection.ConnectDB();
        String V = "Select * From users where email = ?  ;";
        pst = conn.prepareStatement(V);
        pst.setString(1, emailReg.getText());
        rs = pst.executeQuery();
        if (rs.next()) {
            confirmationMsg.setText("user already exist");
        } else {
            String v2 = "INSERT INTO users(username,email, password) values (?,?,?)";
            pst2 = conn.prepareStatement(v2);
            pst2.setString(1, userReg.getText());
            pst2.setString(2, emailReg.getText());
            pst2.setString(3, passReg.getText());
            pst2.executeUpdate();
            moveToRegisterScreen();
        }


    }

    public void moveToRegisterScreen() throws IOException {
        loadStage();
    }

    private void loadStage() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
        rootpaneReg.getChildren().setAll(pane);

    }
}
