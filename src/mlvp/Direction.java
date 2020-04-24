package mlvp;

public class Direction {
	// ----- Attributs -----
	
	CardinalPoint dir;
	
	// ----- Constructeur -----
	
	public Direction(CardinalPoint cp) {
		dir = cp;
	}
	
	public Direction() {
		this(CardinalPoint.values()[(int)(Math.random() * 8)]);
	}
	
	// -----  Setters -----
	
	public void setOpposite() {
		CardinalPoint newDir = CardinalPoint.NORTH;
		switch(dir) {
			case NORTH:
				newDir = CardinalPoint.SOUTH;
				break;
			case NORTH_EAST:
				newDir = CardinalPoint.SOUTH_WEST;
				break;
			case EAST:
				newDir = CardinalPoint.WEST;
				break;
			case SOUTH_EAST:
				newDir = CardinalPoint.NORTH_WEST;
				break;
			case SOUTH:
				newDir = CardinalPoint.NORTH;
				break;
			case SOUTH_WEST:
				newDir = CardinalPoint.NORTH_EAST;
				break;
			case WEST:
				newDir = CardinalPoint.EAST;
				break;
			case NORTH_WEST:
				newDir = CardinalPoint.SOUTH_EAST;
				break;
		}
		
		dir = newDir;
	}
	
	public void setRandom() {
		int index = (int)(Math.random() * 8 + 1);
		CardinalPoint newDir  = CardinalPoint.values()[index];
		
		if(newDir.equals(dir)) {
			newDir = CardinalPoint.values()[index];
		}
		
		dir = newDir;
	}
	
	public void setNear(Hunter h) {
		CardinalPoint bestDir = CardinalPoint.NORTH;
		int  bestDistance = Position.computeDistance(h.getPos(), h.getTreasurePos());
		
		int currentDistance = 0;
		for(CardinalPoint cp : CardinalPoint.values()) {
			Position p = Position.directionToPos(h.getPos(), cp);
			currentDistance = Position.computeDistance(p, h.getTreasurePos());
			
			if(currentDistance < bestDistance) {
				bestDistance = currentDistance;
				bestDir = cp;
			}
		}
		
		dir = bestDir;
	}

	// ----- Fonctions -----

	public int dirToX() {
		int x = 0;

		if(dir == CardinalPoint.NORTH_EAST || dir == CardinalPoint.EAST || dir == CardinalPoint.SOUTH_EAST) {
			x = +1;
		} else if(dir == CardinalPoint.NORTH_WEST || dir == CardinalPoint.WEST || dir == CardinalPoint.SOUTH_WEST) {
			x = -1;
		}

		return x;
	}

	public int dirToY() {
		int y = 0;

		if(dir == CardinalPoint.NORTH_WEST || dir == CardinalPoint.NORTH || dir == CardinalPoint.NORTH_EAST) {
			y = -1;
		} else if(dir == CardinalPoint.SOUTH_WEST || dir == CardinalPoint.SOUTH || dir == CardinalPoint.SOUTH_EAST) {
			y = +1;
		}

		return y;
	}

	public String toString() {
		return "" + dir;
	}
}
