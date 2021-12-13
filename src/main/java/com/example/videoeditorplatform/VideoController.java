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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VideoController implements Initializable {
    @FXML
    public AnchorPane rootpane4;
    @FXML
    public AnchorPane AddVideoPanel;
    @FXML
    public TextField searchInput;
    @FXML
    public TextField AddNom;
    @FXML
    public TextField DeadLineAdd;
    @FXML
    public TextField SongAdd;
    @FXML
    public TextField EditNom;
    @FXML
    public TextField EditDD;
    @FXML
    public TextField EditSong;
    @FXML
    public  CheckBox EditStatus;
    @FXML
    private TableView<VideoSearchModel> VideosTable;
    @FXML
    private TableColumn<VideoSearchModel,Integer> idVideoCol;
    @FXML
    private TableColumn<VideoSearchModel,String> NP;
    @FXML
    private TableColumn<VideoSearchModel,String> nameVideoCol;
    @FXML
    private TableColumn<VideoSearchModel,String> dateCol;
    @FXML
    private TableColumn<VideoSearchModel,String> songCol;
    @FXML
    private TableColumn<VideoSearchModel,Boolean> statusCol;
    @FXML
    private AnchorPane EditVideoPanel;
    @FXML
    private ChoiceBox<String> ClientNameAdd;

    private String queryNomPrenom;
    ObservableList list=FXCollections.observableArrayList();
    ObservableList<VideoSearchModel> videoSearchModelObservableList = FXCollections.observableArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Notif.fxml"));
        rootpane4.getChildren().setAll(pane);
    }

    public void onClickLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootpane4.getChildren().setAll(pane);
    }
    public void editInfoVideo() throws SQLException {
        VideoSearchModel selected=VideosTable.getSelectionModel().getSelectedItem();
        int ID =selected.getId();
        conn = DataBaseConnection.ConnectDB();
        try {
            String v2 = "UPDATE Videos SET nomVideo=?, dateTravail=?, Musique=?, Status=? where idVideo=?;";
            pst2 = conn.prepareStatement(v2);
            pst2.setString(1, EditNom.getText());
            pst2.setString(2, EditDD.getText());
            pst2.setString(3, EditSong.getText());
            pst2.setBoolean(4, EditStatus.isSelected());
            pst2.setInt(5, ID);
            pst2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void DeleteInfoVideo() throws SQLException {
        VideoSearchModel selected=VideosTable.getSelectionModel().getSelectedItem();
        int ID =selected.getId();
        conn = DataBaseConnection.ConnectDB();
        try {
            String v2 = "DELETE FROM Videos where idVideo=?;;";
            pst2 = conn.prepareStatement(v2);
            pst2.setInt(1, ID);
            pst2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void onClickEditBtn(ActionEvent actionEvent) throws SQLException {


        VideoSearchModel selected=VideosTable.getSelectionModel().getSelectedItem();
        int ID =selected.getId();
        if(selected !=null){
            EditVideoPanel.setVisible(true);
            conn = DataBaseConnection.ConnectDB();

            String v2 = "Select nomVideo,dateTravail,Musique,Status From videos where idVideo =?;";
            pst2 = conn.prepareStatement(v2);
            pst2.setInt(1, ID);
            ResultSet rs = pst2.executeQuery();
            if(rs.next()){
                String querynomVideoom = rs.getString("nomVideo");
                String queryDeadline = rs.getString("dateTravail");
                String querySong = rs.getString("Musique");
                Boolean queryStatus = rs.getBoolean("Status");
                EditNom.setText(querynomVideoom);
                EditDD.setText(queryDeadline);
                EditSong.setText(querySong);
                EditStatus.setSelected(queryStatus);
            }
            editInfoVideo();
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please Select an item");
            a.show();
        }
    }

    public void onClickRefresh(ActionEvent actionEvent) throws SQLException {
        refresh();
    }

    public void onClickRemoveBtn(ActionEvent actionEvent) throws SQLException {
        DeleteInfoVideo();
        refresh();
    }

    public void onClickCancelAdd(ActionEvent actionEvent) {
        AddVideoPanel.setVisible(false);
    }

    public void onClickEdit(ActionEvent actionEvent) throws SQLException {


        editInfoVideo();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Client Inserted Succefully");
        a.show();
        refresh();
        EditVideoPanel.setVisible(false);
    }

    public void onClickCancelEdit(ActionEvent actionEvent) {
        EditVideoPanel.setVisible(false);
    }




    public void onClickAddBtn(ActionEvent actionEvent) throws SQLException {
        AddVideoPanel.setVisible(true);
            conn = DataBaseConnection.ConnectDB();
            String v2 = "Select NomPrenom From clients;";
            pst2 = conn.prepareStatement(v2);
            ResultSet rs = pst2.executeQuery();
            list.removeAll(list);
            while (rs.next()){
                queryNomPrenom = rs.getString("NomPrenom");
                list.addAll(queryNomPrenom);
            }
        ClientNameAdd.getItems().addAll(list);
        ClientNameAdd.setValue(queryNomPrenom);
    }

    public void onClickAddVideo(ActionEvent actionEvent) throws SQLException {
        conn = DataBaseConnection.ConnectDB();
        String v2 = "Select idClient From clients where NomPrenom=?;";
        pst = conn.prepareStatement(v2);
        pst.setString(1, ClientNameAdd.getValue());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
        int  queryID = rs.getInt("idClient");
        String v3 = "INSERT INTO videos(nomVideo,dateTravail,Musique,idClient) values (?,?,?,?)";
        pst2 = conn.prepareStatement(v3);
        pst2.setString(1, AddNom.getText());
        pst2.setString(2, DeadLineAdd.getText());
        pst2.setString(3, SongAdd.getText());
        pst2.setInt(4, queryID);
        pst2.execute();
        //   clear();
        //     ClientsTable.refresh();
        refresh();
         }
        AddVideoPanel.setVisible(false);
    }
    public void refresh() throws SQLException {

        conn = DataBaseConnection.ConnectDB();
        String V = "Select idVideo,NomPrenom,nomVideo,dateTravail,Musique,Status From videos,clients where clients.idClient=videos.idClient ;";
        try {
            pst = conn.prepareStatement(V);
            ResultSet res = pst.executeQuery();
            videoSearchModelObservableList.clear();

            while(res.next()){
                int queryID = res.getInt("idVideo");
                String queryNP = res.getString("NomPrenom");
                String queryNomVideo = res.getString("nomVideo");
                String querydatetravail = res.getString("dateTravail");
                String queryMusique = res.getString("Musique");
                Boolean querystatus = res.getBoolean("Status");
                videoSearchModelObservableList.add(new VideoSearchModel(queryID,queryNP,queryNomVideo,querydatetravail,queryMusique,querystatus) );
            }
            // integrate values from to colomn
            idVideoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            NP.setCellValueFactory(new PropertyValueFactory<>("NP"));
            nameVideoCol.setCellValueFactory(new PropertyValueFactory<>("NameVideo"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("deadLine"));
            songCol.setCellValueFactory(new PropertyValueFactory<>("song"));
            statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
            //set all colomns in talble view
            VideosTable.setItems(videoSearchModelObservableList);

            FilteredList<VideoSearchModel> filteredData = new FilteredList<>(videoSearchModelObservableList, b ->true );
            searchInput.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredData.setPredicate(videoSearchModel -> {
                    //if no search value then display all record
                    if(newValue.isEmpty() || newValue.isBlank() || newValue ==null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    if(videoSearchModel.getNameVideo().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(videoSearchModel.getDeadLine().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(videoSearchModel.getSong().indexOf(searchKeyword) > -1){
                        return true;
                    } else{
                        return false;
                    }
                });
            });
            SortedList<VideoSearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(VideosTable.comparatorProperty());
            VideosTable.setItems(sortedData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
