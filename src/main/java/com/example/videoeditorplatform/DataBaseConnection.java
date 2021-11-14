package com.example.videoeditorplatform;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {
    public Connection conn;

    public static Connection ConnectDB(){
    String databaseName="videoeditingplatform";
    String databaseUser="root";
    String databasePassword="";
    String url ="jdbc:mysql://localhost/"+ databaseName;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,databaseUser,databasePassword);
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Connection to database done");
        return conn;
    }catch (Exception e){
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Failed");
        alert.setContentText("Connection to database Failed with errors");
        return null;
    }

    }
}
