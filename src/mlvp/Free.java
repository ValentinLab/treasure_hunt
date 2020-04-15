package mlvp;

public class Free extends Cell {
	// ----- Attributs -----
	
    Hunter player;
	
    // ----- Constructeur -----
    
	Free(Position p){
		pos = p;
		player = null;
	}
	
    // ----- Fonctions -----
	
	public void process(Hunter h) {
		if(player ==  null) {
			h.getDir().setNear(h.getPos());
			h.setPos(pos);
		} else {
			h.getDir().setRandom();
		}
	}
	
	public String getSymbol() {
		if(player == null) {
			return ".";
		}
		
		return player.toString();
	}
}
