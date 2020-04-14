package mlvp;

public class Stone extends Cell {
	
	Stone(Position pos){
		this.pos = pos;
	}
	public String getSymbol() {
		return("#");
	}
	public void process(Hunter h) {
		h.getDir().setNear();
	}

}

