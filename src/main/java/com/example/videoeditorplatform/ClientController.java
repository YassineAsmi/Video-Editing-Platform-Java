package com.example.videoeditorplatform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    public AnchorPane rootpane5;
    @FXML
    public AnchorPane addpanel;
    @FXML
    public TextField nom;
    @FXML
    public TextArea address;
    @FXML
    public TextField tel;
    @FXML
    private TableView<ClientSearchModel> ClientsTable;
    @FXML
    private TableColumn<ClientSearchModel,Integer> idCol;
    @FXML
    private TableColumn<ClientSearchModel,String> nameCol;
    @FXML
    private TableColumn<ClientSearchModel,String> addressCol;
    @FXML
    private TableColumn<ClientSearchModel,Integer> telCol;

    ObservableList<ClientSearchModel> clientSearchModelObservableList = FXCollections.observableArrayList();


    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;

    public void initialize(URL url, ResourceBundle resource)  {
        try {
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void onClickHome(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomeTech.fxml"));
        rootpane5.getChildren().setAll(pane);
    }

    public void onClickClients(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientList.fxml"));
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

    public void onClickAddBtn(ActionEvent actionEvent) {
        addpanel.setVisible(true);
    }

    public void onClickCancelAdd(ActionEvent actionEvent) {
        clear();
    }

    public void onClickAdd(ActionEvent actionEvent) throws SQLException {
        conn = DataBaseConnection.ConnectDB();
        String V = "Select * From clients where tel=?  ;";
        pst = conn.prepareStatement(V);
        pst.setString(1, tel.getText());
        rs = pst.executeQuery();
        if (rs.next()) {
            Alert a = new Alert(Alert.AlertType.NONE);
            // show the dialog
            a.show();
        }
        else {
            String v2 = "INSERT INTO clients(NomPrenom,address, tel) values (?,?,?)";
            pst2 = conn.prepareStatement(v2);
            pst2.setString(1, nom.getText());
            pst2.setString(2, address.getText());
            pst2.setString(3, tel.getText());
            pst2.executeUpdate();
            clear();
            refresh();
        }
    }
    public void refresh() throws SQLException {
        conn = DataBaseConnection.ConnectDB();
        String V = "Select idClient,NomPrenom,address,tel From clients ;";
        try {
            pst = conn.prepareStatement(V);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                int queryID = res.getInt("idClient");
                String queryNomPrenom = res.getString("NomPrenom");
                String queryAddress = res.getString("address");
                int queryTel = res.getInt("tel");
                clientSearchModelObservableList.add(new ClientSearchModel(queryID,queryNomPrenom,queryAddress,queryTel) );
            }
            // integrate values from to colomn
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("NomPrenom"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
            //set all colomns in talble view
            ClientsTable.setItems(clientSearchModelObservableList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clear(){
        addpanel.setVisible(false);
        nom.setText("");
        address.setText("");
        tel.setText("");
    }
}