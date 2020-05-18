package mlvp;

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
	 */
	public String process(Hunter h) {
		h.setPos(pos);
		if(isTaken) {
			return "**ex-aequo**";
		}
		
		isTaken = true;
		return "**gagné**";
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
