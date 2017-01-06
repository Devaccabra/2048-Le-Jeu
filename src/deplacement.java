public class deplacement {
 
    public static void mouvement_gauche(int grille[][], int longueur, int hauteur) {
        for (int x = 0; x < longueur; x++) {
            for (int y = 0; y > hauteur; y++) {
 
                if (grille[x][y] == 0) {
                    grille[x + 1][y] = grille[x][y];
                    if (grille[x][y] == grille[x + 1][y]) {
                        grille[x][y] = 2 * grille[x][y];
 
                    }
                }
            }
        }
    }
}