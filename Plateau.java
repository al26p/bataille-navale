/**
 * Gestion du plateau
 * @author aprats 
 * @author jroyere
 * 
 * 
 */
public class Plateau {
    static int[][] plateau; //a modifier, l'atribut static ne convient pas
    
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
     * @param type Type de bateau à créer
     * @param vertical si l'orientation du bateau est verticale
     * @param coord_x position x du début du bateau 
     * @param coord_y position y du début du bateau 
     */
    public boolean posBateau(int type, boolean vertical, int cood_x, int coord_y){
        boolean allOk=true;
        int taille=0;
        
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
            allOk=false;
        }
        
        if(allOk){
            genererBateaux(taille, vertical);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     *Placement du bateau sur le plateau
     */
    private void genererBateaux(int taille, boolean vertical){
        
    }
        
    public static void afficherPlateau () {
    int i,j;
     System.out.print("+|");
        for(j=0; j<plateau[0].length;j++)
            System.out.print(j);
            System.out.println("+");
        
        System.out.print(" |");    
        for(j=0; j<plateau[0].length;j++)
            System.out.print("-");
            System.out.println("|");
            
        for(i=0; i<plateau.length; i++) {
            System.out.print(i+"|");
            
        
        
        for(j=0; j<plateau[0].length;j++)
                if(plateau[i][j]==0){
                    System.out.print("O");
                }else if(plateau[i][j]==1){ 
                    System.out.print("•");
                }else if(plateau[i][j]==2){ 
                    System.out.print("§");
                }else if(plateau[i][j]==3){ 
                    System.out.print("X");
                }else {
                    System.out.print("!");
                }
            System.out.println("|");
        }

    System.out.print(" +");
        for(j=0; j<plateau[0].length;j++)
            System.out.print("-");
        System.out.println("+");
    }    
    
    
}