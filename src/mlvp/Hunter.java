package mlvp;

public class Hunter {
	// ----- Attributs -----
	
	private String name;
	private Direction dir;
	private Position pos;
	private Position treasurePos;
	
	// ----- Constructeur -----
	
	public Hunter(String n, Position p, Position treasure) {
		name = n;
		pos = p;
		treasurePos = treasure;
	}
	
	public Hunter(String n, Position p) {
		this(n, p, null);
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
		return name;
	}
}
