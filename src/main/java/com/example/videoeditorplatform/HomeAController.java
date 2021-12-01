package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeAController {
    @FXML
    public AnchorPane rootpane3;
    public void onClickHome(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
        rootpane3.getChildren().setAll(pane);
    }

    public void onClickNotif(ActionEvent actionEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("notif.fxml"));
        rootpane3.getChildren().setAll(pane);
    }

    public void onClickVideos(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Video List.fxml"));
        rootpane3.getChildren().setAll(pane);
    }

    public void onClickClients(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientList.fxml"));
        rootpane3.getChildren().setAll(pane);
    }

    public void onClickCreateAccount(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        rootpane3.getChildren().setAll(pane);
    }

    public void onClickLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
        rootpane3.getChildren().setAll(pane);
    }
}
