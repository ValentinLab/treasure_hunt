package model;

/**
 * Case du plateau de type trésor
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Treasure extends Cell {
	// ----- Attribut -----

	boolean isTaken;

	// ----- Constructeurs -----
	
	public Treasure(Position p) {
		pos =  p;
		isTaken = false;
	}

	public Treasure(int x, int y) {
		this(new Position(x + 1, y +1));
	}

	// ----- Fonctions -----

	/**
	 * Interaction entre le joueur et la case
	 *
	 * @param h Le joueur qui arrive sur la case
	 * @return Une chaîne de caractères expliquant l'action du joueur
	 */
	public String process(Hunter h) {
		h.setPos(pos);
		if(isTaken) {
			return "**ex-aequo**";
		}
		
		isTaken = true;
		return "**gagné**";
	}

	public String getImagePath() {
		return "assets/treasure.png";
	}

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public String toString() {
		return "T";
	}
}
