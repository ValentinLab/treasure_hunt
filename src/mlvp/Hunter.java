package mlvp;

public class Hunter {
	// ----- Attributs -----
	
	private char name;
	private Direction dir;
	private Position pos;
	private Position treasurePos;
	
	// ----- Constructeur -----
	
	public Hunter(char n, Position p, Position treasure) {
		dir = new Direction();
		name = n;
		pos = p;
		treasurePos = treasure;
	}
	
	public Hunter(char n, Position p) {
		this(n, p, new Position(1, 1));
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
	
	//  ----- Setter -----
	
	public void setPos(Position p) {
		pos = p;
	}
	
	public void setTreasurePos(Position p) {
		treasurePos = p;
	}
	
	// ----- Fonctions -----
	
	public String toString() {
		return String.valueOf(name);
	}
}
