package com.example.videoeditorplatform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private  AnchorPane EditPanel;
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
    public TextField EditNom;
    @FXML
    public TextArea AddressEdit;
    @FXML
    public TextField TelEdit;
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

    @FXML
    private TextField searchInput;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    int id = -1;
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
            ClientsTable.refresh();
            refresh();
        }
    }
    public void editInfo() throws SQLException {
        ClientSearchModel selected=ClientsTable.getSelectionModel().getSelectedItem();
        int ID =selected.getId();
        conn = DataBaseConnection.ConnectDB();
        try {
            String v2 = "UPDATE Clients SET NomPrenom=?, address=?, tel=? where idClient=?;";
            pst2 = conn.prepareStatement(v2);
            pst2.setString(1, EditNom.getText());
            pst2.setString(2, AddressEdit.getText());
            pst2.setString(3, TelEdit.getText());
            pst2.setInt(4, ID);
            pst2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void DeleteInfo() throws SQLException {
        ClientSearchModel selected=ClientsTable.getSelectionModel().getSelectedItem();
        int ID =selected.getId();
        conn = DataBaseConnection.ConnectDB();
        try {
            String v2 = "DELETE FROM Clients where idClient=?;;";
            pst2 = conn.prepareStatement(v2);
            pst2.setInt(1, ID);
            pst2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void refresh() throws SQLException {

        conn = DataBaseConnection.ConnectDB();
        String V = "Select idClient,NomPrenom,address,tel From clients ;";
        try {
            pst = conn.prepareStatement(V);
            ResultSet res = pst.executeQuery();
            clientSearchModelObservableList.clear();
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

            FilteredList<ClientSearchModel> filteredData = new FilteredList<>(clientSearchModelObservableList,b ->true );
            searchInput.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredData.setPredicate(clientSearchModel -> {
                    //if no search value then display all record
                    if(newValue.isEmpty() || newValue.isBlank() || newValue ==null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    if(clientSearchModel.getNomPrenom().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(clientSearchModel.getTel().toString().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(clientSearchModel.getId().toString().indexOf(searchKeyword) > -1){
                        return true;
                    } else if(clientSearchModel.getAddress().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else{
                        return false;
                    }
                });
            });
            SortedList<ClientSearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(ClientsTable.comparatorProperty());
            ClientsTable.setItems(sortedData);

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

    public void onClickRefresh(ActionEvent actionEvent) throws SQLException {

        ClientsTable.refresh();
    }

    public void onClickEditBtn(ActionEvent actionEvent) throws SQLException {
        EditPanel.setVisible(true);
        ClientSearchModel selected=ClientsTable.getSelectionModel().getSelectedItem();
        int ID =selected.getId();
        if(selected !=null){
            conn = DataBaseConnection.ConnectDB();

                String v2 = "Select NomPrenom,address,tel From clients where idClient =?;";
                pst2 = conn.prepareStatement(v2);
                pst2.setInt(1, ID);
                ResultSet rs = pst2.executeQuery();
                if(rs.next()){
                    String queryNomPrenom = rs.getString("NomPrenom");
                    String queryAddress = rs.getString("address");
                    int queryTel = rs.getInt("tel");
                    EditNom.setText(queryNomPrenom);
                    AddressEdit.setText(queryAddress);
                    TelEdit.setText(String.valueOf(queryTel));
                }
            editInfo();
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please Select an item");
            a.show();
        }



    }

    public void onClickRemoveBtn(ActionEvent actionEvent) throws SQLException {
    DeleteInfo();
    refresh();
    }

    public void onClickCancelEdit(ActionEvent actionEvent) {
        EditPanel.setVisible(false);
        clear();
    }

    public void onClickEdit(ActionEvent actionEvent) throws SQLException {
        editInfo();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Client Inserted Succefully");
        a.show();
        refresh();
        EditPanel.setVisible(false);
    }
}