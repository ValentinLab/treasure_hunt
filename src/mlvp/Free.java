package mlvp;

import javafx.geometry.Pos;

public class Free extends Cell {
	// ----- Attributs -----
	
    Hunter player;

    // ----- Constructeur -----

	public Free(Position p){
		pos = p;
		player = null;
	}

	public Free(int x, int y) {
		this(new Position(x+1, y+1));
	}

	public Free(int x, int y, Hunter h) {
		this(new Position(x+1, y+1));
		player = h;
	}
	
    // ----- Fonctions -----
	
	public void process(Hunter h) {
		if(player ==  null) {
			player = h;
			player.getDir().setNear(h);
			player.setPos(pos);
			System.out.println("Meilleure direction");
		} else {
			player.getDir().setRandom();
			System.out.println("Conflit de personnages");
		}
	}
	
	public String toString() {
		if(player == null) {
			return ".";
		}
		
		return player.toString();
	}
}
