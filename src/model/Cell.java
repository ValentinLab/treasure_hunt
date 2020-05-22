package model;

/**
 * Case du plateau
 *
 * @author Medhi Louison et Valentin Perignon
 */
public abstract class Cell implements Questionnable {
	// ----- Attribut -----
	
	protected Position pos;

	// ----- Getter -----

	public Position getPos() {
		return pos;
	}

	//  ----- Fonctions -----

	/**
	 * Interaction entre le joueur et la case
	 *
	 * @param h Le joueur qui arrive sur la case
	 */
	public abstract String process(Hunter h);

	public abstract String getImagePath();

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public abstract String toString();
}
