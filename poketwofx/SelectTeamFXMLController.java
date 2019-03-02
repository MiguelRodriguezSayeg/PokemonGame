/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MIGUEL RODRIGUEZ
 */
public class SelectTeamFXMLController implements Initializable {
    String username;
    DatabaseConnections db;
    @FXML
    ComboBox cmbSelect;
    @FXML
    Button btnSelect;
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
        cmbSelect.setItems(teams2);
        }
        else{
        JOptionPane.showMessageDialog(null, " No teams created.");
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
    public void handleClose(MouseEvent event) {
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
    public void gotoDeleteTeam(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader;
        Stage stage;
                loader = new FXMLLoader(getClass().getResource("DeleteTeamFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                DeleteTeamFXMLController controller = loader.<DeleteTeamFXMLController>getController();
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
    public void gotoPlay(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        ArrayList<String> pokemon=new ArrayList<String>();
        ArrayList<String> movearray=new ArrayList<String>();
        ArrayList<String> pokemonstats=new ArrayList<String>();
        ArrayList<Pokemon> pokemon2=new ArrayList<Pokemon>();
        ArrayList<Moves> movelist=new ArrayList<Moves>();
        Moves move;
        Teams team;
        Trainers trainer;
        if(!cmbSelect.getSelectionModel().isEmpty()){
                FXMLLoader loader;
                int index=cmbSelect.getSelectionModel().getSelectedIndex();
                pokemon=db.getTeamMembers(username, teams2.get(index));
                for(int x=0;x<pokemon.size();x++){
                    pokemonstats=db.getPokemonStats(pokemon.get(x));
                    movelist.clear();
                    for(int y=4;y<8;y++){
                        movearray=db.getMoveStats(pokemonstats.get(y));
                        movelist.add(new Moves(movearray.get(0),movearray.get(1),movearray.get(2),Integer.valueOf(movearray.get(3)),movearray.get(4)));
                        
                    }
                    pokemon2.add(new Pokemon(pokemonstats.get(0),pokemonstats.get(1),pokemonstats.get(2),pokemonstats.get(3),"",pokemonstats.get(10),Integer.valueOf(pokemonstats.get(8)),Integer.valueOf(pokemonstats.get(9)),Integer.valueOf(pokemonstats.get(11)),Integer.valueOf(pokemonstats.get(12)),Integer.valueOf(pokemonstats.get(13)),Integer.valueOf(pokemonstats.get(14)),Integer.valueOf(pokemonstats.get(15)),Integer.valueOf(pokemonstats.get(16)),Integer.valueOf(pokemonstats.get(17))));
                    for(int f=0;f<4;f++){
                        pokemon2.get(x).addMove(movelist.get(f));
                    }
                }
                team=new Teams(db.getTeamID(username, teams2.get(index)),pokemon2,teams2.get(index),username);
                trainer=new Trainers(db.getTrainerID(username),username,team);
                Stage stage;
                loader = new FXMLLoader(getClass().getResource("GameFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                GameFXMLController controller = loader.<GameFXMLController>getController();
                controller.receiveTrainer(trainer);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
        }
    }
}
