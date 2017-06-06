import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;


/**Gestionnaire d'affichage du jeu du Snake issu du 
 * gestionnaire d'affichage pour la fourmi de Langton (issu de l'affichage du "Jeu de la vie")
 * @author Jean-Francois TREGOUET - modification: Thoniel Solal, Kabbadj Ghali, Alban PRATS
 */
public class Affichage1 extends JFrame{
    public static Affichage1 world = null;
    private PanneauGrille pg;

    public Affichage1(Plateau p,String name) {
        super(name);
        pg = new PanneauGrille(p);
        setContentPane(pg);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**Méthode permettant de fermer l'affichage.
     */

    public void fermer(){
        setVisible(false);
    }

    /**
     * Affiche un plateau.
     * @param p le plateau à afficher
     */
    public static void afficherplateau(Plateau p) {
        world.pg.p = p;
        world.repaint();

    }

    /**
     * Calcule la résolution la plus appropriée à la taille du plateau de
     * façon à ce que la fenêtre occupe 95% de la hauteur ou de la
     * largeur de la zone utile de l'écran.
     * @param plateau le plateau à afficher
     */
    private static int calcRes(int[][] plateau) {
        final double p = 0.45; // pourçentage de la zone utile à occuper
        int resC;
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(); // Taille zone utile
        resC = Math.min((int)(bounds.height*p)/plateau.length,(int)(bounds.width*p)/plateau[0].length);
        resC = Math.max(1,resC); // valeur plancher de 1
        return resC;
    }

    class PanneauGrille extends JPanel {
        private int res;
        private BufferedImage worldImage;
        public Plateau p;


        public PanneauGrille(Plateau monP) {
            p = monP;
            res = Affichage1.calcRes(p.plateau);
            worldImage = new BufferedImage(res*p.plateau[0].length,res*p.plateau.length,BufferedImage.TYPE_INT_RGB);
            setPreferredSize(new Dimension(res*p.plateau[0].length,res*p.plateau.length));



        }
        private void dessineplateau(Graphics g) {

            //Création couleurs
            Color tete = new Color (10, 121, 12);
            Color monVert = new Color (65, 165, 39);
            Color monRouge = new Color (218, 21, 21);
            Color monBleu = new Color (1, 68, 240);


            int nbL = p.plateau.length;
            int nbC = p.plateau[0].length;
            // couleur de fond
            g.setColor(Color.WHITE);
            g.fillRect(0,0,res*nbC,res*nbL);
            // couleurs

            g.setColor(monRouge);
            for (int i = 0; i < nbL; i++)
                for (int j = 0; j < nbC; j++)
                    if (p.plateau[i][j]==3) //couleur de la pomme
                        g.fillRect(res*j,res*i,res,res);

            g.setColor(Color.WHITE);
            for (int i = 0; i < nbL; i++)
                for (int j = 0; j < nbC; j++)
                    if (p.plateau[i][j]==2) // couleur du fond
                        g.fillRect(res*j,res*i,res,res);

            g.setColor(Color.BLACK);
            for (int i = 0; i < nbL; i++)
                for (int j = 0; j < nbC; j++)
                    if (p.plateau[i][j]==4) // couleur des obstacles
                        g.fillRect(res*j,res*i,res,res);

            g.setColor(monVert);
            for (int i = 0; i < nbL; i++)
                for (int j = 0; j < nbC; j++)
                    if (p.plateau[i][j]==1) // couleur de la tête
                        g.fillRect(res*j,res*i,res,res);

            g.setColor(monBleu);
            for (int i = 0; i < nbL; i++)
                for (int j = 0; j < nbC; j++)
                    if (p.plateau[i][j]==0) // couleur des powers up
                        g.fillRect(res*j,res*i,res,res);

            //g.setColor(Color.YELLOW);
            //for (int i = 0; i < nbL; i++)
            // for (int j = 0; j < nbC; j++)
            //   if (p.plateau[i][j]==8) // couleur des zones interdites
            //    g.fillRect(res*j,res*i,res,res);
        }
        public void paint(Graphics g) {
            Graphics gw = worldImage.getGraphics();
            dessineplateau(gw);
            g.drawImage(worldImage,0,0,null);
        }
    }


}       

