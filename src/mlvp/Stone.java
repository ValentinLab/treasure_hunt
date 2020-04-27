/**
 * Case du plateau de type pierre
 *
 * @author Medhi Louison et Valentin Perignon
 */
package mlvp;

public class Stone extends Cell {
	// ----- Attributs -----

	Wall wall;

	// ----- Constructeur -----
	
	public Stone(Position p, Wall w) {
		pos = p;
		wall = w;
	}

	// ----- Functions -----

	/**
	 * Interaction entre le joueur et la case
	 *
	 * @param h Le joueur qui arrive sur la case
	 */
	public void process(Hunter h) {
		h.getDir().setNear(h, wall);
		System.out.println("C'était un mur ...");
	}

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public String toString() {
		return("#");
	}
}

