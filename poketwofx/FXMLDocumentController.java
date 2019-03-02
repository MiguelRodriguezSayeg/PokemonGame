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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
     @FXML 
     private TextField txtUsername;
     @FXML 
     private PasswordField txtPassword;
     @FXML 
     private Button bLogin;
     @FXML 
     private Label label;
     private Label lblAccount;
    public void handleClose(MouseEvent event) {
        System.exit(0);
    }
    public void goToCreateAccount(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("createaccount.fxml"));
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void login(ActionEvent event)throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader;
        Stage stage;
        DatabaseConnections db=new DatabaseConnections();
        if(db.validateExistance(txtUsername.getText())){
            if(db.validatePassword(txtUsername.getText(),txtPassword.getText())){
                db.setStatus(true, txtUsername.getText());
                loader = new FXMLLoader(getClass().getResource("WelcomeFXML.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.setScene(new Scene((Pane)loader.load()) );
                WelcomeFXMLController controller = loader.<WelcomeFXMLController>getController();
                controller.receiveUsername(txtUsername.getText());
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect password. Try again.");
            }
        }
        else{
        JOptionPane optionPane = new JOptionPane("Please create an account first.", JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog("Please create that account first.");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        }
    }
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
