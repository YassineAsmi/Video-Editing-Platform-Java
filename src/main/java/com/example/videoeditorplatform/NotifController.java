package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DateTimeStringConverter;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class NotifController {
    @FXML
    public AnchorPane notifPane;
    @FXML
    public TextField Delay;


    public int ldd;
    int delay;
    LoginController loginController;
    Connection conn = null;
    PreparedStatement pst2 = null;
    public void OnClickHome(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
        notifPane.getChildren().setAll(pane);
    }

    public void OnVideoClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Video List.fxml"));
        notifPane.getChildren().setAll(pane);
    }

    public void OnClientClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientList.fxml"));
        notifPane.getChildren().setAll(pane);
    }

    public void OnNotifClick(ActionEvent actionEvent) throws IOException, SQLException {
        DoNotification();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Notif.fxml"));
        notifPane.getChildren().setAll(pane);

    }

    public void OnLogoutClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        notifPane.getChildren().setAll(pane);
    }

    public void DoNotification() throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();

        conn = DataBaseConnection.ConnectDB();

        String v2 = "Select  delay,nomVideo,dateTravail From videos,users where videos.idUser =users.id;";
        pst2 = conn.prepareStatement(v2);
        ResultSet rs = pst2.executeQuery();

        while (rs.next()){
            String deadline = rs.getString("dateTravail");
            String nomVideo = rs.getString("nomVideo");
            int delay = rs.getInt("delay");
            if(deadline.length() > 2){
                ldd= Integer.parseInt(deadline.substring(deadline.length()-2));
            }

            int DateNotif = Integer.parseInt(dtf.format(now)) -delay;
           if(DateNotif==ldd){
               Alert c = new Alert(Alert.AlertType.WARNING);
               c.setContentText("DeadLine Notification Alert for : "+nomVideo+" End on : "+deadline);
               c.show();
            }
        }
    }

    public void OnClickSaveDelay(ActionEvent actionEvent) throws SQLException {
        conn = DataBaseConnection.ConnectDB();
        String v2 = "INSERT INTO users(delay) values (?) where id = ?;";
        pst2 = conn.prepareStatement(v2);
        pst2.setInt(1, Integer.parseInt(Delay.getText()));
        pst2.setInt(2,loginController.getUserId());
        pst2.executeUpdate();

    }

    public void onClickRefreshNotif(ActionEvent actionEvent) throws SQLException {
        DoNotification();
    }
}
