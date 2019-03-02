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

/**
 * FXML Controller class
 *
 * @author MIGUEL RODRIGUEZ
 */
public class AddTeamFXMLController implements Initializable {
    String username;
    DatabaseConnections db;
    ArrayList<String> pkmn;
    ArrayList<String> selec;
    ObservableList<String> pkmn2;
    @FXML
    ComboBox cmbPkmn;
    @FXML
    TextField txtTeamName;
    @FXML
    Button btnAddTeam,btnAddPokemon;
    @FXML
    Label LblPkmn1,LblPkmn2,LblPkmn3,LblPkmn4,LblPkmn5,LblPkmn6;
    @FXML
    ImageView im1,im2,im3,im4,im5,im6;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        selec=new ArrayList<>();
        db=new DatabaseConnections();
        try {
            pkmn=db.getPokemonNames();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddTeamFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pkmn2 = FXCollections.observableArrayList(pkmn);
        System.out.println(pkmn2);
        cmbPkmn.setItems(pkmn2);
        cmbPkmn.setValue(pkmn2.get(0));
        
    }
    public void addAndShowPokemon(ActionEvent event){
        boolean wasnull=false;
        Image apply=null;
        String output = cmbPkmn.getSelectionModel().getSelectedItem().toString();
        int index=cmbPkmn.getSelectionModel().getSelectedIndex();
        if(selec.size()<6 || selec.contains("")){
            for(int x=0;x<selec.size();x++){
                if(selec.get(x)==""  && wasnull==false){
                    wasnull=true;
                    selec.set(x,output);
                    showImage(x+1,getImage(index));
                }
            }
            if(wasnull!=true){
                    selec.add(selec.size(), output);
                    showImage(selec.size(),getImage(index));
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You have already selected 6 pokemon.");
        }
        System.out.println(selec);
    }
    public void addTeam(ActionEvent event) throws ClassNotFoundException, SQLException{
        if((txtTeamName.getText()!=null) &&(txtTeamName.getText().trim().length() > 0)&&(selec.isEmpty()!=true) ){
            db.createTeam(txtTeamName.getText(), username, selec);
        }
        else{
            JOptionPane.showMessageDialog(null, "Choose 6 pokemon and a valid name to continue.");
        }
    }
    public void eliminatePokemon(MouseEvent event){
        if(event.getSource()==LblPkmn2){  
            im2.setImage(SelectedImages.nss);
            selec.set(1, "");
        }
        else if(event.getSource()==LblPkmn1){  
            im1.setImage(SelectedImages.nss);
            selec.set(0, "");
        }
        else if(event.getSource()==LblPkmn3){  
            im3.setImage(SelectedImages.nss);
            selec.set(2, "");
        }
        else if(event.getSource()==LblPkmn4){  
            im4.setImage(SelectedImages.nss);
            selec.set(3, "");
        }
        else if(event.getSource()==LblPkmn5){  
            im5.setImage(SelectedImages.nss);
            selec.set(4, "");
        }
        else if(event.getSource()==LblPkmn6){  
            im6.setImage(SelectedImages.nss);
            selec.set(5, "");
        }
    }
    public void showImage(int index, Image show){
        switch (index) {
            case 1:  im1.setImage(show);
                     break;
            case 2:  im2.setImage(show);
                     break;
            case 3:  im3.setImage(show);
                     break;
            case 4:  im4.setImage(show);
                     break;
            case 5:  im5.setImage(show);
                     break;
            case 6:  im6.setImage(show);
                     break;
            default: 
                     break;
        }
    
    }
    public Image getImage(int index){
        Image devolver=null;
        switch (index) {
            case 0:  devolver=SelectedImages.Venusaur;break;
            case 1:   devolver= SelectedImages.Charizard; break;
            case 2:   devolver= SelectedImages.Blastoise; break;
            case 3:   devolver= SelectedImages.Pikachu; break;
            case 4:   devolver= SelectedImages.Clefable; break;
            case 5:   devolver= SelectedImages.Golem; break;
            case 6:   devolver= SelectedImages.Dragonite; break;
            case 7:   devolver= SelectedImages.Celebi; break;
            case 8:   devolver= SelectedImages.Lopunny; break;
            case 9:   devolver= SelectedImages.Leafeon; break;
            case 10:   devolver= SelectedImages.Mamoswine; break;
            case 11:   devolver= SelectedImages.Aegislash; break;
            default: break; //devolver=SelectedImages.Pikachu;break;
        }
        return devolver;
    }
    public void receiveUsername(String username) throws SQLException, ClassNotFoundException{
        this.username=username;
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
