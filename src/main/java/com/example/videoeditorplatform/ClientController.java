package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientController {
    @FXML
    public AnchorPane rootpane5;
    public void onClickHome(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
        rootpane5.getChildren().setAll(pane);
    }

    public void onClickClients(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Client List.fxml"));
        rootpane5.getChildren().setAll(pane);
    }

    public void onClickNotif(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("notif.fxml"));
        rootpane5.getChildren().setAll(pane);
    }

    public void onClickVideos(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Video List.fxml"));
        rootpane5.getChildren().setAll(pane);
    }

    public void onClickLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootpane5.getChildren().setAll(pane);
    }
}
