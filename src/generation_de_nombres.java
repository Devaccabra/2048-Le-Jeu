import java.util.Random;
 
public class generation_de_nombres {
 
    public static void generation(int hauteur, int longueur, Interface2048 InterfaceRecrue, int grille[][],
            boolean move)
 
    {
 
        int x;
        x = 0;
 
        Random rd = new Random();
 
        int a = rd.nextInt(longueur); // largeur al�atoire
        int b = rd.nextInt(hauteur); // hauteur al�atoire
        int c = rd.nextInt(11); // probabilit�
 
        if (move == true) {
 
            if (c <= 7)
                x = 2;
            else
                x = 4;
            if (grille[a][b] == 0) {
                grille[a][b] = x;
 
                InterfaceRecrue.modifieCase(a, b, grille[a][b]);
            }
        }
    }
}