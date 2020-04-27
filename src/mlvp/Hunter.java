package mlvp;

public class Hunter implements Comparable<Hunter> {
	// ----- Attributs -----
	
	private char name;
	private Direction dir;
	private Position pos;
	private Position treasurePos;
	
	// ----- Constructeur -----
	
	public Hunter(char n, Position p) {
		dir = new Direction();
		name = n;
		pos = p;
		treasurePos = null;
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

	public int compareTo(Hunter that) {
		return this.name - that.name;
	}
	
	public String toString() {
		return String.valueOf(name);
	}
}
