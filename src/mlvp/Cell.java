package mlvp;

public abstract class Cell implements Questionnable {
	// ----- Attributs -----
	
	protected Position pos;

	// ----- Getter -----

	public Position getPos() {
		return pos;
	}
	
	//  ----- Fonctions -----

	public String posToStr() {
		return pos.getPosX() + " " + pos.getPosY();
	}

	public abstract void process(Hunter h);

	public abstract String toString();
}
