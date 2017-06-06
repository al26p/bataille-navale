/**
 * Gestion du plateau
 * @author aprats
 * @author jroyere
 *
 *
 */
public class Plateau {
    int[][] plateau; //a modifier, l'atribut static ne convient pas

    /**
     * Constructeur de la classe
     * Ne demande par d'arguments, la taille de la grille est fixée à 10
     *
     */
    public Plateau(){
        int taille = 10;
        plateau = new int[taille][taille];

        for (int i=0;i<taille;i++) {
            for(int j=0; j<taille;j++) {
                plateau[i][j] = 0;
            }
        }

    }

    /**
     * Choix de l'orientation du bateau et de son positionement
     * Vérifie si le bateau peut être placé
     * Si oui, génère le bateau
     * @param plateau plateau sur lequel générer les navires
     * @param type Type de bateau à créer
     * @param horizontal si l'orientation du bateau est horizontale
     * @param coord_x position x du début du bateau
     * @param coord_y position y du début du bateau
     * @return si le bateau a bien ete place
     */
    public boolean posBateau(int[][] plateau, int type, boolean horizontal, int coord_x, int coord_y){
        boolean allOk=true;
        int taille=0;
        int xmax = plateau.length;

        int ymax = plateau[0].length;

        if(type==1){
            taille=5;
        }else if (type==2){
            taille=4;
        }else if (type==3){
            taille=3;
        }else if (type==4){
            taille=3;
        }else if (type==5){
            taille=2;
        }else{
            return false;
        }
        //System.out.println("ON EST LA");
        if (!horizontal){
            if(coord_y+taille>ymax){
                return false;
            }
            for(int i=0; i<taille; i++){
                if(plateau[coord_y+i][coord_x]!=0){
                    allOk = false;
                }
            }
        }
        //System.out.println("ON EST LA");
        if(horizontal){
            if(coord_x+taille>xmax){
                return false;
            }
            for(int i=0; i<taille; i++){
                if(plateau[coord_y][coord_x+i]!=0){
                    allOk = false;
                }
            }
        }
        // System.out.println("ON EST LA");

        if(allOk){
            genererBateaux(plateau,coord_x,coord_y,taille, horizontal);
            return true;
        }else{
            return false;
        }
    }

    /**
     *Placement du bateau sur le plateau
     */
    private void genererBateaux(int[][] plateau, int coord_y, int coord_x, int taille, boolean horizontal){

        if (horizontal){
            for (int j=coord_y; j<coord_y+taille; j++)
                plateau[coord_x][j]=1;
        }else{
            for(int i=coord_x; i<taille+coord_x; i++)
                plateau[i][coord_y]=1;
        }
    }

    /**Affiche le plateau de jeu
     *
     * @param plateau Le plateau de jeu
     */
    public void afficherPlateau (int[][] plateau) {
        int i,j;
        System.out.print("+|"); //AFFICHE L'EN TETE
        for(j=0; j<plateau[0].length;j++){
            System.out.print(j);
        }
        System.out.println("+");
        //FIN EN TETE

        System.out.print(" |"); //SEPARATION
        for(j=0; j<plateau[0].length;j++){
            System.out.print("-");
        }
        System.out.println("|");
        // FIN SEPARATION

        for(i=0; i<plateau.length; i++) {
            System.out.print(i+"|");

            for(j=0; j<plateau[0].length;j++){
                if(plateau[i][j]==0){
                    System.out.print("O");
                }else if(plateau[i][j]==1){
                    System.out.print("1");
                }else if(plateau[i][j]==2){
                    System.out.print("2");
                }else if(plateau[i][j]==3){
                    System.out.print("3");
                }else {
                    System.out.print("!");
                }
            }
            System.out.println("|");
        }

        System.out.print(" +");
        for(j=0; j<plateau[0].length;j++)
            System.out.print("-");
        System.out.println("+");
    }

    /**Fonction appelée lors de la reception d'un tir, permet d'actualiser la case touchée
     *
     * @param plateau Le plateau de jeu
     * @param coord_x La coordonée X du tir
     * @param coord_y La coordonnée Y du tir
     * @return L'état de la case
     */
    public int recuTir (int[][] plateau, int coord_x, int coord_y){
        int state = plateau[coord_x][coord_y];
        switch (state){
            case 0:
                state = 2;
                break;
            case 1:
                state = 3;
                break;
            case 2:
                state=2;
                break;
            case 3:
                state = 3;
                break;
            case 4:
                state = 4;
                break;

        }
        plateau[coord_x][coord_y]=state;
        return state;
    }


}