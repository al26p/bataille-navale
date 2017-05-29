public class Joueur {
    private String pseudo;
    Plateau monPlateau;
    private int score;

    public Joueur(String name){
        pseudo=name;
        monPlateau= new Plateau();
        score = 0;
    }

    public int getScore(){return score;}
    public void win(){score ++;}
    public String getName(){return pseudo;}

}