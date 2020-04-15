package mlvp;

public class Hunter {
	// ----- Attributs -----
	
	private String name;
	private Direction dir;
	private Position pos;
	
	// ----- Constructeur -----
	
	public Hunter(String n, Position p) {
		name = n;
		pos = p;
	}
	
	// ----- Getters -----
	
	public Direction getDir() {
		return dir;
	}
	
	public Position getPos() {
		return pos;
	}
	
	//  ----- Setter -----
	
	public void setPos(Position p) {
		pos = p;
	}
	
	// ----- Fonctions -----
	
	public String toString() {
		return name;
	}
}
