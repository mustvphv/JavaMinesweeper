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
import java.util.stream.IntStream;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;
import java.nio.charset.Charset;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.media.*;

abstract class Niveaux extends Application{
    Button choix_niveau = new Button("Quelle niveau choisissez vous?");
    Button debutant = new Button ("JOUER");
    GridPane grid = new GridPane( );
    GridPane grid2 = new GridPane( ) ;
    GridPane grid3 = new GridPane();
    GridPane gridretour = new GridPane( );
    GridPane grid2retour = new GridPane( ) ;
    Scene scene = new Scene (grid ,500 , 500);
    Button bouton_recommencer_partie_debutant=  new Button("Recommencer une partie");
    final Button[][] btn = new Button[10][10];
    int tab_bombes_i[] = new int[10];
    int tab_bombes_j[] = new int[10];
    int tab_cases_i[] = new int[1000];
    int tab_cases_j[] = new int[1000];
    int tab_cases_etat[] = new int[1000];
    int tab_coord_etat[][] = new int[10][10];
    int tab_drapeaux_i[] = new int[1000];
    int tab_drapeaux_j[] = new int[1000];
    static int incremente_drapeau = -1;
    static int pas_encore_cree = 1;

    public int initialise_tab_coord_etat(){
        int ic = 0;
        for(ic = 0; ic<10; ic++){
            for(int jc = 0; jc<10; ++jc){
                tab_coord_etat[ic][jc] = -1;
            }
        }
        return ic;
    }

    public void efface_donnees_precedentes(){
        int i;
        int j;
        incremente_drapeau = -1;
        for(i=0; i<tab_bombes_i.length; i++){
            tab_bombes_i[i]=-1;
            tab_bombes_j[i]=-1;
            tab_drapeaux_i[i]=-1;
            tab_drapeaux_j[i]=-1;
            
        }
        for(i=0; i<tab_cases_i.length; i++){
            tab_cases_i[i]=-1;
            tab_cases_j[i]=-1;
            tab_cases_etat[i]=-1;
        }
        for(i=0; i<tab_coord_etat.length; i++){
            for(j=0; j<tab_coord_etat.length; j++){
                tab_coord_etat[i][j]=-1;
            }
        }
    }

    static int nb_cases_jouees = 0;
    static int continuer=1;
    static int gagner=0;
    static int compteur_cases_touchees=0;
    static int icodrapeau = 0;
    static int jcodrapeau = 0;
    static int clic_droit = 0; 
    public void menu(){
        grid2.setHgap(100) ;
        grid2.add(debutant, 1, 0);
        grid2.setPadding (new Insets (100, 5, 5, 40)) ;
        grid.add(grid2, 1,2);
    }
    public void nivdebutant(){
        grid.getChildren().clear();
        efface_donnees_precedentes();
        grid.add(bouton_recommencer_partie_debutant, 0, 0);
        construit_jeu(btn);
    }

    public int recherche_indices(int i, int j){
        int z, k;
        z=-1;
        k=-1;
        while(k==-1 && z<100){
            if(i==tab_cases_i[z+1] && j==tab_cases_j[z+1]){
                k = z+1;
            }
            else{
                z++;
            }
        }
        return k;
    }
    
    public void enregistre_cases_touchees(int compteur_cases, int i, int j){
        Bouton bouton_c = new Bouton();

                        if(tab_cases_etat[compteur_cases]==0){
                               btn[i][j] = bouton_c.etat_demande(btn[i][j], 20);
                        }
                        if(tab_cases_etat[compteur_cases]==1){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 1);
                        }
                        if(tab_cases_etat[compteur_cases]==2){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 2);
                        }
                        if(tab_cases_etat[compteur_cases]==3){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 3);
                        }
                        if(tab_cases_etat[compteur_cases]==4){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 4);
                        }
                        if(tab_cases_etat[compteur_cases]==5){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 5);
                        }
                        if(tab_cases_etat[compteur_cases]==6){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 6);
                        }
                        if(tab_cases_etat[compteur_cases]==7){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 7);
                        }
                        if(tab_cases_etat[compteur_cases]==8){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 8);
                        }
                        compteur_cases++;
    }

