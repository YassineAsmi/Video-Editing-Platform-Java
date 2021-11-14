package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeTController {
    @FXML
    public Text welcome;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public void OnClickHome(ActionEvent actionEvent) {
    }

    public void OnNotifClick(ActionEvent actionEvent) {
    }

    public void OnVideoClick(ActionEvent actionEvent) {
    }

    public void OnClientClick(ActionEvent actionEvent) {
    }

    public void OnLogoutClick(ActionEvent actionEvent) {
    }

    public void WelcomeMSG(String t) {
        welcome.setTextContent("Welcome"+t);
    }
}
