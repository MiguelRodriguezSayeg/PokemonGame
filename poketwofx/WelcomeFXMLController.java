package poketwofx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author NoSoyYo
 */
public class WelcomeFXMLController implements Initializable {
    public Label labelUsr;
    String username;
    @FXML 
     public Button btnRecords;
    @FXML 
     public Button btnLogOut;
    @FXML 
     public Button btnAddTeam;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void receiveUsername(String username){
        this.username=username;
        labelUsr.setText("WELCOME, "+username.toUpperCase());
    }
    public void handleClose(MouseEvent event) throws SQLException, ClassNotFoundException {
        DatabaseConnections db=new DatabaseConnections();
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
