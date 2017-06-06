public class Joueur {
    /**
     * Nom du joeur
     */
    private String pseudo;
    /**
     * Plateau sur lequel le joueur a placé ses bateaux
     */
    public Plateau monPlateau;
    private int score;
    public Joueur(String name){
        pseudo=name;
        monPlateau= new Plateau();
        score = 0;
    }

    /**Renvoie le score du joueur
     *
     * @return score
     */
    public int getScore(){return score;}

    /**
     * Fonction qui augmente le score du joueur de 1 point
     */
    public void win(){score ++;}

    /**Renvoie le nom du joueur
     *
     * @return Nom du joueur
     */
    public String getName(){return pseudo;}

    /**Permet de mettre  à jour le nom du joueur
     *
     * @param name Le nom du joueur
     */
    public void setName(String name){pseudo=name;}
}