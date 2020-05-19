package model;

/**
 * Case du plateau de type côté
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Side extends Cell {
	// ----- Constructeur -----
	
	public Side(Position p) {
		pos = p;
	}

	public Side(int x, int y) {
		this(new Position(x + 1, y + 1));
	}
	
	// ----- Fonctions -----

	/**
	 * Interaction entre le joueur et la case
	 *
	 * @param h Le joueur qui arrive sur la case
	 */
	public String process(Hunter h) {
		h.getDir().setOpposite();
		return "bord du jeu";
	}

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public String toString() {
		return "+";
	}
}
