package mlvp;

public class Stone extends Cell {
	// ----- Constructeur -----
	
	Stone(Position pos){
		this.pos = pos;
	}
	
    // ----- Functions -----
	
	public String getSymbol() {
		return("#");
	}
	
	public void process(Hunter h) {
		h.getDir().setNear(h);
	}

}

