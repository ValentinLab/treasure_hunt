package mlvp;

public class Treasure extends Cell {
	// ----- Constructeur -----
	
	public Treasure(Position p) {
		pos =  p;
	}

	// ----- Fonctions -----
	
	public void process(Hunter h) {
		h.setPos(pos);
	}

	public String getSymbol() {
		return "T";
	}
}
