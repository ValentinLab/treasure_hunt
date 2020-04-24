package mlvp;

public class Stone extends Cell {
	// ----- Constructeur -----
	
	public Stone(Position p) {
		pos = p;
	}

	public Stone(int x, int y) {
		this(new Position(x + 1, y +1));
	}
	
	// ----- Functions -----
	
	public void process(Hunter h) {
		h.getDir().setNear(h);
		System.out.println("C'était un mur ...");
	}

	public String toString() {
		return("#");
	}
}

