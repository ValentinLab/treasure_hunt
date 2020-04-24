package mlvp;

public class Treasure extends Cell {
	// ----- Constructeur -----
	
	public Treasure(Position p) {
		pos =  p;
	}

	public Treasure(int x, int y) {
		this(new Position(x + 1, y +1));
	}

	// ----- Fonctions -----
	
	public void process(Hunter h) {
		h.setPos(pos);
	}

	public String toString() {
		return "T";
	}
}
