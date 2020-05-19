/**
 * Direction d'un joueur
 *
 * @author Medhi Louison et Valentin Perignon
 */
package model;

public class Direction {
	// ----- Attribut -----
	
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
		CardinalPoint newDir;

		int index;
		do {
			index = (int)(Math.random() * 8);
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
		int distFrom = Position.computeDistance(h.getPos(), w.getFrom()); // distance joueur <-> début du mur
		int distTo = Position.computeDistance(h.getPos(), w.getTo()); // distance joueur <-> fin du mur
		if(w.getIsHorizontal()) {
			if(distFrom <= distTo) {
				if(h.getPos().getX() == w.getFrom().getX()) {
					if(h.getPos().getY() < w.getFrom().getY()) {
						dir = CardinalPoint.SOUTH_WEST;
					} else {
						dir = CardinalPoint.NORTH_WEST;
					}
				} else {
					dir = CardinalPoint.WEST;
				}
			} else {
				if(h.getPos().getX() == w.getTo().getX()) {
					if(h.getPos().getY() < w.getTo().getY()) {
						dir = CardinalPoint.SOUTH_EAST;
					} else {
						dir = CardinalPoint.NORTH_EAST;
					}
				} else {
					dir = CardinalPoint.EAST;
				}
			}
		} else {
			if(distFrom <= distTo) {
				if(h.getPos().getY() == w.getFrom().getY()) {
					if(h.getPos().getX() < w.getFrom().getX()) {
						dir = CardinalPoint.NORTH_EAST;
					} else {
						dir = CardinalPoint.NORTH_WEST;
					}
				} else {
					dir = CardinalPoint.NORTH;
				}
			} else {
				if(h.getPos().getY() == w.getTo().getY()) {
					if(h.getPos().getX() < w.getTo().getX()) {
						dir = CardinalPoint.SOUTH_EAST;
					} else {
						dir = CardinalPoint.SOUTH_WEST;
					}
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
		return String.valueOf(dir.ordinal() + 1);
	}
}
