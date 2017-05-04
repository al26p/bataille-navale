public class BattailleNavale {
    public static void main (String [] args){
        System.out.println("Hello");
        Joueur juliette = new Joueur("ju");
        Joueur alban = new Joueur("al");
       
        System.out.println("Tableau "+juliette.getName()+" : ");
        System.out.println("Score initial : "+ juliette.getScore());
        juliette.monPlateau.afficherPlateau();
        juliette.setScore(true);
        System.out.println("Score nouveau : "+ juliette.getScore());
        System.out.println("Tableau "+alban.getName()+" : ");
        System.out.println("Score initial : "+ alban.getScore());
        alban.monPlateau.afficherPlateau();
        System.out.println("Score nouveau : "+ alban.getScore());
    }
}