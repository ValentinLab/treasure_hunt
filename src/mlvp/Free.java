package mlvp;

public class Free extends Cell {
	
    boolean isTaken;
	
    // ----- Constructeur -----
	Free(Position pos){
		this.pos = pos;
	}
	
    // ----- Fonctions -----
	public void setTaken(){
		isTaken = !isTaken;
	}
	public String getSymbol() {
		return(".");
	}
	
	public void process(Hunter h) {
		if(isTaken) {
			h.getDir().setDir();
			h.setPos(pos);
		}
		else {
			h.getDir().setOpposite();
		}
	}
	
}
