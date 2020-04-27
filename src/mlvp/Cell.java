/**
 * Case du plateau
 *
 * @author Medhi Louison et Valentin Perignon
 */
package mlvp;

public abstract class Cell implements Questionnable {
	// ----- Attributs -----
	
	protected Position pos;

	//  ----- Fonctions -----

	/**
	 * Transformer la position de la case en chaîne de caractères
	 *
	 * @return La position sous forme de chaîne
	 */
	public String posToStr() {
		return pos.getX() + " " + pos.getY();
	}

	/**
	 * Interaction entre le joueur et la case
	 *
	 * @param h Le joueur qui arrive sur la case
	 */
	public abstract void process(Hunter h);

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public abstract String toString();
}
