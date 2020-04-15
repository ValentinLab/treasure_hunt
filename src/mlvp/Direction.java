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
	
	public void setOposite() {
		
	}
	
	public void setRandom() {
		int index = (int)(Math.random() * 8 + 1);
		CardinalPoint newDir  = CardinalPoint.values()[index];
		
		if(newDir.equals(dir)) {
			newDir = CardinalPoint.values()[index];
		}
		
		dir = newDir;
	}
	
	public void setNear(Position p) {
		
	}
}
