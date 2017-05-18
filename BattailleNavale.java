import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class BattailleNavale {
    static String ERR1 = "GENERATION ERROR";
    static Scanner sc = new Scanner(System.in);
    static int gamemode;
    
    public static void main (String [] args){
        System.out.println("Hello");
        int dkzojdzik=0;
        while(true){
        if(dkzojdzik!=0){    
        System.out.println("Press Enter to continue");
        try{System.in.read();}
        catch(Exception e){}
        }
        dkzojdzik=1;
        System.out.println("Bienvenue dans Bataille Navale. Sélectionnez un choix pour commencer");
        System.out.println("1. 1 joueur vs IA");
        System.out.println("2. 1 VS 1 (LAN)");
        System.out.println("3. Comment jouer ?");
        System.out.println("4. A propos");
        gamemode = sc.nextInt();
        switch(gamemode){
            case 1 :
                System.out.println("Vous jouez contre l'ordinateur");
                System.out.println("Coming soon");
                break;
            case 2 : 
                System.out.println("LAN : Selectionnez votre rôle");
                System.out.println("0. Client : Un autre joueur heberge la partie");
                System.out.println("1. Server ; Vous hebergez la partie attendez que l'autre joueur se connecte a vous");
                int role = sc.nextInt();
                if(role==0){Client();}
                else{Server();}
                break;
            case 3 :
                howTo();
                break;
            case 4 :
                About();
                break;
                
        }
        }
    }
        
    
    private static void howTo(){
        System.out.println("Vous disposez de 5 bateaux de longueurs différentes à positionner sur un plateau de 10 sur 10.");
        System.out.println("Tour à tour positionnez les bateaux qui vous sont proposés de la manière suivante:");
        System.out.println("Si vous avez choisi de positionner votre bateau verticalement, votre bateau se situera en-dessous de la case rentrée.");
        System.out.println("Si vous avez choisi de positionner votre bateau horizontalement, votre bateau se situera à droite de la case rentrée");
        System.out.println("Le jeu commence, sélectionnez une case sur laquelle tirer sur le plateau adverse, si un bateau s'y situe le message 'touché' s'affichera sinon rien");
        System.out.println("Si toutes les cases d'un même bateau sont touchées, le message 'coulé' s'affichera");
        System.out.println("A l'inverse, si l'adversaire touche un de vos bateau, le message 'vous avez été touché' s'affichera et s'il parvient à couler votre bateau le message 'votre bateau a coulé' s'affichera");
        System.out.println("Le but est de couler tous les bateaux de l'adversaire");
        
    }
    
    private static void About(){
        System.out.println("Jeu de bataille navale créé par Alban PRATS et Juliette ROYERE, élèves à l'INSA LYON  (PCC1A Groupe 12) dans le cadre d'un projet d'informatique.")
    }    
    private static void Client(){
        Socket socket;
		BufferedReader in;
		PrintWriter out;

		try {
		
			socket = new Socket(InetAddress.getLocalHost(),2009);	
		        System.out.println("Demande de connexion");

		        in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		        String message_distant = in.readLine();
		        System.out.println(message_distant);
		        
		        socket.close();
		       
		}catch (UnknownHostException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
        
    }    
        
        
    private static void Server(){
        ServerSocket socketserver  ;
		Socket socketduserveur ;
		BufferedReader in;
		PrintWriter out;
		
		try {
		
			socketserver = new ServerSocket(2009);
			System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
			socketduserveur = socketserver.accept(); 
		        System.out.println("Un zéro s'est connecté");
			out = new PrintWriter(socketduserveur.getOutputStream());
		        out.println("Vous êtes connecté zéro !");
		        out.flush();
		                
		        socketduserveur.close();
		        socketserver.close();
		        
		}catch (IOException e) {
			
			e.printStackTrace();
		}
        
    }    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
     /* 
        //Demander le nom des joueurs
        String input;
        menu();
        
        System.out.println("Veuillez saisir le nom du J1 :");
        input = sc.nextLine();
        Joueur j1 = new Joueur(input);
        //Création des joueurs
        System.out.println("Veuillez saisir le nom du J2 :");
        input = sc.nextLine();
        Joueur j2 = new Joueur(input);
       
        System.out.println("Tableau "+j1.getName()+" : ");
        System.out.println("Score initial : "+ j1.getScore());
        
        j1.monPlateau.afficherPlateau(j1.monPlateau.plateau);
        j1.win();
        System.out.println("Score nouveau : "+ j1.getScore());
        System.out.println("Tableau "+j2.getName()+" : ");
        System.out.println("Score initial : "+ j2.getScore());
        j2.monPlateau.afficherPlateau(j2.monPlateau.plateau);
        System.out.println("Score nouveau : "+ j2.getScore());
        
    }
    
    */
    
    
    

}