public void faire_apparaitre_mines(int GagnerOuPerdre){
        Bouton bouton_c = new Bouton();
        int i = 0;
        for(i=0;i<10;i++){
            if(GagnerOuPerdre==0){
                if((tab_coord_etat[tab_bombes_i[i]][tab_bombes_j[i]] == 0) || (tab_coord_etat[tab_bombes_i[i]][tab_bombes_j[i]] == 10)){
                    btn[tab_bombes_i[i]][tab_bombes_j[i]] = bouton_c.etat_demande(btn[tab_bombes_i[i]][tab_bombes_j[i]], 11);
                }
                if(tab_coord_etat[tab_bombes_i[i]][tab_bombes_j[i]] == 5){
                    btn[tab_bombes_i[i]][tab_bombes_j[i]] = bouton_c.etat_demande(btn[tab_bombes_i[i]][tab_bombes_j[i]], 13);
                }
            }
            if(GagnerOuPerdre==1){
                if((tab_coord_etat[tab_bombes_i[i]][tab_bombes_j[i]] == 0) || (tab_coord_etat[tab_bombes_i[i]][tab_bombes_j[i]] == 10)){
                    btn[tab_bombes_i[i]][tab_bombes_j[i]] = bouton_c.etat_demande(btn[tab_bombes_i[i]][tab_bombes_j[i]], 10);
                }
                if(tab_coord_etat[tab_bombes_i[i]][tab_bombes_j[i]] == 5){
                    btn[tab_bombes_i[i]][tab_bombes_j[i]] = bouton_c.etat_demande(btn[tab_bombes_i[i]][tab_bombes_j[i]], 13);
                }
            }
        }
        if(GagnerOuPerdre==0){
           for(i=0; i<incremente_drapeau; ++i){
               if(tab_coord_etat[tab_drapeaux_i[i]][tab_drapeaux_j[i]] == 6){
                   btn[tab_drapeaux_i[i]][tab_drapeaux_j[i]] = bouton_c.etat_demande(btn[tab_drapeaux_i[i]][tab_drapeaux_j[i]], 14);
               }
               else{
                   btn[tab_drapeaux_i[i]][tab_drapeaux_j[i]] = bouton_c.etat_demande(btn[tab_drapeaux_i[i]][tab_drapeaux_j[i]], 13);
               }
           }
        }
}

    public void avez_vous_gagne(){
        Musique musique_a = new Musique();
        if(compteur_cases_touchees==((btn.length-1)*(btn.length-1))-10){

            musique_a.jouer_musique_victoire();
            Label vict = new Label("VOUS AVEZ");
            String str1 = "GAGNÉ!";
            String str2 = new String(str1.getBytes(),Charset.forName("UTF-8"));
            Label partie_gagnee = new Label(str2);
            partie_gagnee.setTextFill(Color.GREEN);
            partie_gagnee.setFont(new Font("Arial", 18));
            grid.add(vict, 0, 9);
            grid.add(partie_gagnee, 0,10);
            continuer=0;
            gagner=1;
        }
    }

    public void supprimedrapeau(int ki, int kj){
       for(int i=0; i<incremente_drapeau; i++){
           if(tab_drapeaux_i[i]==ki && tab_drapeaux_j[i]==kj){
               tab_drapeaux_i[i]=tab_drapeaux_i[incremente_drapeau];
               tab_drapeaux_j[i]=tab_drapeaux_j[incremente_drapeau];
               incremente_drapeau--;
           }
       }
    }


    public void transformedrapeau(int ki, int kj){
            Bouton bouton_c = new Bouton();
                        {
                        if(tab_coord_etat[ki][kj]==-1){
                            incremente_drapeau++;
                            tab_drapeaux_i[incremente_drapeau]=ki;
                            tab_drapeaux_j[incremente_drapeau]=kj;
                            tab_coord_etat[ki][kj]=6;
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 13);
                        }
                        else if(tab_coord_etat[ki][kj]==0){
                            incremente_drapeau++;
                            tab_drapeaux_i[incremente_drapeau]=ki;
                            tab_drapeaux_j[incremente_drapeau]=kj;
                            tab_coord_etat[ki][kj]=5;
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 13);
                        }
                        else if(tab_coord_etat[ki][kj]==5){
                            supprimedrapeau(ki, kj);
                            tab_coord_etat[ki][kj]=10;
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 15);
                        }
                        else if(tab_coord_etat[ki][kj]==6){
                            supprimedrapeau(ki, kj);
                            tab_coord_etat[ki][kj]=11;
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 15);
                        }
                        else if(tab_coord_etat[ki][kj]==10){
                            tab_coord_etat[ki][kj]=0;
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 0);
                        }
                        else if(tab_coord_etat[ki][kj]==11){
                            tab_coord_etat[ki][kj]=-1;
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 0);
                        }
                        }
     }



    public void transfcaseetat(int compteur_bombes_autour1, int ki, int kj, int kico, int kjco){
            Bouton bouton_c = new Bouton();

                        if(compteur_bombes_autour1==1 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 1);
                            tab_cases_etat[nb_cases_jouees]=1;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==2 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 2);
                            tab_cases_etat[nb_cases_jouees]=2;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==3 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 3);
                            tab_cases_etat[nb_cases_jouees]=3;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==4 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 4);
                            tab_cases_etat[nb_cases_jouees]=4;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==5 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 5);
                            tab_cases_etat[nb_cases_jouees]=5;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==6 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 6);
                            tab_cases_etat[nb_cases_jouees]=6;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==7 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 7);
                            tab_cases_etat[nb_cases_jouees]=7;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }
                        if(compteur_bombes_autour1==8 && (ki==kico && kj==kjco)){
                            btn[ki][kj] = bouton_c.etat_demande(btn[ki][kj], 8);
                            tab_cases_etat[nb_cases_jouees]=8;
                            tab_cases_i[nb_cases_jouees]=ki;
                            tab_cases_j[nb_cases_jouees]=kj;
                            tab_coord_etat[ki][kj] = 1;
                            nb_cases_jouees++;
                        }

   }







    


    public void consdebutant(Button[][] bouton, int ico, int jco){
        Bouton bouton_c = new Bouton();
        bouton[ico][jco]= new Button();
        int compteur_bombes_bis = 0;
        int compteur_bombes_bisb = 0;
        int compteur_bombes_tris = 0;
        int compteur_bombes_autour = 0;
        int indice = 0;
        int indice2 = 0;
        int v = 0;
        int fin_cycle = 0;
        Musique musique_c = new Musique();
        
        if(continuer==1){
        for(int i=1; i<btn.length ; i++){
                for(int j=1; j<btn.length ;j++){
                    avez_vous_gagne();

                    if((tab_coord_etat[i][j]) % 5 == 0){
                       if (clic_droit==0) {
                        if((tab_coord_etat[ico][jco]==0) || (tab_coord_etat[ico][jco]==10)){
                            if(continuer==0 && gagner==1){
                                faire_apparaitre_mines(1);
  
                            }
                            else{
                            faire_apparaitre_mines(0);
                            btn[ico][jco] = bouton_c.etat_demande(btn[ico][jco], 12);
                            musique_c.jouer_musique_fail();
                            Label gameover = new Label("VOUS AVEZ");
                            Label gameover2 = new Label("PERDU!");
                            gameover.setTextFill(Color.RED);
                            gameover.setFont(new Font("Arial", 18));
                            gameover2.setTextFill(Color.RED);
                            gameover2.setFont(new Font("Arial", 18));
                            grid.add(gameover, 0, 9);
                            grid.add(gameover2, 0, 10);
                            if (continuer==1) {
                                    continuer=0;
                                    gagner=0;
                            }
                            }
                        }
                        else{
                            if(tab_coord_etat[i][j]==0){
                                btn[i][j] = bouton_c.etat_demande(btn[i][j], 10);
                            }
                            else if (tab_coord_etat[i][j]==5){
                                    btn[i][j] = bouton_c.etat_demande(btn[i][j], 13);
                                 }
                                 else {
                                    btn[i][j] = bouton_c.etat_demande(btn[i][j], 15);
                                 }                        
                         }
                       }
                       else {
                          if (i==ico && j==jco){
                              transformedrapeau(i, j);

                          }
                          else if(tab_coord_etat[i][j]==0){
                                btn[i][j] = bouton_c.etat_demande(btn[i][j], 10);
                               }
                               else if (tab_coord_etat[i][j]==5){
                                    btn[i][j] = bouton_c.etat_demande(btn[i][j], 13);
                                    }
                                    else {
                                       btn[i][j] = bouton_c.etat_demande(btn[i][j], 15);
                                    }                        
                       
                       }
                    }

                    else{
                        
                      if (clic_droit==0) {
                            
                        indice = recherche_indices(i, j);
                        
                        if((indice>-1) || (tab_coord_etat[i][j]==6) || (tab_coord_etat[i][j]==11)){
                        if (tab_coord_etat[i][j]==1) {
                                        enregistre_cases_touchees(indice, i, j);
                                 }
                        
                                 else if (tab_coord_etat[i][j]==6) {
                                    btn[i][j] = bouton_c.etat_demande(btn[i][j], 13);
                                 }

                                 else if ((tab_coord_etat[i][j]==11) && (i==ico) && (j==jco)) {
                                    tab_coord_etat[i][j]=-1;
                                    btn[i][j] = bouton_c.etat_demande(btn[i][j], 0);
                                 }

                                 else if ((tab_coord_etat[i][j]==11) && (i!=ico) && (j!=jco)) {
                                    btn[i][j] = bouton_c.etat_demande(btn[i][j], 15);
                                 }
        
                        }
                        
                        else if(i==ico && j==jco){

                        compteur_cases_touchees++;
                        compteur_bombes_autour = 0;

                        if((i-1)>=1 && (i-1)<=9 && j>=1 && j<=9){
                        if((tab_coord_etat[i-1][j]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if((i+1)>=1 && (i+1)<=9 && j>=1 && j<=9){
                        if((tab_coord_etat[i+1][j]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if(i>=1 && i<=9 && (j-1)>=1 && (j-1)<=9){
                        if((tab_coord_etat[i][j-1]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if(i>=1 && i<=9 && (j+1)>=1 && (j+1)<=9){
                        if((tab_coord_etat[i][j+1]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if((i-1)>=1 && (i-1)<=9 && (j-1)>=1 && (j-1)<=9){
                        if((tab_coord_etat[i-1][j-1]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if((i+1)>=1 && (i+1)<=9 && (j+1)>=1 && (j+1)<=9){
                        if((tab_coord_etat[i+1][j+1]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if((i+1)>=1 && (i+1)<=9 && (j-1)>=1 && (j-1)<=9){
                        if((tab_coord_etat[i+1][j-1]%5) == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if((i-1)>=1 && (i-1)<=9 && (j+1)>=1 && (j+1)<=9){
                        if((tab_coord_etat[i-1][j+1])%5 == 0){
                            compteur_bombes_autour++;
                        }
                        }
                        if(compteur_bombes_autour==0 && (i==ico && j==jco) ){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 20);
                            tab_cases_etat[nb_cases_jouees]=0;
                            tab_cases_i[nb_cases_jouees]=i;
                            tab_cases_j[nb_cases_jouees]=j;
                            tab_coord_etat[i][j] = 1;
                            nb_cases_jouees++;

                            
                            int ifinalprop1 = i-1;
                            int jfinalprop1 = j;
                            int ifinalprop2 = i+1;
                            int jfinalprop2 = j;
                            int ifinalprop3 = i;
                            int jfinalprop3 = j-1;
                            int ifinalprop4 = i;
                            int jfinalprop4 = j+1;
                            int ifinalprop5 = i-1;
                            int jfinalprop5 = j-1;
                            int ifinalprop6 = i+1;
                            int jfinalprop6 = j+1;
                            int ifinalprop7 = i+1;
                            int jfinalprop7 = j-1;
                            int ifinalprop8 = i-1;
                            int jfinalprop8 = j+1;
                            
                            
                            consdebutant(btn, ifinalprop1, jfinalprop1);
                            if((ifinalprop2>=1 && ifinalprop2<=9) && (jfinalprop2>=1 && jfinalprop2<=9) && (tab_coord_etat[ifinalprop2][jfinalprop2] == -1)){
                                consdebutant(btn, ifinalprop2, jfinalprop2);
                            }
                            if((ifinalprop3>=1 && ifinalprop3<=9) && (jfinalprop3>=1 && jfinalprop3<=9) && (tab_coord_etat[ifinalprop3][jfinalprop3] == -1)){
                                consdebutant(btn, ifinalprop3, jfinalprop3);
                            }
                            if((ifinalprop4>=1 && ifinalprop4<=9) && (jfinalprop4>=1 && jfinalprop4<=9) && (tab_coord_etat[ifinalprop4][jfinalprop4] == -1)){
                                consdebutant(btn, ifinalprop4, jfinalprop4);
                            }
                            if((ifinalprop5>=1 && ifinalprop5<=9) && (jfinalprop5>=1 && jfinalprop5<=9) && (tab_coord_etat[ifinalprop5][jfinalprop5] == -1)){
                                consdebutant(btn, ifinalprop5, jfinalprop5);
                            }
                            if((ifinalprop6>=1 && ifinalprop6<=9) && (jfinalprop6>=1 && jfinalprop6<=9) && (tab_coord_etat[ifinalprop6][jfinalprop6] == -1)){
                                consdebutant(btn, ifinalprop6, jfinalprop6);
                            }
                            if((ifinalprop7>=1 && ifinalprop7<=9) && (jfinalprop7>=1 && jfinalprop7<=9) && (tab_coord_etat[ifinalprop7][jfinalprop7] == -1)){
                                consdebutant(btn, ifinalprop7, jfinalprop7);
                            }
                            if((ifinalprop8>=1 && ifinalprop8<=9) && (jfinalprop8>=1 && jfinalprop8<=9) && (tab_coord_etat[ifinalprop8][jfinalprop8] == -1)){
                                consdebutant(btn, ifinalprop8, jfinalprop8);
                            }
                          
                            break;

                        }
                        transfcaseetat(compteur_bombes_autour, i, j, ico, jco);
                        }
                        else{
                                btn[i][j] = bouton_c.etat_demande(btn[i][j], 0);
                        }
                        }
                        else {
                          if (i==ico && j==jco){
                              transformedrapeau(i, j);

                          }
                          else if (tab_coord_etat[i][j]==-1){
                                   btn[i][j] = bouton_c.etat_demande(btn[i][j], 0);
                               }
                               else if (tab_coord_etat[i][j]==1){
                                       indice = recherche_indices(i, j);
                        
                                       if (indice>-1) {
                                           enregistre_cases_touchees(indice, i, j);
                                       }
                                    }
                               else if (tab_coord_etat[i][j]==6){
                                       btn[i][j] = bouton_c.etat_demande(btn[i][j], 13);
                                    }
                               else if (tab_coord_etat[i][j]==11){
                                       btn[i][j] = bouton_c.etat_demande(btn[i][j], 15);
                                    }                        
                                       



                        }
                        
                        }
                      Label label = new Label();
                      int ifinal = i;
                      int jfinal = j;

                      btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
 
                           @Override
                           public void handle(MouseEvent event) {
                               MouseButton button = event.getButton();
                               if(button==MouseButton.PRIMARY){
                                   clic_droit=0;
                                   if(continuer==1){
                                       consdebutant(btn, ifinal, jfinal);
                                   }
                               }else if(button==MouseButton.SECONDARY){
                                   icodrapeau=ifinal;
                                   jcodrapeau=jfinal;
                                   clic_droit=1;
                                   if(continuer==1){
                                       consdebutant(btn, icodrapeau, jcodrapeau);
                                   }
                                   
                               }else if(button==MouseButton.MIDDLE){
                                   clic_droit=0;
                           }
            }
        });
 
                        grid.add(btn[i][j], i, j);
                        
                
                }
        }
        }
    }

    public void construit_jeu(Button[][] bouton){
        nb_cases_jouees=0;
        compteur_cases_touchees=0;
        continuer=1;
        efface_donnees_precedentes();
        initialise_tab_coord_etat();
        int ico=1;
        int jco=1;
        Bouton bouton_c = new Bouton();
        bouton[ico][jco]= new Button();
        int compteur_bombes=0;
        int commencer_a_jouer=0;
        int NB_MAX2BOMBES=10;
        int compteur_cases = 0;
        for(int i=1; i<btn.length; i++){
                for(int j=1; j<btn.length;j++){
                        if(Math.random() * (10-1) > 8 && compteur_bombes<10){
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 10);
                            tab_bombes_i[compteur_bombes]=i;
                            tab_bombes_j[compteur_bombes]=j;
                            tab_coord_etat[i][j] = 0;
                            commencer_a_jouer=1;
                            compteur_bombes++;
                        }
                        else{
                        if(tab_cases_etat[compteur_cases]==0){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 0);
                        }
                        else if(tab_cases_etat[compteur_cases]==1){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 1);
                        }
                        else if(tab_cases_etat[compteur_cases]==2){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 2);
                        }
                        else if(tab_cases_etat[compteur_cases]==3){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 3);
                        }
                        else if(tab_cases_etat[compteur_cases]==4){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 4);
                        }
                        else if(tab_cases_etat[compteur_cases]==5){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 5);
                        }
                        else if(tab_cases_etat[compteur_cases]==6){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 6);
                        }
                        else if(tab_cases_etat[compteur_cases]==7){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 7);
                        }
                        else if(tab_cases_etat[compteur_cases]==8){
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 8);
                        }
                        else{
                            compteur_cases++;
                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 0);
                            commencer_a_jouer=1;
                        }

                            btn[i][j] = bouton_c.etat_demande(btn[i][j], 0);
                            commencer_a_jouer=1;
                        }
                        
                    final int ifinal = i;
                    final int jfinal = j;                          
                        
                        btn[ifinal][jfinal].setOnAction(event->{consdebutant(btn,ifinal,jfinal);});


                        grid.add(btn[i][j], i, j);

                }

        }
        if(compteur_bombes != 10){
            compteur_bombes=0;
            construit_jeu(btn);
        }
    }

}

