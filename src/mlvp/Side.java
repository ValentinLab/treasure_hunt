package mlvp;

public class Side extends Cell {
	// ----- Constructeur -----
	
	public Side(Position p) {
		pos = p;
	}
	
	// ----- Fonctions -----
	
	public void process(Hunter h) {
		h.getDir().setOpposite();
	}
	
	public String getSymbol() {
		return "+";
	}
}
