/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class createAccountController implements Initializable {
     @FXML 
     private TextField txtGetUsername;
     @FXML 
     private TextField txtGetPassword;
     @FXML 
     private Button btnRegister;
     @FXML 
     private Label labelClose;
     public void handleClose(MouseEvent event) {
        System.exit(0);
    }
    public void createAccount(ActionEvent event)throws IOException, SQLException, ClassNotFoundException{
        DatabaseConnections db=new DatabaseConnections();
        db.createAccount(txtGetUsername.getText(),txtGetPassword.getText());    
    }
    public void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
    }    
   
}
