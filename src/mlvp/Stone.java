package mlvp;

public class Stone extends Cell {
	// ----- Attributs -----

	Wall wall;

	// ----- Constructeur -----
	
	public Stone(Position p, Wall w) {
		pos = p;
		wall = w;
	}

	public Stone(int x, int y, Wall w) {
		this(new Position(x + 1, y +1), w);
	}

	// ----- Functions -----
	
	public void process(Hunter h) {
		h.getDir().setNear(h, wall);
		System.out.println("C'était un mur ...");
	}

	public String toString() {
		return("#");
	}
}

