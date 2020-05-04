package mlvp;

/**
 * Classe principale
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Game {
	public static void main(String args[]) {
		// Création du plateau de jeu
		Board b = new Board();
		// Lancement du jeu
		b.playGame();

		// Fenêtre
		MainFrame mf = new MainFrame();
	}
}
