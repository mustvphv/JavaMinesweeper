import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javax.swing.JButton;
import javafx.scene.media.*;        

public class Bouton{
    public Button etat_demande(Button bouton_a_changer, int etat_bouton){
        Image mineimage=new Image("files/mine.png");
        ImageView mineimagev=new ImageView(mineimage);
        mineimagev.setFitHeight(22);
        mineimagev.setFitWidth(22);
        Image drapeauimage=new Image("files/drapeau.png");
        ImageView drapeauimagev=new ImageView(drapeauimage);
        drapeauimagev.setFitHeight(22);
        drapeauimagev.setFitWidth(22);
        Image pointinterimage=new Image("files/pointinter.png");
        ImageView pointinterimagev=new ImageView(pointinterimage);
        pointinterimagev.setFitHeight(22);
        pointinterimagev.setFitWidth(22);
        String texte1 = new String("1");
        if(etat_bouton==0){
            bouton_a_changer = new Button("  ");
        }
        if(etat_bouton==20){
            bouton_a_changer = new Button("");
            bouton_a_changer.setStyle("-fx-background-color: white; -fx-border-color:black");
        }
        if(etat_bouton==1){
            bouton_a_changer = new Button("1");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:blue;");
        }
        if(etat_bouton==2){
            bouton_a_changer = new Button("2");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:green;");
        }
        if(etat_bouton==3){
            bouton_a_changer = new Button("3");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:red;");
        }
        if(etat_bouton==4){
            bouton_a_changer = new Button("4");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:purple;");
        }
        if(etat_bouton==5){
            bouton_a_changer = new Button("5");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:black;");
        }
        if(etat_bouton==6){
            bouton_a_changer = new Button("6");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:black;");
        }
        if(etat_bouton==7){
            bouton_a_changer = new Button("7");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:black;");
        }
        if(etat_bouton==8){
            bouton_a_changer = new Button("8");
            bouton_a_changer.setStyle("-fx-font-size:18; -fx-text-fill:black;");
        }
        if(etat_bouton==11){
            bouton_a_changer = new Button("",mineimagev);
            
        }
        if(etat_bouton==10){
            bouton_a_changer = new Button("");
        }
        if(etat_bouton==12){
            bouton_a_changer = new Button("",mineimagev);
            bouton_a_changer.setStyle("-fx-background-color:red");
        }
        if(etat_bouton==13){
            bouton_a_changer = new Button("",drapeauimagev);
        }
        if(etat_bouton==14){
            bouton_a_changer = new Button("R");
        }
        if(etat_bouton==15){
            bouton_a_changer = new Button("",pointinterimagev);
        }
        bouton_a_changer.setPrefHeight(40);
        bouton_a_changer.setPrefWidth(40);
        return bouton_a_changer;
    }    
}

