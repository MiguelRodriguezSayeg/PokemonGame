/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeMath.round;

public class GameFXMLController implements Initializable {
    boolean ready=true;
    String username;
    @FXML
    Button btnM1,btnM2,btnM3,btnM4,btnGiveUp,btnGo;
    @FXML
    ComboBox cmbPokemon;
    @FXML
    Label myName,botName,lblMyMax,lblBotMax;
    @FXML
    ProgressBar mypg,botpg;
    @FXML
    ImageView myPokemon, botPokemon;
    Trainers trainer;
    Battles battle;
    ObservableList<String> teams2;
    ArrayList<String> sand;
    ArrayList<Pokemon> pkmn;
    DatabaseConnections db;
    int myActualHash;
    boolean forceChange=false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void receiveTrainer(Trainers trainer) throws ClassNotFoundException, SQLException{
        this.trainer=trainer;
        this.username=trainer.Username;
        sand=new ArrayList<>();
        battle=new Battles(trainer);
        db=new DatabaseConnections();
        myName.setText(battle.t1.Team.pkmn.get(0).getName().trim());
        botName.setText(battle.t2.Team.pkmn.get(0).getName().trim());
        String foto = "/images/Pokemon/" + battle.t1.Team.pkmn.get(0).getName().trim().toLowerCase()+ "_b.gif";
        String foto2 = "/images/Pokemon/" + battle.t2.Team.pkmn.get(0).getName().trim().toLowerCase()+ "_f.gif";
        pkmn = trainer.Team.pkmn;
        myActualHash=trainer.Team.pkmn.get(0).hashCode();
        for(int x=0;x<pkmn.size();x++){
            sand.add(pkmn.get(x).getName().trim());
        }
        teams2 = FXCollections.observableArrayList(sand);
        cmbPokemon.setItems(teams2);
        myPokemon.setImage(new Image(foto));
        botPokemon.setImage(new Image(foto2));
        lblMyMax.setText(String.valueOf(battle.t1.Team.pkmn.get(0).HP));
        lblBotMax.setText(String.valueOf(battle.t2.Team.pkmn.get(0).HP));
        btnM1.setText(battle.t1.Team.pkmn.get(0).moves.get(0).name.trim());
        btnM2.setText(battle.t1.Team.pkmn.get(0).moves.get(1).name.trim());
        btnM3.setText(battle.t1.Team.pkmn.get(0).moves.get(2).name.trim());
        btnM4.setText(battle.t1.Team.pkmn.get(0).moves.get(3).name.trim());
        System.out.println(battle.t2.Team.pkmn.get(0).moves.get(1).name.trim());
        mypg.setProgress(1);
        botpg.setProgress(1);
    }
    public void handleClose(MouseEvent event) throws SQLException, ClassNotFoundException {
        db.addLoss(username);
        db.setStatus(false, username);
        System.exit(0);
    }
    public int getMyFirst(){
        Pokemon first;
        int cmbpkmn=0;
            for(int x=0;x<battle.t1.Team.pkmn.size();x++){
                if(battle.t1.Team.pkmn.get(x).hashCode()==myActualHash){
                    cmbpkmn=x;
                }
            }
        first=pkmn.get(cmbpkmn);
        return cmbpkmn;
    }
    public int getMyAlives(){
        int cmbpkmn=0;
            for(int x=0;x<battle.t1.Team.pkmn.size();x++){
                if(battle.t1.Team.pkmn.get(x).getAlive()==true){
                    cmbpkmn++;
                }
            }
        return cmbpkmn;
    }
    public int getEnemyAlives(){
        int cmbpkmn=0;
            for(int x=0;x<battle.t2.Team.pkmn.size();x++){
                if(battle.t2.Team.pkmn.get(x).getAlive()==true){
                    cmbpkmn++;
                }
            }
        return cmbpkmn;
    }
    public int getEnemyPokemon(){
        boolean yaentre=false;
        int cmbpkmn=0;
            for(int x=0;x<battle.t2.Team.pkmn.size();x++){
                if(battle.t2.Team.pkmn.get(x).getAlive()==true&&yaentre==false){
                    cmbpkmn=x;
                    yaentre=true;
                }
            }
        return cmbpkmn;
    }
    public void switchPokemon(ActionEvent event) throws SQLException, ClassNotFoundException, IOException{
        int cmbpkmn=0;
        boolean somebodyDied=false;
        DecimalFormat df = new DecimalFormat("#.00");
        this.forceChange=false;
        int newindex=cmbPokemon.getSelectionModel().getSelectedIndex();
        if(pkmn.get(newindex).hashCode()!=myActualHash){
        myActualHash=battle.t1.Team.pkmn.get(newindex).hashCode();
        for(int x=0;x<battle.t1.Team.pkmn.size();x++){
            if(battle.t1.Team.pkmn.get(x).hashCode()==myActualHash){
                cmbpkmn=x;
            }
        }
        myName.setText(battle.t1.Team.pkmn.get(cmbpkmn).getName().trim());
        lblMyMax.setText(String.valueOf(battle.t1.Team.pkmn.get(cmbpkmn).HP));
        String foto = "/images/Pokemon/" + battle.t1.Team.pkmn.get(cmbpkmn).getName().trim().toLowerCase()+ "_b.gif";
        myPokemon.setImage(new Image(foto));
        btnM1.setText(battle.t1.Team.pkmn.get(cmbpkmn).moves.get(0).name.trim());
        btnM2.setText(battle.t1.Team.pkmn.get(cmbpkmn).moves.get(1).name.trim());
        btnM3.setText(battle.t1.Team.pkmn.get(cmbpkmn).moves.get(2).name.trim());
        btnM4.setText(battle.t1.Team.pkmn.get(cmbpkmn).moves.get(3).name.trim());
        double prc=((double)battle.t1.Team.pkmn.get(cmbpkmn).getcHP()/(double)battle.t1.Team.pkmn.get(cmbpkmn).getHP());
        mypg.setProgress(Double.valueOf(df.format(prc)));
        Random r = new Random();
        int rand=r.nextInt((3 - 0) + 1);
        int enemy=getEnemyPokemon();
        int mypk=cmbpkmn;
        Moves rec=convertToMove(battle.t2.Team.pkmn.get(enemy).moves.get(rand).getMoveName());
        Rules reglas=new Rules();
        System.out.println("hola");
        int dambot=reglas.decideDamage(rec, battle.t2.Team.pkmn.get(enemy), battle.t1.Team.pkmn.get(mypk));
        battle.t1.Team.pkmn.get(mypk).setcHP(-dambot);
        System.out.println("Te quedan "+battle.t1.Team.pkmn.get(mypk).getcHP());
        prc=((double)battle.t1.Team.pkmn.get(mypk).getcHP()/(double)battle.t1.Team.pkmn.get(mypk).getHP());
        mypg.setProgress(Double.valueOf(df.format(prc)));
        if(prc<=0){
           if(getMyAlives()>0){
           this.forceChange=true;
           battle.t1.Team.pkmn.remove(mypk);
           teams2.remove(mypk);
           cmbPokemon.setItems(teams2);
           lblMyMax.setText("");
           myName.setText("");
           myPokemon.setImage(SelectedImages.nss);
           btnM1.setText("");
           btnM2.setText("");
           btnM3.setText("");
           btnM4.setText("");
           }
           else{
           battle.gameOver=true;
           battle.winner=battle.t2;
           battle.loser=battle.t1;
           JOptionPane.showMessageDialog(null, "You lose.");
           db.addLoss(username);
           gotoMain(event);
           
           }
        }
        
        }
    }
    public void selectAttack(ActionEvent event) throws SQLException, ClassNotFoundException, IOException{
        String movestring="";
        Random r = new Random();
        int rand=r.nextInt((3 - 0) + 1);
        System.out.println("El random es "+rand);
        double prc;
        boolean somebodyDied=false;
        DecimalFormat df = new DecimalFormat("#.00");
        if(ready==true){
        if(forceChange!=true){
            this.ready=false;
        if(event.getSource()==btnM1){
            movestring=btnM1.getText();
        }
        else if(event.getSource()==btnM2){
            movestring=btnM2.getText();
        }
        else if(event.getSource()==btnM3){
            movestring=btnM3.getText();
        }
        else if(event.getSource()==btnM4){
            movestring=btnM4.getText();
        }
        //convertir string a objeto de movimiento
        Moves use=convertToMove(movestring);
        
        //obtener indexes de pokemon
        int mypk=getMyFirst();
        System.out.println("Mi numero de pk es"+mypk);
        int enemy=getEnemyPokemon();
        Moves rec=convertToMove(battle.t2.Team.pkmn.get(enemy).moves.get(rand).getMoveName());
        System.out.println("Mi numero de enemy es"+enemy);
        Rules reglas=new Rules();
        int dammine=reglas.decideDamage(use, battle.t1.Team.pkmn.get(mypk), battle.t2.Team.pkmn.get(enemy));
        int dambot=reglas.decideDamage(rec, battle.t2.Team.pkmn.get(enemy), battle.t1.Team.pkmn.get(mypk));
        System.out.println("I hitted "+dammine);
        System.out.println("I received"+dambot);
        if(battle.t2.Team.pkmn.get(enemy).getSpeed()<=battle.t1.Team.pkmn.get(mypk).getSpeed()){
        System.out.println("Yo fui màs rapido, con "+battle.t1.Team.pkmn.get(mypk).getSpeed()+"vs "+battle.t2.Team.pkmn.get(enemy).getSpeed());    
        battle.t2.Team.pkmn.get(enemy).setcHP(-dammine);
        System.out.println("Le quedan "+battle.t2.Team.pkmn.get(enemy).getcHP());
        prc=((double)battle.t2.Team.pkmn.get(enemy).getcHP()/(double)battle.t2.Team.pkmn.get(enemy).getHP());
        botpg.setProgress(Double.valueOf(df.format(prc)));
        if(prc<=0){
            if(getEnemyAlives()>0){
            battle.t2.Team.pkmn.remove(enemy);
            enemy=getEnemyPokemon();
            String foto= "/images/Pokemon/" + battle.t2.Team.pkmn.get(enemy).getName().trim().toLowerCase()+ "_f.gif";
            botPokemon.setImage(new Image(foto));
            botName.setText(battle.t2.Team.pkmn.get(enemy).getName());
            lblBotMax.setText(String.valueOf(battle.t2.Team.pkmn.get(enemy).getHP()));
            prc=((double)battle.t2.Team.pkmn.get(enemy).getcHP()/(double)battle.t2.Team.pkmn.get(enemy).getHP());
            botpg.setProgress(prc);
            somebodyDied=true;
            }
            else{
            battle.gameOver=true;
            battle.winner=battle.t1;
            battle.loser=battle.t2;
            JOptionPane.showMessageDialog(null, "You won the battle.");
            db.addWin(username);
            gotoMain(event);
            }
        }
        if(somebodyDied==false){
            battle.t1.Team.pkmn.get(mypk).setcHP(-dambot);
            System.out.println("Te quedan "+battle.t1.Team.pkmn.get(mypk).getcHP());
            prc=((double)battle.t1.Team.pkmn.get(mypk).getcHP()/(double)battle.t1.Team.pkmn.get(mypk).getHP());
            mypg.setProgress(Double.valueOf(df.format(prc)));
            if(prc<=0){
            if(getMyAlives()>0){
            this.forceChange=true;
            battle.t1.Team.pkmn.remove(mypk);
            teams2.remove(mypk);
            cmbPokemon.setItems(teams2);
            lblMyMax.setText("");
            myName.setText("");
            myPokemon.setImage(SelectedImages.nss);
            btnM1.setText("");
            btnM2.setText("");
            btnM3.setText("");
            btnM4.setText("");
            somebodyDied=true;
           }
           else{
           battle.gameOver=true;
           battle.winner=battle.t2;
           battle.loser=battle.t1;
           JOptionPane.showMessageDialog(null, "You lose.");
           db.addLoss(username);
           gotoMain(event);
           }
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "There was no enemy to hit.");
        }
        }
        else{
        System.out.println("El otro fue màs ràpido, con"+battle.t2.Team.pkmn.get(enemy).getSpeed()+"vs"+battle.t1.Team.pkmn.get(mypk).getSpeed());
        battle.t1.Team.pkmn.get(mypk).setcHP(-dambot);
        System.out.println("Te quedan "+battle.t1.Team.pkmn.get(mypk).getcHP());
        prc=((double)battle.t1.Team.pkmn.get(mypk).getcHP()/(double)battle.t1.Team.pkmn.get(mypk).getHP());
        mypg.setProgress(Double.valueOf(df.format(prc)));
        if(prc<=0){
           if(getMyAlives()>0){
           this.forceChange=true;
           battle.t1.Team.pkmn.remove(mypk);
           teams2.remove(mypk);
           cmbPokemon.setItems(teams2);
           lblMyMax.setText("");
           myName.setText("");
           myPokemon.setImage(SelectedImages.nss);
           btnM1.setText("");
           btnM2.setText("");
           btnM3.setText("");
           btnM4.setText("");
           somebodyDied=true;
           }
           else{
           battle.gameOver=true;
           battle.winner=battle.t2;
           battle.loser=battle.t1;
           JOptionPane.showMessageDialog(null, "You lose.");
           db.addLoss(username);
           gotoMain(event);
           
           }
        }
        if(somebodyDied==false){
            battle.t2.Team.pkmn.get(enemy).setcHP(-dammine);
            System.out.println("Le quedan "+battle.t2.Team.pkmn.get(enemy).getcHP());
            prc=((double)battle.t2.Team.pkmn.get(enemy).getcHP()/(double)battle.t2.Team.pkmn.get(enemy).getHP());
            botpg.setProgress(Double.valueOf(df.format(prc)));
            if(prc<=0){
            if(getEnemyAlives()>0){
            battle.t2.Team.pkmn.remove(enemy);
            enemy=getEnemyPokemon();
            String foto= "/images/Pokemon/" + battle.t2.Team.pkmn.get(enemy).getName().trim().toLowerCase()+ "_f.gif";
            botPokemon.setImage(new Image(foto));
            botName.setText(battle.t2.Team.pkmn.get(enemy).getName());
            lblBotMax.setText(String.valueOf(battle.t2.Team.pkmn.get(enemy).getHP()));
            prc=((double)battle.t2.Team.pkmn.get(enemy).getcHP()/(double)battle.t2.Team.pkmn.get(enemy).getHP());
            botpg.setProgress(prc);
            somebodyDied=true;
            }
            else{
            battle.gameOver=true;
            battle.winner=battle.t1;
            battle.loser=battle.t2;
            JOptionPane.showMessageDialog(null, "You won.");
            db.addWin(username);
            gotoMain(event);
            }
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "There was no enemy to hit.");
        }
        }
        }
        else{
        JOptionPane.showMessageDialog(null, "Change your Pokemon first.");
        }
        }
        this.ready=true;
    }
    public Moves convertToMove(String move) throws SQLException, ClassNotFoundException{
        Moves newMove;
        ArrayList<String>movstats=db.getMoveStats(move);
        newMove=new Moves(movstats.get(0),movstats.get(1),movstats.get(2),Integer.valueOf(movstats.get(3)),movstats.get(4));
        return newMove;
    }
    public void giveUp(ActionEvent event) throws SQLException, ClassNotFoundException, IOException{
            db.addLoss(username);
            gotoMain(event);
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
}
