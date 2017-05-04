public class Joueur {
    private String pseudo;
    Plateau monPlateau;
    private int score;
    
    public Joueur(String name){
        pseudo=name;
        monPlateau= new Plateau();
        score = 10;
    }
    
    public int getScore(){return score;}
    public void setScore(boolean win){if(win){score ++;}}
    public String getName(){return pseudo;}
    
}