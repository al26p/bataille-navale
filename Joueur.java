public class Joueur {
    private String pseudo;
    public Plateau monPlateau;
    private int score;

    public Joueur(String name){
        pseudo=name;
        monPlateau= new Plateau();
        score = 0;
    }

    public int getScore(){return score;}
    public void win(){score ++;}
    public String getName(){return pseudo;}
    public void setName(String name){pseudo=name;}
}