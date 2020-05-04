package mlvp;

/**
 * Case du plateau de type libre
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Free extends Cell {
	// ----- Attribut -----
	
    Hunter player;

    // ----- Constructeurs -----

	public Free(Position p) {
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

	/**
	 * Supprimer le joueur de la case si sa position est différente
	 */
	private void removePlayer() {
		if(player != null) {
			if(!player.getPos().equals(pos)) {
				player = null;
			}
		}
	}

	/**
	 * Interaction entre le joueur et la case
	 *
	 * @param h Le joueur qui arrive sur la case
	 */
	public void process(Hunter h) {
		removePlayer();

		if(player ==  null) {
			player = h;
			player.setPos(pos);
			player.getDir().setNear(h);
			System.out.println("Meilleure direction");
		} else {
			h.getDir().setRandom();
			System.out.println("Conflit de personnages");
		}
	}

	/**
	 * Transformer la case en chaîne de caractères
	 *
	 * @return La case sous forme de chaîne
	 */
	public String toString() {
		removePlayer();

		if(player == null) {
			return ".";
		}
		
		return player.toString();
	}
}
