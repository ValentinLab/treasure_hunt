package mlvp;

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
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	// ----- Fonctions -----
	
	public static int computeDistance(Position from, Position to) {
		return (int)Math.pow(to.posX - from.posX, 2) + (int)Math.pow(to.posY - from.posY, 2);
	}

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

	public boolean equals(Position that) {
		return this.posX == that.posX && this.posY == that.posY;
	}

	public String toString() {
		return "[" + posX + " " + posY + "]";
	}
}
