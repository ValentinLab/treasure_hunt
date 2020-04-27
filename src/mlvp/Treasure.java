/**
 * Case du plateau de type trésor
 *
 * @author Medhi Louison et Valentin Perignon
 */
package mlvp;

public class Treasure extends Cell {
	// ----- Constructeur -----
	
	public Treasure(Position p) {
		pos =  p;
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
	public void process(Hunter h) {
		h.setPos(pos);
		System.out.println("Trésor découvert !");
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
