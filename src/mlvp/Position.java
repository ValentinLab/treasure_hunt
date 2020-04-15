package mlvp;

public class Position {
	// ----- Attributs -----
	
	int posX;
	int posY;
	
	// ----- Constructeur -----
	
	public Position(int x, int y) {
		posX = x;
		posY = y;
	}
	
	// ----- Fonctions -----
	
	public static int computeDistance(Position from, Position to) {
		return (int)Math.pow(to.posX - from.posX, 2) + (int)Math.pow(to.posY - from.posY, 2);
	}
	
	public static Position directionToPos(Position currentPos, CardinalPoint cp) {
		int x = currentPos.posX;
		int y = currentPos.posY;
		
		if(cp == CardinalPoint.NORTH || cp == CardinalPoint.NORTH_EAST || cp == CardinalPoint.NORTH_WEST) {
			x -= 1;
		} else if(cp == CardinalPoint.SOUTH || cp == CardinalPoint.SOUTH_EAST || cp == CardinalPoint.SOUTH_WEST) {
			x += 1;
		}
		
		if(cp == CardinalPoint.WEST || cp == CardinalPoint.NORTH_WEST || cp == CardinalPoint.SOUTH_WEST) {
			y -= 1;
		} else if(cp == CardinalPoint.EAST || cp == CardinalPoint.NORTH_EAST || cp == CardinalPoint.SOUTH_EAST) {
			y += 1;
		}
		
		return new Position(x, y);
	}
}
