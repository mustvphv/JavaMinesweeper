import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;
import javafx.scene.media.*;

public class Jeu extends Niveaux{
    Musique musique = new Musique();
    private TextField source = new TextField( );
    private TextField dest = new TextField( ) ;
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){ 
        menu();

        debutant.setOnAction(event->{musique.jouer_musique1();nivdebutant();primaryStage.setHeight(600);primaryStage.setWidth(800);});
         

        bouton_recommencer_partie_debutant.setOnAction(event->{musique.jouer_musique_retour();nivdebutant();});

        primaryStage.setScene(scene);
        primaryStage.show( );
    }

}

