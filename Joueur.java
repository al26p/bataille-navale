public class Personne {
    String pseudo;
    Plateau monPlateau;
    
    public Personne(String name){
        pseudo=name;
        monPlateau= new Plateau();
    }
    
}