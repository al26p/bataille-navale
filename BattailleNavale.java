import java.io.*;
import java.util.Scanner;
import java.net.*;


public class BattailleNavale {
    static String ERR1 = "GENERATION ERROR";
    static Scanner sc = new Scanner(System.in);
    static int gamemode;

    public static void main (String [] args){
        System.out.println("Hello");
        int waitForEnter=0;
        while(true){
            if(waitForEnter!=0){
                System.out.println("Press Enter to continue");
                try{System.in.read();}
                catch(Exception e){}

            }
            waitForEnter=1;
            // Clear la console ?
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
        System.out.println("Jeu de bataille navale créé par Alban PRATS et Juliette ROYERE, élèves à l'INSA LYON  (PCC1A Groupe 12) dans le cadre d'un projet d'informatique.");
    }





    private static void Client(){
        Scanner sb = new Scanner(System.in);
        Socket socket;

        try {
            System.out.println("Entrez l'adresse du serveur de jeu");
            String netAdress = sb.nextLine();
            socket = new Socket(netAdress, 2009);
            System.out.println("Demande de connexion");
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream dIn = new DataInputStream(socket.getInputStream());

            System.out.println("Entrez votre nom");
            String name = sb.nextLine();
            Joueur J2 = new Joueur(name);

            dOut.writeByte(1);
            dOut.writeUTF(J2.getName());
            dOut.flush();

            int pos_x = -1;
            int pos_y = -1;
            int case_state;
            boolean partie = true;
            boolean tour = false;
            boolean gotX = false;
            boolean gotY = false;
            Joueur J1 = new Joueur("J1");


            while(partie) {
                try{dIn.read();} //wait for input
                catch(Exception e){}


                byte typeTransmission = dIn.readByte();
                switch (typeTransmission) {
                    case 1:
                        String lanName = dIn.readUTF();
                        System.out.println("Vous jouez contre " + lanName);
                        J1.setName(lanName);
                        //Place les bateaux
                        generationInitiale(J2);
                        System.out.println(lanName+"commence");
                        tour = true;

                        break;
                    case 2:
                        pos_x = dIn.readInt();
                        gotX=true;
                        break;
                    case 3:
                        pos_y = dIn.readInt();
                        gotY=true;
                        break;
                    case 4:
                        case_state = dIn.readInt();
                        //changer l'état de la case demandée
                        case_state = dIn.readInt();
                        J1.monPlateau.plateau[pos_x][pos_y]=case_state;
                        tour = false;
                        break;
                }
                if(gotX&&gotY){
                    int to_send=J2.monPlateau.recuTir(J2.monPlateau.plateau, pos_x, pos_y);
                    dOut.writeByte(4);
                    dOut.writeInt(to_send);
                    dOut.flush();
                    tour=true;
                }
                if(tour) {
                    System.out.println("Entrez la coordonnée x que vous souhaitez attaquer");
                    pos_x = sb.nextInt();
                    System.out.println("Entrez la coordonnée y que vous souhaitez attaquer");
                    pos_y = sb.nextInt();


                    dOut.writeByte(2);
                    dOut.writeInt(pos_x);
                    dOut.flush();

                    dOut.writeByte(3);
                    dOut.writeInt(pos_y);
                    dOut.flush();
                }




            }

                /*
                Typical message :
                dOut.writeByte(1);    //Type de contenu à envoyer
                dOut.writeUTF("MESSAGE")  // Données à evnoyer
                dOut.flush(); //send !

                 */

            dIn.close();
            dOut.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void Server(){

        ServerSocket socketserver;
        Socket socketduserveur;
        Scanner sb = new Scanner(System.in);

        System.out.println("Entrez votre nom");
        String name = sb.nextLine();
        Joueur J1 = new Joueur(name);
        Joueur J2 = new Joueur("J2");

        try {
            socketserver = new ServerSocket(2009);
            System.out.println("Le server est à l'adresse " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Le serveur est à l'écoute du port " + socketserver.getLocalPort());
            socketduserveur = socketserver.accept();
            System.out.println("Un joueur s'est connecté");

            DataInputStream dIn = new DataInputStream(socketduserveur.getInputStream());
            DataOutputStream dOut = new DataOutputStream(socketduserveur.getOutputStream());

            //envoi du pseudo
            dOut.writeByte(1);
            dOut.writeUTF(J1.getName());
            dOut.flush();

            int pos_x = -1;
            int pos_y = -1;
            int case_state = -1;
            boolean partie = true;
            boolean tour = true;
            boolean gotX = false;
            boolean gotY = false;


            while(partie) {
                try{dIn.read();} //wait for input
                catch(Exception e){}


                byte typeTransmission = dIn.readByte();
                switch (typeTransmission) {
                    case 1:
                        String lanName = dIn.readUTF();
                        System.out.println("Vous jouez contre " + lanName);
                        J2.setName(lanName);
                        //Place les bateaux
                        generationInitiale(J1);
                        System.out.println("Vous commencez");
                        tour = true;

                        break;
                    case 2:
                        pos_x = dIn.readInt();
                        gotX=true;
                        break;
                    case 3:
                        pos_y = dIn.readInt();
                        gotY=true;
                        break;
                    case 4:
                        case_state = dIn.readInt();
                        //changer l'état de la case demandée
                        case_state = dIn.readInt();
                        J2.monPlateau.plateau[pos_x][pos_y]=case_state;
                        tour = false;
                        break;
                }
                if(gotX&&gotY){
                    int to_send=J1.monPlateau.recuTir(J1.monPlateau.plateau, pos_x, pos_y);
                    dOut.writeByte(4);
                    dOut.writeInt(to_send);
                    dOut.flush();
                    tour=true;
                }
                if(tour) {
                    System.out.println("Entrez la coordonnée x que vous souhaitez attaquer");
                    pos_x = sb.nextInt();
                    System.out.println("Entrez la coordonnée y que vous souhaitez attaquer");
                    pos_y = sb.nextInt();


                    dOut.writeByte(2);
                    dOut.writeInt(pos_x);
                    dOut.flush();

                    dOut.writeByte(3);
                    dOut.writeInt(pos_y);
                    dOut.flush();
                }




            }



            dOut.close();
            dIn.close();
            socketduserveur.close();
            socketserver.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generationInitiale (Joueur Player){
        boolean horizontal;
        int pos_x;
        int pos_y;
        Scanner sc = new Scanner(System.in);
        System.out.println("Placez vos bateaux");


        for(int boat = 1; boat < 6; boat++){
            switch (boat){
                case 1:
                    System.out.println("Vous placer un porte-avions (5 cases long)");
                    break;
                case 2:
                    System.out.println("Vous placer un croiseur (4 cases long)");
                    break;
                case 3:
                    System.out.println("Vous placer un contre-torpilleur (3 cases long)");
                    break;
                case 4:
                    System.out.println("Vous placer un sous-marin (3 cases long)");
                    break;
                case 5:
                    System.out.println("Vous placer untorpileur (2 cases long)");
                    break;
            }

            System.out.println("Orientation horizontale ?");
            horizontal = sc.nextBoolean();
            System.out.println("Coordonnée x (attention à la longueur du bateau)");
            pos_x = sc.nextInt();
            System.out.println("Coordonnée y (attention à la longueur du bateau)");
            pos_y = sc.nextInt();
            if(!Player.monPlateau.posBateau(Player.monPlateau.plateau, boat, horizontal, pos_x, pos_y)){
                boat --;
                System.out.println("Erreur lors de la créationde votre bateau, veuillez recommencer");
            }; //return true/false
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
