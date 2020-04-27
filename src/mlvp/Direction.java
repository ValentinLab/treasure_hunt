/**
 * Direction d'un joueur
 *
 * @author Medhi Louison et Valentin Perignon
 */
package mlvp;

public class Direction {
	// ----- Attributs -----
	
	CardinalPoint dir;
	
	// ----- Constructeur -----
	
	public Direction() {
		dir = CardinalPoint.values()[(int)(Math.random() * 8)];
	}
	
	// ----- Fonctions -----

	/**
	 * Positionner la direction à l'opposé
	 */
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

	/**
	 * Positionner la direction de manière aléatoire
	 */
	public void setRandom() {
		int index = (int)(Math.random() * 8);
		CardinalPoint newDir = dir;

		do {
			newDir  = CardinalPoint.values()[index];
		} while(newDir.equals(dir));
		
		dir = newDir;
	}

	/**
	 * Positionner la direction de manière à se rapprocher du trésor
	 *
	 * @param h Joueur
	 */
	public void setNear(Hunter h) {
		CardinalPoint bestDir = CardinalPoint.NORTH;
		int  bestDistance = Position.computeDistance(h.getPos(), h.getTreasurePos());
		
		int currentDistance;
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

	/**
	 * Positionner la direction de manière à se rapprocher du trésor et éviter le mur
	 *
	 * @param h Joueur
	 * @param w Mur que rencontre le joueur
	 */
	public void setNear(Hunter h, Wall w) {
		// Calcul de la meilleure distance
		setNear(h);

		// Modifier la direction selon le mur
		if(w.getIsHorizontal()) {
			if(dir == CardinalPoint.NORTH_WEST || dir == CardinalPoint.SOUTH_WEST) {
				dir = CardinalPoint.WEST;
			} else if(dir == CardinalPoint.NORTH_EAST || dir == CardinalPoint.SOUTH_EAST) {
				dir = CardinalPoint.EAST;
			} else {
				int distFrom = Position.computeDistance(h.getPos(), w.getFrom());
				int distTo = Position.computeDistance(h.getPos(), w.getTo());
				if(distFrom <= distTo) {
					dir = CardinalPoint.WEST;
				} else {
					dir = CardinalPoint.EAST;
				}
			}
		} else {
			if(dir == CardinalPoint.NORTH_WEST || dir == CardinalPoint.NORTH_EAST) {
				dir = CardinalPoint.NORTH;
			} else if(dir == CardinalPoint.SOUTH_WEST || dir == CardinalPoint.SOUTH_EAST) {
				dir = CardinalPoint.SOUTH;
			} else {
				int distFrom = Position.computeDistance(h.getPos(), w.getFrom());
				int distTo = Position.computeDistance(h.getPos(), w.getTo());
				if(distFrom <= distTo) {
					dir = CardinalPoint.NORTH;
				} else {
					dir = CardinalPoint.SOUTH;
				}
			}
		}
	}

	/**
	 * Transformer la direction en une valeur x apparternant à [-1, +1]
	 *
	 * @return Valeur x
	 */
	public int dirToX() {
		int x = 0;

		if(dir == CardinalPoint.NORTH_EAST || dir == CardinalPoint.EAST || dir == CardinalPoint.SOUTH_EAST) {
			x = +1;
		} else if(dir == CardinalPoint.NORTH_WEST || dir == CardinalPoint.WEST || dir == CardinalPoint.SOUTH_WEST) {
			x = -1;
		}

		return x;
	}

	/**
	 * Transformer la direction en une valeur y apparternant à [-1, +1]
	 *
	 * @return Valeur y
	 */
	public int dirToY() {
		int y = 0;

		if(dir == CardinalPoint.NORTH_WEST || dir == CardinalPoint.NORTH || dir == CardinalPoint.NORTH_EAST) {
			y = -1;
		} else if(dir == CardinalPoint.SOUTH_WEST || dir == CardinalPoint.SOUTH || dir == CardinalPoint.SOUTH_EAST) {
			y = +1;
		}

		return y;
	}

	/**
	 * Transformer la direction en une chaîne de caractères
	 *
	 * @return Direction sous forme de chaîne
	 */
	public String toString() {
		return "" + dir;
	}
}
