import java.util.Random;
 
public class generation_de_nombres {
 
    public static void generation(int hauteur, int longueur, Interface2048 InterfaceRecrue, int grille[][],
            boolean move)
 
    {
 
        int x;
        x = 0;
 
        Random rd = new Random();
 
        int a = rd.nextInt(longueur); // largeur aléatoire
        int b = rd.nextInt(hauteur); // hauteur aléatoire
        int c = rd.nextInt(11); // probabilité
 
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