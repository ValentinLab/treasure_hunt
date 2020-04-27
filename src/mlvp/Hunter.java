package mlvp;

/**
 * Joueur
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Hunter implements Comparable<Hunter> {
	// ----- Attributs -----
	
	private char name;
	private Direction dir;
	private Position pos;
	private Position treasurePos;
	
	// ----- Constructeur -----
	
	public Hunter(char n, Position p) {
		dir = new Direction();
		name = n;
		pos = p;
		treasurePos = null;
	}
	
	// ----- Getters -----
	
	public Direction getDir() {
		return dir;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public Position getTreasurePos() {
		return treasurePos;
	}
	
	//  ----- Setters -----
	
	public void setPos(Position p) {
		pos = p;
	}
	
	public void setTreasurePos(Position p) {
		treasurePos = p;
	}
	
	// ----- Fonctions -----

	/**
	 * Comparer deux joueurs entre eux
	 *
	 * @param that Joueur à comparer
	 * @return Vrai si les joueurs sont égaux (i.e. même nom)
	 */
	public int compareTo(Hunter that) {
		return this.name - that.name;
	}

	/**
	 * Transformer un joueur en une chaîne de caractères
	 *
	 * @return Joueur sous forme de chaîne
	 */
	public String toString() {
		return String.valueOf(name);
	}
}
