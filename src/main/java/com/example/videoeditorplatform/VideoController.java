package com.example.videoeditorplatform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VideoController {
    @FXML
    public AnchorPane rootpane4;
    public void onClickHome(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
        rootpane4.getChildren().setAll(pane);
    }

    public void onClickClients(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientList.fxml"));
        rootpane4.getChildren().setAll(pane);
    }

    public void onClickVideos(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Video List.fxml"));
        rootpane4.getChildren().setAll(pane);
    }

    public void onClickNotif(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("notif.fxml"));
        rootpane4.getChildren().setAll(pane);
    }

    public void onClickLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootpane4.getChildren().setAll(pane);
    }
}
