/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class DeleteTeamFXMLController implements Initializable {
    String username;
    @FXML
    ComboBox cmbTeams;
    @FXML
    Button btnDelete;
    @FXML
    Label lblExit;
    DatabaseConnections db;
    ObservableList<String> teams2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void receiveUsername(String username) throws SQLException, ClassNotFoundException{
        this.username=username;
        db=new DatabaseConnections();
        try {
            teams2 = FXCollections.observableArrayList(db.getTeamNames(username));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteTeamFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!teams2.isEmpty()){
        cmbTeams.setItems(teams2);
        cmbTeams.setValue(0);
        }
        else{
        JOptionPane.showMessageDialog(null, " No teams created.");
        }
    }
    public void deleteTeam(ActionEvent event) throws SQLException, ClassNotFoundException{
        if(!cmbTeams.getSelectionModel().isEmpty()){
            int index=cmbTeams.getSelectionModel().getSelectedIndex();
            db.deleteTeam(db.getTeamID(username, teams2.get(index)));
            teams2.remove(index);
            JOptionPane.showMessageDialog(null, " Successfully deleted.");
        }
    }
    public void gotoMain(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader;
        Stage stage;
                loader = new FXMLLoader(getClass().getResource("WelcomeFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                WelcomeFXMLController controller = loader.<WelcomeFXMLController>getController();
                controller.receiveUsername(username);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
    }
    public void handleClose(MouseEvent event) throws SQLException, ClassNotFoundException {
        db.setStatus(false, username);
        System.exit(0);
    }
    public void logout(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        DatabaseConnections db=new DatabaseConnections();
        db.setStatus(false, username);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void goToReadRecords(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader;
        Stage stage;
                loader = new FXMLLoader(getClass().getResource("ReadRecordsFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                ReadRecordsFXMLController controller = loader.<ReadRecordsFXMLController>getController();
                controller.receiveUsername(username);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
    }
    public void gotoAddTeam(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader;
        Stage stage;
                loader = new FXMLLoader(getClass().getResource("AddTeamFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                AddTeamFXMLController controller = loader.<AddTeamFXMLController>getController();
                controller.receiveUsername(username);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
    }
    public void gotoSelection(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader;
        Stage stage;
                loader = new FXMLLoader(getClass().getResource("SelectTeamFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                SelectTeamFXMLController controller = loader.<SelectTeamFXMLController>getController();
                controller.receiveUsername(username);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
    }
}
