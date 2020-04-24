package mlvp;

public class Side extends Cell {
	// ----- Constructeur -----
	
	public Side(Position p) {
		pos = p;
	}

	public Side(int x, int y) {
		this(new Position(x + 1, y + 1));
	}
	
	// ----- Fonctions -----
	
	public void process(Hunter h) {
		h.getDir().setOpposite();
		System.out.println("Bord du jeu...");
	}
	
	public String toString() {
		return "+";
	}
}
