package mlvp;

/**
 * Case du plateau
 *
 * @author Medhi Louison et Valentin Perignon
 */
public abstract class Cell implements Questionnable {
	// ----- Attribut -----
	
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
	public abstract String process(Hunter h);

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public abstract String toString();
}
