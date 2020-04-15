package mlvp;

public class Direction {
	// ----- Attributs -----
	
	CardinalPoint dir;
	
	// ----- Constructeur -----
	
	public Direction(CardinalPoint cp) {
		dir = cp;
	}
	
	public Direction() {
		this(CardinalPoint.values()[(int)(Math.random() * 8 + 1)]);
	}
	
	// -----  Fonctions -----
	
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
			currentDistance = Position.computeDistance(Position.directionToPos(h.getPos(), cp), h.getTreasurePos());
			
			if(currentDistance < bestDistance) {
				bestDistance = currentDistance;
				bestDir = cp;
			}
		}
		
		dir = bestDir;
	}
}
