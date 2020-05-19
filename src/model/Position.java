package model;

/**
 * Position d'un élément sur le plateau de jeu
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Position {
	// ----- Attributs -----
	
	private int posX;
	private int posY;
	
	// ----- Constructeur -----
	
	public Position(int x, int y) {
		posX = x;
		posY = y;
	}
	
	// ----- Getters -----
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	// ----- Fonctions statiques -----

	/**
	 * Calculer la distance entre deux points
	 *
	 * @param from Position de départ
	 * @param to Position d'arrivée
	 * @return Distance de from à to
	 */
	public static int computeDistance(Position from, Position to) {
		return (int)Math.pow(to.posX - from.posX, 2) + (int)Math.pow(to.posY - from.posY, 2);
	}

	/**
	 * Transformation d'une direction en une position
	 *
	 * @param currentPos Position actuelle
	 * @param cp Direction
	 * @return Position dans la direction cp de currentPos
	 */
	public static Position directionToPos(Position currentPos, CardinalPoint cp) {
		int x = currentPos.posX;
		int y = currentPos.posY;

		if(cp == CardinalPoint.NORTH || cp == CardinalPoint.NORTH_EAST || cp == CardinalPoint.NORTH_WEST) {
			y -= 1;
		} else if(cp == CardinalPoint.SOUTH || cp == CardinalPoint.SOUTH_EAST || cp == CardinalPoint.SOUTH_WEST) {
			y += 1;
		}

		if(cp == CardinalPoint.WEST || cp == CardinalPoint.NORTH_WEST || cp == CardinalPoint.SOUTH_WEST) {
			x -= 1;
		} else if(cp == CardinalPoint.EAST || cp == CardinalPoint.NORTH_EAST || cp == CardinalPoint.SOUTH_EAST) {
			x += 1;
		}

		return new Position(x, y);
	}

	// ----- Fonctions -----

	/**
	 * Vérifier si deux positions sont égales
	 *
	 * @param that Position à vérifier
	 * @return Vrai si elles sont égales
	 */
	public boolean equals(Position that) {
		return this.posX == that.posX && this.posY == that.posY;
	}

	/**
	 * Transformer une position en une chaîne de caractères
	 *
	 * @return La position sous forme de chaîne
	 */
	public String toString() {
		return "[" + posX + " " + posY + "]";
	}
}
