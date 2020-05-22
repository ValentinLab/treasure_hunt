package model;

/**
 * Case du plateau de type pierre
 *
 * @author Medhi Louison et Valentin Perignon
 */
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
	 * @return Une chaîne de caractères expliquant l'action du joueur
	 */
	public String process(Hunter h) {
		h.getDir().setNear(h, wall);
		return "un mur";
	}

	public String getImagePath() {
		String path;

		char prefix = 'v';
		if(wall.getIsHorizontal()) {
			prefix  = 'h';
		}

		if(wall.getFrom().equals(pos)) {
			path ="assets/wall_" + prefix + "_0.png";
		} else if(wall.getTo().equals(pos)) {
			path ="assets/wall_" + prefix + "_2.png";
		} else {
			path ="assets/wall_" + prefix + "_1.png";
		}

		return path;
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

