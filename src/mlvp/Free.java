package mlvp;

public class Free extends Cell {
	// ----- Attributs -----
	
    Hunter player;

    // ----- Constructeur -----
    
	public Free(Position p){
		pos = p;
		player = null;
	}

	public Free(int x, int y) {
		this(new Position(x +1, y +1));
	}

	
    // ----- Fonctions -----
	
	public void process(Hunter h) {
		if(player ==  null) {
			player = h;
			player.getDir().setNear(h);
			player.setPos(pos);
		} else {
			player.getDir().setRandom();
		}
	}
	
	public String toString() {
		if(player == null) {
			return ".";
		}
		
		return player.toString();
	}
}
