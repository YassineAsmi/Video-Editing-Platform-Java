package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeTController {
 //   @FXML
   // public Text welcome;
   @FXML
         public AnchorPane rootpane2;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public void OnClickHome(ActionEvent actionEvent) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
       rootpane2.getChildren().setAll(pane);
    }

    public void OnNotifClick(ActionEvent actionEvent) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("notif.fxml"));
       rootpane2.getChildren().setAll(pane);
    }

    public void OnVideoClick(ActionEvent actionEvent) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("Video List.fxml"));
       rootpane2.getChildren().setAll(pane);
    }

    public void OnClientClick(ActionEvent actionEvent) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientList.fxml"));
       rootpane2.getChildren().setAll(pane);
    }

    public void OnLogoutClick(ActionEvent actionEvent) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
       rootpane2.getChildren().setAll(pane);
    }


}
