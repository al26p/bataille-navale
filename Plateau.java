public class Plateau {
    public Complexe(){
        
    public static boolean[][] genererMondeAleatoire(int hauteur, int largeur, double p){
    boolean[][] monde = new boolean[hauteur][largeur];
     for (int i=0;i<hauteur;i++) {
        for(int j=0; j<largeur;j++) {
        monde[i][j] = (Math.random()<p);
        }
    }
        return monde;
    }
    }
    
    
}