package mlvp;

public class Side extends Cell {
	// ----- Constructeur -----
	
	public Side(Position p) {
		pos = p;
	}
	
	// ----- Fonctions -----
	
	public void process(Hunter h) {
		Hunter.getDir().seOpposite();
	}
	
	public String getSymbol() {
		return "+";
	}
}
